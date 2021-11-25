package com.example.jeubeub.app.leaderboard.service;

import android.app.Activity;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.leaderboard.service.getScoreCallBack;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;

import java.util.Objects;

public class LeaderboardService {

    public static final String MORPION_VICTOIRE = "CgkI3rSPs44MEAIQAg";
    public static final String MORPION_DEFAITE = "CgkI3rSPs44MEAIQAw";

    public static void submitNewScore(int score, String idLeaderboard, Activity activity){
        getScoreLeaderboard(idLeaderboard, activity, currentScore -> {
            LoginActivity.getLeaderboardsClient().submitScore(idLeaderboard, currentScore + score);
        });
    }

    private static void getScoreLeaderboard(String idLeaderboard, Activity activity, getScoreCallBack callBack){
        LoginActivity.getLeaderboardsClient().loadCurrentPlayerLeaderboardScore(idLeaderboard, LeaderboardVariant.TIME_SPAN_ALL_TIME, LeaderboardVariant.COLLECTION_FRIENDS)
        .addOnSuccessListener(activity, leaderboardScoreAnnotatedData -> {
            if (leaderboardScoreAnnotatedData != null) {
                if (leaderboardScoreAnnotatedData.get() != null) {
                    long currentScore = Objects.requireNonNull(leaderboardScoreAnnotatedData.get()).getRawScore();
                    callBack.onSuccess((int) currentScore);
                } else {
                    callBack.onSuccess(0);
                }
            }
        }).addOnFailureListener(e -> System.out.println("Failure"));
    }
}
