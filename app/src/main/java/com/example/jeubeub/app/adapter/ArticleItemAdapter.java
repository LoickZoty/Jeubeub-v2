package com.example.jeubeub.app.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.ShopActivity;
import com.example.jeubeub.app.model.ArticleItem;
import com.example.jeubeub.app.service.ShopService;

import java.util.List;

public class ArticleItemAdapter extends BaseAdapter {

    private Context context;
    private List<ArticleItem> articleItemList;
    private LayoutInflater inflater;

    public ArticleItemAdapter(Context context, List<ArticleItem> articleItemList)
    {
        this.context = context;
        this.articleItemList = articleItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return articleItemList.size();
    }

    @Override
    public ArticleItem getItem(int position) {
        return articleItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_shop, null);
        ArticleItem currentItem = getItem(position);

        int id = currentItem.getId();
        String name = currentItem.getName();
        double price = currentItem.getPrice();
        int quantity = currentItem.getQuantity();
        String string_price = price + "€";

        TextView friendNameView = view.findViewById(R.id.item_name);
        friendNameView.setText(name);

        TextView item_price = view.findViewById(R.id.item_price);
        item_price.setText(string_price);

        Button button_buy = view.findViewById(R.id.buy_item);
        button_buy.setOnClickListener(v -> ((ShopActivity)context).requestPayment(id , quantity, String.valueOf(price)));

        return view;
    }
}
