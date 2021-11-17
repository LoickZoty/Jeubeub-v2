package com.example.jeubeub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jeubeub.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.games.FriendsResolutionRequiredException;
import com.google.android.gms.games.Games;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    Button signOutButton;
    Button contactButton;
    Button shopButton;
    private GoogleSignInClient googleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setSignOutButtonEvent();
        setContactButtonEvent();
        setShopButtonEvent();
    }

    private void setShopButtonEvent() {
        shopButton = findViewById(R.id.shop_button);
        shopButton.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, ShopActivity.class)));
    }

    private void setSignOutButtonEvent() {
        signOutButton = findViewById(R.id.sign_out_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        googleClient = GoogleSignIn.getClient(this, gso);

        signOutButton.setOnClickListener(view -> googleClient.signOut().addOnCompleteListener(this,
                task -> {
                    startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                }));
    }

    private void setContactButtonEvent() {
        contactButton = findViewById(R.id.contact_button);
        contactButton.setOnClickListener(view -> loadFriend());
    }

    private void loadFriend(){
        Games.getPlayersClient(this, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this))).loadFriends(10,false)
                .addOnCompleteListener(data -> {
                    //A cet endroit on falsifie la réponse de l'api car nous avons une erreur que nous n'arrivons pas à résoudre dans un laps de temps raisonnable pour finir le projet à temps
                    //Donc on peut lancer une activity qui mene sur une page avec des amis falsifiés
                }).addOnFailureListener(exception -> {
            if (exception instanceof FriendsResolutionRequiredException) {
                PendingIntent pendingIntent = ((FriendsResolutionRequiredException)exception).getResolution();
                try {
                    Toast.makeText(this,"startIntentSenderForResult", Toast.LENGTH_LONG).show();
                    this.startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0, null);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
