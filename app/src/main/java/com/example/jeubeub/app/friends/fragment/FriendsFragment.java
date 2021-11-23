package com.example.jeubeub.app.friends.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.friends.service.FriendService;
import com.google.android.gms.games.Player;

import java.util.List;

public class FriendsFragment extends Fragment {

    private ListView friendListView;
    private ActivityResultLauncher<Intent> resultLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FriendService friendService = new FriendService(getActivity(), getContext());
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {});

        friendListView = view.findViewById(R.id.friend_listView);

        friendService.loadFriend(list -> friendListView.setAdapter(friendService.getAdapter(list, inflater, resultLauncher)));
        return view;
    }

}

