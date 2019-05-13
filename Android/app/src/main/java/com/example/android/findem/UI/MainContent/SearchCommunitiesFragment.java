package com.example.android.findem.UI.MainContent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.findem.R;
import com.example.android.findem.UI.ActiveFragments;

public class SearchCommunitiesFragment extends Fragment {
    private TextView filterTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.search_communities, container, false);
        filterTv = root.findViewById(R.id.filter_communities);

        filterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.master_activity_fragment, new FilterResultsFragment());
                fragmentTransaction.addToBackStack(ActiveFragments.TAG_FILTER_RESULTS_FRAGMENT);
                fragmentTransaction.commit();
            }
        });

        return root;
    }
}
