package com.example.jeubeub.app.fragment;

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
import com.example.jeubeub.app.activity.LoginActivity;
import com.example.jeubeub.app.service.FriendService;
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
        this.friendListView = view.findViewById(R.id.friend_listView);

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {});

        friendService.loadFriend(list -> {
            ArrayAdapter<Player> adapter = getAdapter(list, inflater);
            friendListView.setAdapter(adapter);
        });
        return view;
    }

    @NonNull
    private ArrayAdapter<Player> getAdapter(List<Player> players, final LayoutInflater inflater) {
        return new ArrayAdapter<>(getActivity(), R.layout.adapter_friend, players) {
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
                showProfileButton.setOnClickListener(v -> LoginActivity.getPlayersClient()
                        .getCompareProfileIntentWithAlternativeNameHints(
                                player.getPlayerId(), player.getDisplayName(), LoginActivity.getDisplayName())
                        .addOnSuccessListener(intent -> resultLauncher.launch(intent)));
                return rowView;
            }
        };
    }
}

