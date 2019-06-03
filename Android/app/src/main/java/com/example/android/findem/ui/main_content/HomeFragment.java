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
import android.widget.Button;
import android.widget.Toast;

import com.example.android.findem.R;
import com.example.android.findem.adapters.CommunityListAdapter;
import com.example.android.findem.models.Community;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.utils.CommunityLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    CommunityListAdapter trendingCommunitiesAdapter;
    CommunityListAdapter yourCommunitiesAdapter;
    private Button createCommunityBtn;
    private Button searchCommunityBtn;

    private long uid;
    private ArrayList<Community> yourCommunities = new ArrayList<>();
    private ArrayList<Community> trendingCommunities = new ArrayList<>();

    private static final String LOG_TAG = "HomeFragment";

    public HomeFragment() {
        //Empty constructor
    }

    private void setTitle() {
        if (getActivity() != null) {
            getActivity().setTitle("Home");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home, container, false);
        uid = this.getArguments().getLong("uid", -1);
        setTitle();
        setUpWorld(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitle();
    }

    private void setUpWorld(View root) {
        LinearLayoutManager linearManagerTrending = new LinearLayoutManager(getContext());
        RecyclerView trendingCommunitiesRv = root.findViewById(R.id.home_trending_communities_rv);
        trendingCommunitiesRv.setLayoutManager(linearManagerTrending);
        trendingCommunitiesRv.setHasFixedSize(true);
        trendingCommunitiesAdapter = new CommunityListAdapter();
        trendingCommunitiesAdapter.setState(trendingCommunities, getContext(), uid, getFragmentManager());
        trendingCommunitiesRv.setAdapter(trendingCommunitiesAdapter);

        LinearLayoutManager linearManagerYour = new LinearLayoutManager(getContext());
        RecyclerView yourCommunitiesRv = root.findViewById(R.id.home_your_communities_rv);
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
            uid = bundle.getLong("uid", -1);
            new HomeASyncTask().execute(uid);
        } else {
            Log.e(LOG_TAG, "Error retrieving uid from bundle");
            Toast.makeText(getContext(), "Something went really wrong... try restarting the application", Toast.LENGTH_LONG).show();
        }
    }

    private void setOnClickListeners() {
        final Bundle bundle = new Bundle();
        bundle.putLong(getResources().getString(R.string.bundle_uid), uid);

        createCommunityBtn.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();

            CreateCommunityFragment createCommunityFragment = new CreateCommunityFragment();
            createCommunityFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.master_activity_fragment, createCommunityFragment);
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_CREATE_FRAGMENT);
            fragmentTransaction.commit();
        });

        searchCommunityBtn.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();

            SearchCommunitiesFragment searchCommunitiesFragment = new SearchCommunitiesFragment();
            searchCommunitiesFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.master_activity_fragment, searchCommunitiesFragment);
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_SEARCH_FRAGMENT);
            fragmentTransaction.commit();
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class HomeASyncTask extends AsyncTask<Long, Void, ArrayList<List<Community>>> {

        @Override
        protected ArrayList<List<Community>> doInBackground(Long... longs) {
            if (longs.length < 1 || longs[0] == null) {
                return new ArrayList<>();
            }

            ArrayList<List<Community>> results = new ArrayList<>();
            results.add(CommunityLoader.getCommunitiesOfUid(longs[0]));
            results.add(CommunityLoader.getTrendingCommunities());

            return results;
        }

        @Override
        protected void onPostExecute(ArrayList<List<Community>> communities) {
            super.onPostExecute(communities);

            if (communities == null) {
                Log.e(LOG_TAG, "Failed to retrieve communities");
            } else {
                Log.d(LOG_TAG, "Community size returning is " + communities.size());

                if (communities.get(0) != null) {
                    yourCommunities.clear();
                    yourCommunities.addAll(communities.get(0));
                    yourCommunitiesAdapter.notifyDataSetChanged();
                }

                if (communities.get(1) != null) {
                    trendingCommunities.clear();
                    trendingCommunities.addAll(communities.get(1));
                    trendingCommunitiesAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
