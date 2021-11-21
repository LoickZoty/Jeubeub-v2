package com.example.jeubeub.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.example.jeubeub.app.adapter.InventoryItemAdapter;
import com.example.jeubeub.app.service.InventoryService;


public class InventoryFragment extends Fragment {

    private InventoryService inventoryService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        inventoryService = new InventoryService();

        view.findViewById(R.id.shop_button).setOnClickListener(v ->
                getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ShopFragment()).commit());

        ListView inventoryListView =   view.findViewById(R.id.inventory_listView);
        inventoryListView.setAdapter(new InventoryItemAdapter(getContext(), inventoryService.getInventoryPlayer("154")));

        return view;
    }

}
