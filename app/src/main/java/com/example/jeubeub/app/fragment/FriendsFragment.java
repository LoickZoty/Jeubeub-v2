package com.example.jeubeub.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.PlatformVpnProfile;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.adapter.FriendItemAdapter;
import com.example.jeubeub.app.service.FriendService;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;


public class FriendsFragment extends Fragment {

    private FriendService friendService;
    private ListView friendListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.friendService = new FriendService(getActivity(), getContext());
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        this.friendListView = view.findViewById(R.id.friend_listView);
        this.friendService.loadFriend(list -> {
            ArrayAdapter<Player> adapter = getAdapter(list, inflater);
            friendListView.setAdapter(adapter);
            //friendListView.setAdapter(new FriendItemAdapter(activity, getContext(), list));
        });
        return view;
    }

    @NonNull
    private ArrayAdapter<Player> getAdapter(List<Player> players, final LayoutInflater inflater) {
        return new ArrayAdapter<Player>(getActivity(), R.layout.adapter_friend, players) {
            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {
                View rowView = inflater.inflate(R.layout.adapter_friend, viewGroup, /* attachToRoot= */ false);
                final Player player = getItem(position);
                TextView textView = rowView.findViewById(R.id.friend_name);
                textView.setText(player.getDisplayName());
                ImageButton showProfileButton = rowView.findViewById(R.id.show_profile);
                showProfileButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                        LoginActivity.getPlayersClient()
                                        .getCompareProfileIntentWithAlternativeNameHints(
                                                player.getPlayerId(), player.getDisplayName(), LoginActivity.getDisplayName())
                                        .addOnSuccessListener(
                                                new OnSuccessListener<Intent>() {
                                                    @Override
                                                    public void onSuccess(Intent intent) {
                                                        registerForActivityResult(
                                                                new ActivityResultContracts.StartActivityForResult(),
                                                                new ActivityResultCallback<ActivityResult>() {
                                                                    @Override
                                                                    public void onActivityResult(ActivityResult result) {
                                                                        if (result.getResultCode() == Activity.RESULT_OK) {
                                                                            System.out.println("??????????");
                                                                        }
                                                                    }
                                                                }).launch(intent);
                                                    }
                                                });
                            }
                        });
                return rowView;
            }
        };
    }
}

