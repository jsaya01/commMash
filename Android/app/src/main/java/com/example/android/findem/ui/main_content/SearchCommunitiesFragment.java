package com.example.android.findem.ui.main_content;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.findem.R;
import com.example.android.findem.adapters.CommunityListAdapter;
import com.example.android.findem.models.Community;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.utils.CommunityLoader;

import java.util.ArrayList;
import java.util.List;

public class SearchCommunitiesFragment extends Fragment {
    CommunityListAdapter communityListAdapter;

    private ArrayList<Community> communities = new ArrayList<>();
    private static final String LOG_TAG = "SearchCommunitiesFrag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.search_communities, container, false);
        TextView filterTv = root.findViewById(R.id.filter_communities);

        setUpWorld(root);

        filterTv.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.master_activity_fragment, new FilterResultsFragment());
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_FILTER_FRAGMENT);
            fragmentTransaction.commit();
        });

        return root;
    }

    private void setUpWorld(View root){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView communitiesRv = root.findViewById(R.id.search_communities_rv);
        communitiesRv.setLayoutManager(linearLayoutManager);
        communitiesRv.setHasFixedSize(true);
        communityListAdapter = new CommunityListAdapter();
        int uid = 1;
        communityListAdapter.setState(communities, getContext(), uid, getFragmentManager());
        communitiesRv.setAdapter(communityListAdapter);

        new SearchASyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class SearchASyncTask extends AsyncTask<Void, Void, List<Community>> {

        @Override
        protected List<Community> doInBackground(Void... voids) {
            return CommunityLoader.getAllCommunities();
        }

        @Override
        protected void onPostExecute(List<Community> community) {
            super.onPostExecute(community);

            if (community == null) {
                Log.e(LOG_TAG, "Failed to retrieve communities");
            } else {
                Log.d(LOG_TAG, "Community size returning is " + community.size());

                communities.clear();
                communities.addAll(community);
                communityListAdapter.notifyDataSetChanged();
            }
        }
    }
}
