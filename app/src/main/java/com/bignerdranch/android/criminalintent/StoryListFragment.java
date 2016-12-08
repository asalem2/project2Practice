package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.List;

public class StoryListFragment extends Fragment {
    private SearchView sv;
    private RecyclerView mStoryRecyclerView;
    private StoryAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);

        mStoryRecyclerView = (RecyclerView) view
                .findViewById(R.id.story_recycler_view);
        mStoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        sv = (SearchView) view.findViewById(R.id.search_view);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                Log.d("TAGG", newText);
                StoryLab storyLab = StoryLab.get(App.getContext());
//                StoryLab storyLab = StoryLab.get(getActivity());
                StoryAdapter storyAdapter = new StoryAdapter(storyLab.searchStory(newText));
                mStoryRecyclerView.setAdapter(storyAdapter);
                return true;
            }
        });



        updateUI();

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        StoryLab storyLab = StoryLab.get(getActivity());
        List<Story> stories = storyLab.getStories();

        if (mAdapter == null) {
            mAdapter = new StoryAdapter(stories);
            mStoryRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }


    }

    private class StoryHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDescrTextView;
    //    private CheckBox mSolvedCheckBox;

        private Story mStory;


        public StoryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.story_title_TV);
            mDescrTextView = (TextView) itemView.findViewById(R.id.description);
    //        mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_story_solved_check_box);

            /*mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_story_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_story_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_story_solved_check_box);*/
        }

        public void bindStory(Story story) {
            mStory = story;
            mTitleTextView.setText(mStory.getTitle());
            mDescrTextView.setText(mStory.getmDescr().toString());
    //        mSolvedCheckBox.setChecked(mStory.isSolved());
        }

        @Override
        public void onClick(View v) {
            Intent intent = StoryActivity.newIntent(getActivity(), mStory.getId());
            startActivity(intent);
        }
    }

    private class StoryAdapter extends RecyclerView.Adapter<StoryHolder> {

        private List<Story> mStories;
        private View.OnClickListener listener;

        public StoryAdapter(List<Story> stories) {
            mStories = stories;
        }

        @Override
        public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_story, parent, false);
            return new StoryHolder(view);
        }

        @Override
        public void onBindViewHolder(StoryHolder holder, int position) {
            Story story = mStories.get(position);
            holder.bindStory(story);
           /* holder.mTitleTextView.setText(mStories.get(position).getTitle());
            holder.mDescrTextView.setText(mStories.get(position).getDate());*/
        }

        @Override
        public int getItemCount() {
            return mStories.size();
        }
    }
}
