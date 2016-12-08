package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class StoryFragment extends Fragment {

    private static final String ARG_STORY_ID = "story_id";

    private Story mStory;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_story, container, false);

        mTitleField = (EditText) v.findViewById(R.id.story_title);
        mTitleField.setText(mStory.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStory.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.story_date);
        mDateButton.setText(mStory.getmDescr().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.story_solved);
        mSolvedCheckBox.setChecked(mStory.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mStory.setSolved(isChecked);
            }
        });

        return v;
    }
}
