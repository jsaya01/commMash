package com.example.android.findem.ui.main_content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class SearchCommunitiesFragment extends Fragment {
    private TextView filterTv;
    private RecyclerView communitiesRv;
    CommunityListAdapter communityListAdapter;

    private int uid = 1;
    private ArrayList<Community> communities = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.search_communities, container, false);
        filterTv = root.findViewById(R.id.filter_communities);

        setUpWorld(root);

        communities = CommunityLoader.getCommunitiesOfUid(uid);

        filterTv.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.master_activity_fragment, new FilterResultsFragment());
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_FILTER_RESULTS_FRAGMENT);
            fragmentTransaction.commit();
        });

        return root;
    }

    private void setUpWorld(View root){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        communitiesRv = root.findViewById(R.id.search_communities_rv);
        communitiesRv.setLayoutManager(linearLayoutManager);
        communitiesRv.setHasFixedSize(true);
        communityListAdapter = new CommunityListAdapter();
        communityListAdapter.setState(communities, getContext(), uid, getFragmentManager());
    }
}
