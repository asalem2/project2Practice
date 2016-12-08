package com.bignerdranch.android.criminalintent;


import android.app.Application;
import android.content.Context;

import android.os.SystemClock;

import java.util.concurrent.TimeUnit;


/**
 * Created by NoLimitProduction on 11/30/16.
 */

public class App extends Application {
/*    @Override
    public void onCreate() {
        super.onCreate();*/

        private static App instance;


        public static Context getContext(){
            return instance.getApplicationContext();
        }

        public void onCreate() {
            instance = this;
            super.onCreate();
        }

        // Don't do this! This is just so cold launches take some time
/*
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
*/
    }
