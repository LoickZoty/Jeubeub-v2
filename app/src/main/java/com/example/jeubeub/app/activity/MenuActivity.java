package com.example.jeubeub.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import com.example.jeubeub.R;
import com.example.jeubeub.app.fragment.FriendsFragment;
import com.example.jeubeub.app.fragment.GamesFragment;
import com.example.jeubeub.app.fragment.LeaderboardFragment;
import com.example.jeubeub.app.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_bar);
        bottomNav.setOnItemSelectedListener(navListener);
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch(item.getItemId()){
                    case R.id.leaderbord_button:
                        selectedFragment = new LeaderboardFragment();
                        break;
                    case R.id.games_button:
                        selectedFragment = new GamesFragment();
                        break;
                    case R.id.friends_button:
                        selectedFragment = new FriendsFragment();
                        break;
                    case R.id.shop_button:
                        selectedFragment = new ShopFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };
}
