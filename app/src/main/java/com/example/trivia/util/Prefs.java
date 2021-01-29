package com.example.trivia.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    public static final String HIGHEST_SCORE = "highest_score";
    public static final String LAST_INDEX = "last_index";
    private SharedPreferences preferences;

    public Prefs(Activity context) {
        this.preferences = context.getPreferences(Context.MODE_PRIVATE);
    }

    public void saveHighestScore(int score) {
        int currentScore = score;

        int lastScore = preferences.getInt(HIGHEST_SCORE, 0);
        if (currentScore > lastScore) {
            preferences.edit().putInt(HIGHEST_SCORE, currentScore).apply();
        }
    }

    public int getHighestScore() {
        return preferences.getInt(HIGHEST_SCORE, 0);
    }

    public void setState(int index) {
      preferences.edit().putInt(LAST_INDEX, index).apply();
    }

    public int getState() {
        return preferences.getInt(LAST_INDEX, 0);
    }

}
