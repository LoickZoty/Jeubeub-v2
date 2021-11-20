package com.example.jeubeub.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.model.FriendItem;
import com.google.android.gms.games.Player;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class FriendItemAdapter extends BaseAdapter {

    private Activity activity;
    private Context context;
    private List<Player> friendItemList;
    private LayoutInflater inflater;

    public FriendItemAdapter(Activity activity, Context context, List<Player> friendItemList)
    {
        this.context = context;
        this.friendItemList = friendItemList;
        this.inflater = LayoutInflater.from(context);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return friendItemList.size();
    }

    @Override
    public Player getItem(int position) {
        return friendItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_friend, null);
        Player player = getItem(position);

        String name = player.getDisplayName();
        String id = player.getPlayerId();

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

        Button showProfile = view.findViewById(R.id.show_profile);
        showProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                }
            });

        return view;
    }
}
