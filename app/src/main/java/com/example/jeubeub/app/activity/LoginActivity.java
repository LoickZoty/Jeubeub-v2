package com.example.jeubeub.app.activity;

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
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    public final static String JEUBEUB_API = "http://149.202.40.246:8786/Jeubeub/api/v1"; //A mettre dans une classe plus adequate
    public static int USER_TOKEN = 1; //Pour le test

    private static final int LOGIN_SUCCESS = 1;

    private static GoogleSignInClient googleClient;
    private static LeaderboardsClient leaderboardsClient;
    private static PlayersClient playersClient;
    private static String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();

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
            playersClient = Games.getPlayersClient(this, account);
            leaderboardsClient = Games.getLeaderboardsClient(this, account);
            displayName = account.getDisplayName();
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
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
            playersClient = Games.getPlayersClient(this, account);
            leaderboardsClient = Games.getLeaderboardsClient(this, account);
            displayName = account.getDisplayName();
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
        } catch (ApiException e) {
            System.out.println("signInResult:failed code=" + e.getStatusCode());
        }
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