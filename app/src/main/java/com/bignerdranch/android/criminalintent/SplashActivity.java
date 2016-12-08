package com.bignerdranch.android.criminalintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;



/**
 * Created by NoLimitProduction on 12/7/16.
 */

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 8000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(this,StoryListFragment.class);
        startActivity(intent);
        finish();
    }
}
