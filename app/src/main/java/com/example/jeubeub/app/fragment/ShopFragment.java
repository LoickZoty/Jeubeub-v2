package com.example.jeubeub.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.example.jeubeub.app.service.ShopService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;

import org.json.JSONObject;

import java.util.Optional;


public class ShopFragment extends Fragment {

    Button Gpay_item_1;
    Button Gpay_item_2;
    ShopService shopService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        Gpay_item_1 = view.findViewById(R.id.Gpay_item_1);
        Gpay_item_2 = view.findViewById(R.id.Gpay_item_2);
        shopService = new ShopService(getActivity());
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart() {
        super.onStart();
        Gpay_item_1.setOnClickListener(v -> shopService.requestPayment(Gpay_item_1));
        Gpay_item_2.setOnClickListener(v -> shopService.requestPayment(Gpay_item_2));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 911) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    PaymentData paymentData = PaymentData.getFromIntent(data);
                    ShopService.handlePaymentSuccess(paymentData);
                    break;

                case Activity.RESULT_CANCELED:
                    // The user cancelled the payment attempt
                    break;

                case AutoResolveHelper.RESULT_ERROR:
                    Status status = AutoResolveHelper.getStatusFromIntent(data);
                    Toast.makeText(getActivity(), "Error googlePay " + status.getStatusCode(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
