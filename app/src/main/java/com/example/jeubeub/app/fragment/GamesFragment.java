package com.example.jeubeub.app.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.MorpionActivity;


public class GamesFragment extends Fragment {

    Button morpion_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, container, false);
        morpion_button = view.findViewById(R.id.morpion_button);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart() {
        super.onStart();
        morpion_button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MorpionActivity.class);
            startActivity(intent);
        });
    }
}
