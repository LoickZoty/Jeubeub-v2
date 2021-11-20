package com.example.jeubeub.app.service;

import com.example.jeubeub.app.model.FriendItem;
import com.google.android.gms.games.Player;

import java.util.List;

public interface loadFriendCallBack {
    void onLoad(List<Player> list);
}
