package com.example.jeubeub.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.jeubeub.R;
import com.example.jeubeub.app.activity.gameActivity.QueueActivity;
import com.example.jeubeub.app.friends.fragment.FriendsFragment;
import com.example.jeubeub.app.inventory.fragment.InventoryFragment;
import com.example.jeubeub.app.leaderboard.fragment.LeaderboardFragment;
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
                        findViewById(R.id.sign_out_button).setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, QueueActivity.class)));
                        break;
                    case R.id.friends_button:
                        selectedFragment = new FriendsFragment();
                        break;
                    case R.id.inventory_button:
                        selectedFragment = new InventoryFragment();
                        break;
                    case R.id.sign_out_button:
                        //TODO CONSTANTE
                        findViewById(R.id.sign_out_button).setOnClickListener(view -> LoginActivity.getGoogleSignInClient().signOut().addOnCompleteListener(this,
                            task -> startActivity(new Intent(MenuActivity.this, LoginActivity.class))));
                        break;
                }

                if(selectedFragment != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };
}