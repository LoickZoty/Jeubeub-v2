package com.example.jeubeub.app.inventory.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.inventory.service.InventoryService;
import com.example.jeubeub.app.inventory.shop.activity.ShopActivity;


public class InventoryFragment extends Fragment {

    private ListView inventoryListView;
    private InventoryService inventoryService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        inventoryService = new InventoryService(getActivity());
        view.findViewById(R.id.shop_button).setOnClickListener(v -> startActivity(new Intent(getActivity(), ShopActivity.class)));
        inventoryListView =  view.findViewById(R.id.inventory_listView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        inventoryService.renderInventoryPlayer(LoginActivity.USER_TOKEN, inventoryListView, getLayoutInflater());
    }
}
