package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

public class StoryListActivity extends StoryFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new StoryListFragment();
    }
}
