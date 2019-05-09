package com.example.android.findem.UI.MainContent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.findem.R;
import com.example.android.findem.UI.ActiveFragments;

public class HomeFragment extends Fragment {
    private RecyclerView trendingCommunities;
    private RecyclerView yourCommunitiies;
    private Button createCommunityBtn;
    private Button searchCommunityBtn;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home, container, false);

        trendingCommunities = root.findViewById(R.id.home_trending_communities_rv);
        yourCommunitiies = root.findViewById(R.id.home_your_communities_rv);
        createCommunityBtn = root.findViewById(R.id.home_create_community_btn);
        searchCommunityBtn = root.findViewById(R.id.home_search_community_btn);

        createCommunityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.master_activity_fragment, new CreateCommunityFragment());
                fragmentTransaction.addToBackStack(ActiveFragments.TAG_CREATE_COMM_FRAGMENT);
                fragmentTransaction.commit();
            }
        });

        searchCommunityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.master_activity_fragment, new CommunityFragment());
                fragmentTransaction.addToBackStack(ActiveFragments.TAG_SEARCH_COMM_FRAGMENT);
                fragmentTransaction.commit();
            }
        });

        return root;
    }


}
