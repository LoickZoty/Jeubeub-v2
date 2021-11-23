package com.example.jeubeub.app.leaderboard.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.jeubeub.R;
import com.example.jeubeub.app.leaderboard.activity.MorpionLeaderboardActivity;

public class LeaderboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        view.findViewById(R.id.morpion_leadernoard_button).setOnClickListener(
                v -> startActivity(new Intent(requireActivity(), MorpionLeaderboardActivity.class)));
        return view;
    }
}

