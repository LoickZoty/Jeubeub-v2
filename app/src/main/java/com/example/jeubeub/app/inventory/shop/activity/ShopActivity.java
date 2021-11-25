package com.example.jeubeub.app.inventory.shop.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jeubeub.R;
import com.example.jeubeub.app.inventory.shop.model.ArticleItem;
import com.example.jeubeub.app.inventory.shop.service.ShopService;
import com.example.jeubeub.app.leaderboard.service.LeaderboardService;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopActivity extends AppCompatActivity {

    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 911;
    private ShopService shopService;
    private PaymentsClient paymentsClient;
    private int quantity;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        this.shopService = new ShopService();

        paymentsClient = shopService.createPaymentsClient(this);
        ListView articleListView = findViewById(R.id.article_listView);

        //TODO METHODE DANS SHOPSERVICE QUI CALL BDD POUR REMPLIRE LE SHOP
        List<ArticleItem> list = new ArrayList<>();
        list.add(new ArticleItem(1,"500 Or", 0.99, 500));
        list.add(new ArticleItem(4,"100 Gemme", 2.99, 100));
        articleListView.setAdapter(shopService.getAdapter(list, getLayoutInflater(), this));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void requestPayment(int id, int quantity, String price) {
        Optional<JSONObject> paymentDataRequestJson = this.shopService.getPaymentDataRequest(price);
        if (paymentDataRequestJson.isPresent()) {
            PaymentDataRequest request = PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());
            this.quantity = quantity;
            this.id = id;
            AutoResolveHelper.resolveTask(paymentsClient.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOAD_PAYMENT_DATA_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    PaymentData paymentData = PaymentData.getFromIntent(data);
                    this.shopService.handlePaymentSuccess(this ,paymentData, this.id, this.quantity);
                    break;

                case Activity.RESULT_CANCELED:
                    break;

                case AutoResolveHelper.RESULT_ERROR:
                    AutoResolveHelper.getStatusFromIntent(data);
                    break;
            }
        }
    }
}