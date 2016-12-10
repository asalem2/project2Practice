package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by neo on 11/28/2016.
 */

public class StoryListFragment extends Fragment {
    private RecyclerView mStoryRecyclerView;
    private StoryAdapter mAdapter;
    private SearchView mStorySearchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);


        mStorySearchView = (SearchView) view.findViewById(R.id.story_search_view);


        mStoryRecyclerView = (RecyclerView) view
                .findViewById(R.id.story_recycler_view);
        mStoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        mStorySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                mAdapter.getFilter().filter(query);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String query){
                //this will filter as you type

                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        updateUI();

        return view;
    }

    private void updateUI() {
        StoryLab storyLab = StoryLab.get(getActivity());
        ArrayList<Story> cards = storyLab.getStories();

        if(mAdapter == null){
            mAdapter = new StoryAdapter(getContext(),cards);
            mStoryRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }

    }

    private class StoryAdapter extends RecyclerView.Adapter<StoryHolder> implements Filterable{

        Context c;

        private ArrayList<Story> mStory;
        private ArrayList<Story> filterList;
        SearchFilter filter;

        public StoryAdapter(Context cont, ArrayList<Story> mStory) {
            this.c = cont;
            this.mStory = mStory;
            this.filterList = mStory;
        }


        @Override
        public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_story, parent, false);
            return new StoryHolder(view);
        }

        @Override
        public void onBindViewHolder(StoryHolder holder, int position) {
            Story card = mStory.get(position);
            holder.bindStory(card);
        }

        @Override
        public int getItemCount() {
            return mStory.size();
        }

        @Override
        public Filter getFilter(){
            //RETURN FILTERED OBJECT
            if(filter == null){
                filter = new SearchFilter(filterList,this);
            }
            return filter;
        }
    }


    private class StoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDescriptionTextView;
        private ImageView mImageView;
        private Story mStory;

        public StoryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.card_story_title_TV);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.card_description);
            mImageView = (ImageView) itemView.findViewById(R.id.card_imageView);
        }

        public void bindStory(Story story) {
            mStory = story;


            mTitleTextView.setText(mStory.getmTitle());
            mDescriptionTextView.setText(mStory.getmDescr().toString());
            mImageView.setImageDrawable(mStory.getmImage());
        }

        @Override
        public void onClick(View v) {
            Intent intent = StoryActivity.newIntent(getActivity(), mStory.getId());
            startActivity(intent);
        }
    }

    private class SearchFilter extends Filter {

        StoryAdapter adapter;
        ArrayList<Story> filterList;

        private SearchFilter(ArrayList<Story> filterList, StoryAdapter adapter){
            this.adapter = adapter;
            this.filterList = filterList;
        }

        //Filter function start
        @Override
        protected FilterResults performFiltering(CharSequence arg){
            FilterResults results = new FilterResults();

            if(arg != null && arg.length() > 0){
                arg = arg.toString().toUpperCase();
                //Story the filtered cards
                ArrayList<Story> filteredStory = new ArrayList<>();

                for(int i=0; i< filterList.size(); i++){
                    if(filterList.get(i).getmTitle().toUpperCase().contains(arg)){
                        //add player to filtered players
                        filteredStory.add(filterList.get(i));
                    }
                }
                results.count=filteredStory.size();
                results.values=filteredStory;
            }else{
                results.count=filterList.size();
                results.values=filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence arg, FilterResults results){
            adapter.mStory = (ArrayList<Story>) results.values;

            //REFRESH RECYCLERVIEW
            adapter.notifyDataSetChanged();
        }



    }
}
