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
import com.example.jeubeub.app.service.FriendService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.FriendsResolutionRequiredException;
import com.google.android.gms.games.Games;

import java.util.Objects;


public class FriendsFragment extends Fragment {

    private FriendService friendService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.friendService = new FriendService(getActivity(), getContext());
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.friendService.loadFriend();
    }

}

