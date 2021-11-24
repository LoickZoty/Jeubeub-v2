package com.example.jeubeub.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.DefaultRetryPolicy;
import com.example.jeubeub.R;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.games.activity.QueueActivity;
import com.example.jeubeub.app.friends.fragment.FriendsFragment;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.games.popup.CallGamePopup;
import com.example.jeubeub.app.games.popup.EndGamePopup;
import com.example.jeubeub.app.inventory.fragment.InventoryFragment;
import com.example.jeubeub.app.leaderboard.fragment.LeaderboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class MenuActivity extends AppCompatActivity {
    public final static String JEUBEUB_API = "http://149.202.40.246:8786/Jeubeub/api/v1"; //A mettre dans une classe plus adequate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_bar);
        bottomNav.setOnItemSelectedListener(navListener);

        waitCallGameRequest();
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
                        System.out.println("????");
                        startActivity(new Intent(MenuActivity.this, QueueActivity.class));
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

    private void waitCallGameRequest() {
        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(
                300000,
                1,
                1
        );
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Request.getRequest(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject json) {
                        System.out.println("le json du callGame : "+json);
                        try {
                            CallGamePopup callGamePopup = new CallGamePopup(MenuActivity.this, json);
                            callGamePopup.show();
                            run();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Exception exception) {
                        System.err.println(exception);
                    }
                }, MenuActivity.this, JEUBEUB_API + "/friend/waitCallGame?playerId=" + LoginActivity.USER_TOKEN, retryPolicy);
            }
        });
        t1.start();
    }
}
