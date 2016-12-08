package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Story {

    private UUID mId;
    private String mTitle;
    private String mDescr;
    private boolean mSolved;

    public Story() {
        mId = UUID.randomUUID();
        //Change text to blank space
        setmDescr("Here is Description ..");
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
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
}
