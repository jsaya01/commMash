package com.example.android.findem.UI.MainContent;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.android.findem.Adapters.CommunityListAdapter;
import com.example.android.findem.Models.Community;
import com.example.android.findem.R;
import com.example.android.findem.UI.ActiveFragments;
import com.example.android.findem.Utils.CommunityLoader;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView trendingCommunitiesRv;
    CommunityListAdapter trendingCommunitiesAdapter;
    private RecyclerView yourCommunitiesRv;
    CommunityListAdapter yourCommunitiesAdapter;
    private Button createCommunityBtn;
    private Button searchCommunityBtn;

    private int uid;
    private ArrayList<Community> yourCommunities = new ArrayList<>();
    private ArrayList<Community> trendingCommunities = new ArrayList<>();

    private static final String LOG_TAG = "HomeFragment";

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home, container, false);
        setUpWorld(root);

        return root;
    }

    private void setUpWorld(View root) {
        LinearLayoutManager linearManagerTrending = new LinearLayoutManager(getContext());
        trendingCommunitiesRv = root.findViewById(R.id.home_trending_communities_rv);
        trendingCommunitiesRv.setLayoutManager(linearManagerTrending);
        trendingCommunitiesRv.setHasFixedSize(true);
        trendingCommunitiesAdapter = new CommunityListAdapter();
        trendingCommunitiesAdapter.setState(trendingCommunities, getContext(), uid, getFragmentManager());
        trendingCommunitiesRv.setAdapter(trendingCommunitiesAdapter);

        LinearLayoutManager linearManagerYour = new LinearLayoutManager(getContext());
        yourCommunitiesRv = root.findViewById(R.id.home_your_communities_rv);
        yourCommunitiesRv.setLayoutManager(linearManagerYour);
        yourCommunitiesRv.setHasFixedSize(true);
        yourCommunitiesAdapter = new CommunityListAdapter();
        yourCommunitiesAdapter.setState(yourCommunities, getContext(), uid, getFragmentManager());
        yourCommunitiesRv.setAdapter(yourCommunitiesAdapter);

        createCommunityBtn = root.findViewById(R.id.home_create_community_btn);
        searchCommunityBtn = root.findViewById(R.id.home_search_community_btn);

        setOnClickListeners();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            uid = bundle.getInt("uid", -1);
            new HomeASyncTask().execute(uid);
        } else {
            Log.e(LOG_TAG, "Error retrieving uid from bundle");
            Toast.makeText(getContext(), "Something went really wrong... try restarting the application", Toast.LENGTH_LONG).show();
        }
    }

    private void setOnClickListeners() {
        final Bundle bundle = new Bundle();
        bundle.putInt(getResources().getString(R.string.bundle_uid), uid);

        createCommunityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                CreateCommunityFragment createCommunityFragment = new CreateCommunityFragment();
                createCommunityFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.master_activity_fragment, createCommunityFragment);
                fragmentTransaction.addToBackStack(ActiveFragments.TAG_CREATE_COMM_FRAGMENT);
                fragmentTransaction.commit();
            }
        });

        searchCommunityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                SearchCommunitiesFragment searchCommunitiesFragment = new SearchCommunitiesFragment();
                searchCommunitiesFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.master_activity_fragment, searchCommunitiesFragment);
                fragmentTransaction.addToBackStack(ActiveFragments.TAG_SEARCH_COMM_FRAGMENT);
                fragmentTransaction.commit();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class HomeASyncTask extends AsyncTask<Integer, Void, ArrayList<Community>> {

        @Override
        protected ArrayList<Community> doInBackground(Integer... integers) {
            if (integers.length < 1 || integers[0] == null) {
                return null;
            }

            return CommunityLoader.getAllCommunities(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Community> community) {
            super.onPostExecute(community);

            Log.d(LOG_TAG, "Community size returning is " + community.size());
            yourCommunities.addAll(community);
        }
    }
}
