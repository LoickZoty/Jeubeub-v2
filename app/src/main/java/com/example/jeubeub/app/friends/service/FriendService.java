package com.example.jeubeub.app.friends.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendService {

    private Activity activity;
    private Context context;

    public FriendService(Activity activity, Context context){
        this.activity = activity;
        this.context =context;
    }

    public void loadFriend(loadFriendCallBack loadFriendCallBack) {
        GoogleSignIn.requestPermissions(this.activity, 1, GoogleSignIn.getLastSignedInAccount(this.context), Games.GamesOptions.builder().build());
        Games.getPlayersClient(this.activity, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this.context))).loadFriends(100, false)
            .addOnSuccessListener( data -> {
                PlayerBuffer playerBuffer = data.get();
                ArrayList<Player> players = new ArrayList<>();
                for (int i = 0; i < Objects.requireNonNull(playerBuffer).getCount(); i++) {
                    players.add(playerBuffer.get(i).freeze());
                }
                loadFriendCallBack.onLoad(players);
            });
    }

    @NonNull
    public ArrayAdapter<Player> getAdapter(List<Player> players, final LayoutInflater inflater, ActivityResultLauncher<Intent> resultLauncher) {
        return new ArrayAdapter<>(this.activity, R.layout.adapter_friend, players) {
            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {
                @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.adapter_friend, viewGroup, false);
                final Player player = getItem(position);
                TextView textView = rowView.findViewById(R.id.friend_name);
                textView.setText(player.getDisplayName());
                Button buttonInvite = rowView.findViewById(R.id.invite_friend_id);
                buttonInvite.setTag(player.getPlayerId());
                buttonInvite.setOnClickListener(v -> {
                    //Quand on est ici c'est quand on clique sur le bouton pour inviter un ami a jouer
                    //buttonInvite.getTag() permet de recup l'id du joueur que l'on veut inviter
                });
                Button showProfileButton = rowView.findViewById(R.id.show_profile);
                //TODO CONSTANTE
                showProfileButton.setOnClickListener(v -> LoginActivity.getPlayersClient()
                        .getCompareProfileIntentWithAlternativeNameHints(
                                player.getPlayerId(), player.getDisplayName(), LoginActivity.getDisplayName())
                        .addOnSuccessListener(resultLauncher::launch));
                return rowView;
            }
        };
    }
}
