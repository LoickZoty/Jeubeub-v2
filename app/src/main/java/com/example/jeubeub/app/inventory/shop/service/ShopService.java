package com.example.jeubeub.app.inventory.shop.service;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.MenuActivity;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.inventory.model.InventoryItem;
import com.example.jeubeub.app.inventory.shop.activity.ShopActivity;
import com.example.jeubeub.app.inventory.shop.model.ArticleItem;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopService {

    public ShopService(){}

    public ArrayAdapter<ArticleItem> getAdapter(List<ArticleItem> articles, final LayoutInflater inflater, Activity activity) {
        return new ArrayAdapter<>(activity,R.layout.adapter_shop, articles) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {
                View view = inflater.inflate(R.layout.adapter_shop, null);
                ArticleItem currentItem = getItem(position);

                int id = currentItem.getId();
                String name = currentItem.getName();
                double price = currentItem.getPrice();
                int quantity = currentItem.getQuantity();
                String string_price = price + "€";

                TextView friendNameView = view.findViewById(R.id.item_name);
                friendNameView.setText(name);

                TextView item_quantity = view.findViewById(R.id.item_quantity);
                item_quantity.setText(String.valueOf(quantity));

                TextView item_price = view.findViewById(R.id.item_price);
                item_price.setText(string_price);

                Button button_buy = view.findViewById(R.id.buy_item);
                button_buy.setOnClickListener(v -> ((ShopActivity)activity).requestPayment(id , quantity, String.valueOf(price)));
                return view;
            }
        };
    }

    private static JSONObject getTransactionInfo(String price) throws JSONException {
        JSONObject transactionInfo = new JSONObject();
        transactionInfo.put("totalPrice", price);
        transactionInfo.put("totalPriceStatus", "FINAL");
        transactionInfo.put("countryCode", "FR");
        transactionInfo.put("currencyCode", "EUR");
        transactionInfo.put("checkoutOption", "COMPLETE_IMMEDIATE_PURCHASE");

        return transactionInfo;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public  Optional<JSONObject> getPaymentDataRequest(String price) {
        try {
            JSONObject paymentDataRequest = getBaseRequest();
            paymentDataRequest.put(
                    "allowedPaymentMethods", new JSONArray().put(getCardPaymentMethod()));
            paymentDataRequest.put("transactionInfo", getTransactionInfo(price));
            paymentDataRequest.put("merchantInfo", getMerchantInfo());

            paymentDataRequest.put("shippingAddressRequired", true);

            JSONObject shippingAddressParameters = new JSONObject();
            shippingAddressParameters.put("phoneNumberRequired", false);

            JSONArray allowedCountryCodes = new JSONArray().put("FR");

            shippingAddressParameters.put("allowedCountryCodes", allowedCountryCodes);
            paymentDataRequest.put("shippingAddressParameters", shippingAddressParameters);
            return Optional.of(paymentDataRequest);

        } catch (JSONException e) {
            return Optional.empty();
        }
    }

    public void handlePaymentSuccess(Activity activity, PaymentData paymentData, int id, int quantity) {
        Request.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                Toast.makeText(activity, "Achat effectué !", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Exception exception) {
                Toast.makeText(activity, "Achat refusé !!", Toast.LENGTH_SHORT).show();
            }
        }, activity, MenuActivity.JEUBEUB_API + "/shop/pushItemPlayer?playerId=" + LoginActivity.USER_TOKEN + "&itemId=" + id + "&itemQuantity="+quantity, null);
            //TODO CONSTANTE

                                                                                                                                                        //        } catch (JSONException e) {
                                                                                                                                                        //            throw new RuntimeException("The selected garment cannot be parsed from the list of elements")//        }
    }

    public  PaymentsClient createPaymentsClient(Activity activity) {
        Wallet.WalletOptions walletOptions =
                new Wallet.WalletOptions.Builder().setEnvironment(WalletConstants.ENVIRONMENT_TEST).build();
        return Wallet.getPaymentsClient(activity, walletOptions);
    }

    private  JSONObject getMerchantInfo() throws JSONException {
        return new JSONObject().put("merchantName", "Example Merchant");
    }

    private static JSONObject getBaseRequest() throws JSONException {
        return new JSONObject().put("apiVersion", 2).put("apiVersionMinor", 0);
    }

    private static JSONObject getGatewayTokenizationSpecification() throws JSONException {
        return new JSONObject() {{
            put("type", "PAYMENT_GATEWAY");
            put("parameters", new JSONObject() {{
                put("gateway", "example");
                put("gatewayMerchantId", "exampleGatewayMerchantId");
            }});
        }};
    }

    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("AMEX")
                .put("DISCOVER")
                .put("INTERAC")
                .put("JCB")
                .put("MASTERCARD")
                .put("VISA");
    }

    private static JSONArray getAllowedCardAuthMethods() {
        return new JSONArray()
                .put("PAN_ONLY")
                .put("CRYPTOGRAM_3DS");
    }

    private static JSONObject getBaseCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = new JSONObject();
        cardPaymentMethod.put("type", "CARD");
        JSONObject parameters = new JSONObject();
        parameters.put("allowedAuthMethods", getAllowedCardAuthMethods());
        parameters.put("allowedCardNetworks", getAllowedCardNetworks());
        parameters.put("billingAddressRequired", true);
        JSONObject billingAddressParameters = new JSONObject();
        billingAddressParameters.put("format", "FULL");
        parameters.put("billingAddressParameters", billingAddressParameters);
        cardPaymentMethod.put("parameters", parameters);
        return cardPaymentMethod;
    }

    private static JSONObject getCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = getBaseCardPaymentMethod();
        cardPaymentMethod.put("tokenizationSpecification", getGatewayTokenizationSpecification());
        return cardPaymentMethod;
    }

    public void getShopItem(ListView articleListView, LayoutInflater layoutInflater, Activity activity) {
        List<ArticleItem> list = new ArrayList<>();
        Request.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    JSONArray data = json.getJSONArray("data");
                    for(int i =0; i < data.length(); i++){
                        JSONObject c = data.getJSONObject(i);
                        list.add(new ArticleItem(c.getInt("id"), c.getString("name"),c.getDouble("price") ,c.getInt("quantity") ));
                    }
                    articleListView.setAdapter(getAdapter(list,layoutInflater, activity));
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
            @Override
            public void onError(Exception exception) {
                System.err.println(exception.getMessage());
            }
        }, activity, MenuActivity.JEUBEUB_API + "/shop/displayItems", null);
    }
}
