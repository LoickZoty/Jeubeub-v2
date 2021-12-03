package com.example.jeubeub.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jeubeub.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    public static String USER_TOKEN;

    private static final int LOGIN_SUCCESS = 1;

    private static GoogleSignInClient googleClient;
    public static GoogleSignInOptions gso;
    private static LeaderboardsClient leaderboardsClient;
    private static PlayersClient playersClient;
    private static String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();

        googleClient = GoogleSignIn.getClient(this,gso);

        VideoView videoLogin = findViewById(R.id.video_login);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.jeubeub;
        Uri uri = Uri.parse(path);
        videoLogin.setVideoURI(uri);
        videoLogin.setOnPreparedListener(mp -> {
            videoLogin.start();
            mp.setLooping(true);
        });

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(view -> {
            Intent signIntent = googleClient.getSignInIntent();
            startActivityForResult(signIntent, LOGIN_SUCCESS);
        });
    }

    @Override
    protected void onStart () {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            Toast.makeText(this,"Bonjour " + account.getGivenName(), Toast.LENGTH_SHORT).show();
            connect(account);
        }
    }

    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_SUCCESS) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult (Task< GoogleSignInAccount > completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            connect(account);
        } catch (ApiException e) {
            System.out.println("signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void connect(GoogleSignInAccount account) {
        playersClient = Games.getPlayersClient(this, account);
        leaderboardsClient = Games.getLeaderboardsClient(this, account);
        displayName = account.getDisplayName();

        getPlayersClient().getCurrentPlayerId().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(@NonNull String response) {
                System.out.println(response);
                Toast.makeText(LoginActivity.this, USER_TOKEN, Toast.LENGTH_SHORT).show();
                USER_TOKEN = response;
                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.err.println(e.getMessage());
                Toast.makeText(LoginActivity.this, "ERROR : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static PlayersClient getPlayersClient(){
        return playersClient;
    }

    public static LeaderboardsClient getLeaderboardsClient(){
        return leaderboardsClient;
    }

    public static String getDisplayName() {
        return displayName;
    }

    public static GoogleSignInClient getGoogleSignInClient(){return googleClient;}
}