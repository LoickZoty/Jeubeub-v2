package com.example.jeubeub.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jeubeub.R;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.friends.fragment.FriendsFragment;
import com.example.jeubeub.app.games.fragment.QueueFragment;
import com.example.jeubeub.app.inventory.fragment.InventoryFragment;
import com.example.jeubeub.app.leaderboard.fragment.LeaderboardFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {
    public final static String JEUBEUB_API = "http://149.202.40.246:8786/Jeubeub/api/v1"; //A mettre dans une classe plus adequate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_bar);
        bottomNav.setOnItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.games_button); //Menu par defaut

        /*
        LoginActivity.getPlayersClient().getCurrentPlayerId().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(@NonNull String response) {
                System.out.println(response);
                Toast.makeText(MenuActivity.this, LoginActivity.USER_TOKEN, Toast.LENGTH_SHORT).show();
                LoginActivity.USER_TOKEN = response;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.err.println(e.getMessage());
                Toast.makeText(MenuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
         */
    }


    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnItemSelectedListener navListener =
        item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.leaderbord_button:
                    selectedFragment = new LeaderboardFragment();
                    break;
                case R.id.games_button:
                    selectedFragment = new QueueFragment();
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
                            task -> {
                                LoginActivity.reconnect = true;
                                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                            }));
                    break;
            }

            if (selectedFragment != null)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        };
}
