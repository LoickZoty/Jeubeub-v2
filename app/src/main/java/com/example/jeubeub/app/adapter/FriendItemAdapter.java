package com.example.jeubeub.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.jeubeub.R;
import com.example.jeubeub.app.model.FriendItem;

import java.util.List;

public class FriendItemAdapter extends BaseAdapter {

    private Context context;
    private List<FriendItem> friendItemList;
    private LayoutInflater inflater;

    public FriendItemAdapter(Context context, List<FriendItem> friendItemList)
    {
        this.context = context;
        this.friendItemList = friendItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return friendItemList.size();
    }

    @Override
    public FriendItem getItem(int position) {
        return friendItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_friend, null);
        FriendItem currentItem = getItem(position);

        String name = currentItem.getName();
        int id = currentItem.getId();

        TextView friendNameView = view.findViewById(R.id.friend_name);
        friendNameView.setText(name);

        Button buttonInvite = view.findViewById(R.id.invite_friend_id);
        buttonInvite.setTag(id);
        buttonInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Quand on est ici c'est quand on clique sur le bouton pour inviter un ami a jouer
                //buttonInvite.getTag() permet de recup l'id du joueur que l'on veut inviter
            }
        });

        return view;
    }
}
