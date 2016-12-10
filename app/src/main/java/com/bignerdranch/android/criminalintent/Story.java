package com.bignerdranch.android.criminalintent;

import android.graphics.drawable.Drawable;

import java.util.Date;
import java.util.UUID;

public class Story {



    private UUID mId;
    private String mTitle;
    private String mDescr;
    private boolean mSolved;
    private Drawable mImage;


    public Story() {
        mId = UUID.randomUUID();
        //Change text to blank space
        setmDescr("Here is Description ..");
    }

    public UUID getId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String title) {
        mTitle = title;
    }

    public String getmDescr() {
        return mDescr;
    }

    public void setmDescr(String mDescr) {
        this.mDescr = mDescr;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public void setmImage(Drawable mImage) {
        this.mImage = mImage;
    }
}
