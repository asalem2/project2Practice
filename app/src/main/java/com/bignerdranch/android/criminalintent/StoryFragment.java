package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class StoryFragment extends Fragment {

    private static final String ARG_STORY_ID = "story_id";

    private Story mStory;
    private TextView mTitleField;
    private TextView mDescription;
    private ImageView mPicture;
//    private CheckBox mSolvedCheckBox;

    public static StoryFragment newInstance(UUID storyId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORY_ID, storyId);

        StoryFragment fragment = new StoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID storyId = (UUID) getArguments().getSerializable(ARG_STORY_ID);
        mStory = StoryLab.get(getActivity()).getStory(storyId);
        //mStory = new Story();
        //mStory.setmTitle(" ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        mTitleField=(TextView) view.findViewById(R.id.story_title_TV);
        mDescription = (TextView) view.findViewById(R.id.description);
        //change image view to storyimageview. reference xml for furthor instructions upon completion.
        mPicture = (ImageView) view.findViewById(R.id.imageView);

        mTitleField.setText(mStory.getmTitle());
        mDescription.setText(mStory.getmDescr());
        mPicture.setImageDrawable(mStory.getmImage());
        return view;
    }

}

