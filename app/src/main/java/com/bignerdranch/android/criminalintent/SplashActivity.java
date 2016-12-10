package com.bignerdranch.android.criminalintent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;



/**
 * Created by NoLimitProduction on 12/7/16.
 */

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent intent = new Intent(SplashActivity.this, StoryListActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
