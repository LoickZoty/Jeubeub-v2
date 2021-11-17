package com.example.jeubeub.app.service;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentSender;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.FriendsResolutionRequiredException;
import com.google.android.gms.games.Games;

import java.util.Objects;

public class FriendService {

    private Activity activity;
    private Context context;

    public FriendService(Activity activity, Context context){
        this.activity = activity;
        this.context =context;
    }


    public void loadFriend() {
        Games.getPlayersClient(this.activity, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this.context))).loadFriends(10, false)
                .addOnCompleteListener(data -> {
                    Toast.makeText(this.activity, "FRIENDS LOAD...", Toast.LENGTH_SHORT).show();
                    //A cet endroit on falsifie la réponse de l'api car nous avons une erreur que
                    // nous n'arrivons pas à résoudre

                });
    }

}
