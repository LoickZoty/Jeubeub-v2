package com.example.jeubeub.app.fragment;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.FriendsResolutionRequiredException;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Objects;


public class LeaderboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        showLeaderboard(requireActivity(), requireContext());
        return view;
    }

    private void showLeaderboard(Activity activity, Context context) {
        Games.getLeaderboardsClient(activity, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(context)))
                .getLeaderboardIntent("CgkI3rSPs44MEAIQAg")
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, 9004);
                    }
                }).addOnFailureListener(e -> System.out.println(e.getMessage()));
    }
}

