package com.example.jeubeub.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.example.jeubeub.app.adapter.FriendItemAdapter;
import com.example.jeubeub.app.service.FriendService;


public class FriendsFragment extends Fragment {

    private FriendService friendService;
    private ListView friendListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.friendService = new FriendService(getActivity(), getContext());
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        this.friendListView = view.findViewById(R.id.friend_listView);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.friendService.loadFriend(list -> {
            friendListView.setAdapter(new FriendItemAdapter(getContext(), list));
        });
    }
}

