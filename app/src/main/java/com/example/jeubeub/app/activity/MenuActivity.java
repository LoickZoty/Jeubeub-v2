package com.example.jeubeub.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.jeubeub.R;
import com.example.jeubeub.app.fragment.FriendsFragment;
import com.example.jeubeub.app.fragment.GamesFragment;
import com.example.jeubeub.app.fragment.LeaderboardFragment;
import com.example.jeubeub.app.fragment.ShopFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuActivity extends AppCompatActivity {

    Button signOutButton;
    Button contactButton;
    Button shopButton;
    private GoogleSignInClient googleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_bar);
        bottomNav.setOnItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnItemSelectedListener navListener =
            new NavigationBarView.OnItemSelectedListener(){
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                }
            };

//    private void setSignOutButtonEvent() {
//        signOutButton = findViewById(R.id.sign_out_button);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .build();
//        googleClient = GoogleSignIn.getClient(this, gso);
//
//        signOutButton.setOnClickListener(view -> googleClient.signOut().addOnCompleteListener(this,
//                task -> {
//                    startActivity(new Intent(MenuActivity.this, LoginActivity.class));
//                }));
//    }
}
