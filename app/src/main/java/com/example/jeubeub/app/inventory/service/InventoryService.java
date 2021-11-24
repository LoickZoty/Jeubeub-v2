package com.example.jeubeub.app.inventory.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.MenuActivity;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.inventory.model.InventoryItem;
import com.example.jeubeub.app.inventory.shop.model.ArticleItem;
import com.google.android.gms.games.Player;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class InventoryService {

    private Activity activity;
    public InventoryService(Activity activity){
        this.activity = activity;
    }

    public void renderInventoryPlayer(String id, ListView inventoryListView, LayoutInflater inflater) {
        List<InventoryItem> list = new ArrayList<>();
        Request.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    JSONArray data = json.getJSONArray("data");
                    for(int i =0; i < data.length(); i++){
                        JSONObject c = data.getJSONObject(i);
                        list.add(new InventoryItem(c.getString("name"), c.getInt("quantity")));
                        inventoryListView.setAdapter(getAdapter(list,inflater));
                    }
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
            @Override
            public void onError(Exception exception) {
                System.err.println(exception.getMessage());
            }
        }, activity, MenuActivity.JEUBEUB_API + "/shop/displayItemsPlayer?playerId=" + id, null);
    }

    private ArrayAdapter<InventoryItem> getAdapter(List<InventoryItem> articles, final LayoutInflater inflater) {
        return new ArrayAdapter<>(this.activity, R.layout.adapter_inventory, articles) {
            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {
                View view = inflater.inflate(R.layout.adapter_inventory, null);
                InventoryItem currentItem = getItem(position);
                String name = currentItem.getName();
                String quantity = String.valueOf(currentItem.getQuantity());
                TextView item_name = view.findViewById(R.id.inventory_name);
                item_name.setText(name);
                TextView item_quantity = view.findViewById(R.id.inventory_quantity);
                item_quantity.setText(quantity);
                return view;
            }
        };
    }


}
