package com.example.jeubeub.app.fragment;

import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.FriendsResolutionRequiredException;
import com.google.android.gms.games.Games;

import java.util.Objects;


public class FriendsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFriend();
    }

    private void loadFriend() {
        Games.getPlayersClient(getActivity(), Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(getContext()))).loadFriends(10, false)
                .addOnCompleteListener(data -> {
                    Toast.makeText(getActivity(), "FRIENDS LOAD...", Toast.LENGTH_SHORT).show();
                    //A cet endroit on falsifie la réponse de l'api car nous avons une erreur que
                    // nous n'arrivons pas à résoudre

                }).addOnFailureListener(exception -> {
            if (exception instanceof FriendsResolutionRequiredException) {
                PendingIntent pendingIntent = ((FriendsResolutionRequiredException) exception).getResolution();
                try {
                    this.startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0, null);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
