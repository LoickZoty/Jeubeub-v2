package com.example.jeubeub.app.service;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;

import java.util.ArrayList;
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

        Games.getPlayersClient(this.activity, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this.context))).loadFriends(10, false)
            .addOnSuccessListener( data -> {
                PlayerBuffer playerBuffer = data.get();
                ArrayList<Player> players = new ArrayList<>();
                for (int i = 0; i < Objects.requireNonNull(playerBuffer).getCount(); i++) {
                    players.add(playerBuffer.get(i).freeze());
                }
                loadFriendCallBack.onLoad(players);
            });
    }
}

