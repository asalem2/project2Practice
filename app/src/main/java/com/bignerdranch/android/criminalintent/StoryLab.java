package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StoryLab {
    private static StoryLab sStoryLab;

    private ArrayList<Story> mStories;

    public static StoryLab get(Context context) {
        if (sStoryLab == null) {
            sStoryLab = new StoryLab(context);
        }
        return sStoryLab;
    }

    private StoryLab(Context context) {
        mStories = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Story story = new Story();
            story.setTitle("Story #" + i);
            story.setSolved(i % 2 == 0);
            mStories.add(story);
        }
    }

    public List<Story> getStories() {
        return mStories;
    }

    public ArrayList<Story> searchStory(String s){
        ArrayList<Story> searchList = new ArrayList<>();
        for(int i=0; i<mStories.size(); i++){
            if(mStories.get(i).getTitle().contains(s)){
                searchList.add(mStories.get(i));
            }
        }
        return searchList;
    }
    public Story getStory(UUID id) {
        for (Story story : mStories) {
            if (story.getId().equals(id)) {
                return story;
            }
        }
        return null;
    }

}
