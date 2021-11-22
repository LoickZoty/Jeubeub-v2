package com.example.jeubeub.app.fragment;

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
import com.example.jeubeub.app.activity.ShopActivity;
import com.example.jeubeub.app.adapter.InventoryItemAdapter;
import com.example.jeubeub.app.service.InventoryService;


public class InventoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        InventoryService inventoryService = new InventoryService();
        view.findViewById(R.id.shop_button).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), ShopActivity.class))
                );
        ListView inventoryListView =   view.findViewById(R.id.inventory_listView);
        //TODO changer id par LoginActivity.getGoogleSignInAccount().getId()
        inventoryListView.setAdapter(new InventoryItemAdapter(getContext(), inventoryService.getInventoryPlayer("154")));
        return view;
    }
}
