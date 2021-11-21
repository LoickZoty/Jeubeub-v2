package com.example.jeubeub.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jeubeub.R;
import com.example.jeubeub.app.model.InventoryItem;

import java.util.List;

public class InventoryItemAdapter extends BaseAdapter {

    private List<InventoryItem> inventoryItems;
    private LayoutInflater inflater;

    public InventoryItemAdapter(Context context, List<InventoryItem> inventoryItems)
    {
        this.inventoryItems = inventoryItems;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return inventoryItems.size();
    }

    @Override
    public InventoryItem getItem(int position) {
        return inventoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
}
