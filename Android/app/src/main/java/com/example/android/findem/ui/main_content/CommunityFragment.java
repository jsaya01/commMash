package com.example.android.findem.ui.main_content;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.findem.R;
import com.example.android.findem.models.Community;
import com.example.android.findem.adapters.MatchListAdapter;
import com.example.android.findem.models.Match;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.utils.MatchLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CommunityFragment extends Fragment {
    private MatchListAdapter yourMatchesAdapter;
    private TextView profileEditButton;
    private Group addMatchesButton;

    private FragmentManager fragmentManager = getFragmentManager();

    private List<Match> yourMatches = new ArrayList<>();

    private long uid;
    private Community community;

    private static final String LOG_TAG = "CommunityFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle userBundle = this.getArguments();

        uid = Objects.requireNonNull(userBundle).getLong(getResources().getString(R.string.bundle_uid));

        community = (Community)userBundle.getSerializable(getResources().getString(R.string.bundle_community));
        Log.e(LOG_TAG, String.valueOf(community.getCid()));

        View root = inflater.inflate(R.layout.community, container, false);
        setUpWorld(root, userBundle);

        return root;
    }

    private void setUpWorld(View root, Bundle bundle) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView matchesOverviewRv = root.findViewById(R.id.community_matches_rv);
        matchesOverviewRv.setLayoutManager(linearLayoutManager);
        matchesOverviewRv.setHasFixedSize(true);
        yourMatchesAdapter = new MatchListAdapter();
        yourMatchesAdapter.setState((ArrayList<Match>) yourMatches, getContext(), uid, getFragmentManager());
        matchesOverviewRv.setAdapter(yourMatchesAdapter);

        profileEditButton = root.findViewById(R.id.community_my_profile_btn);
        addMatchesButton = root.findViewById(R.id.community_add_matches_grp);

        TextView communityDescription = root.findViewById(R.id.community_description);

        setOnClickListeners();

        if (bundle != null) {
            // set the community's title, description, and image
            Objects.requireNonNull(getActivity()).setTitle(community.getName());
            communityDescription.setText(community.getDescription());

            // fetch all matches for the user and add them to the array list
            new CommunityFragment.CommunityASyncTask().execute(uid);

        } else {
            Log.e(LOG_TAG, "Error retrieving community from bundle");
            Toast.makeText(getContext(), "Could not access the community.", Toast.LENGTH_LONG).show();
        }
    }


    private void setOnClickListeners() {
        profileEditButton.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();
            fragmentTransaction.replace(R.id.master_activity_fragment, new EditProfileFragment());
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_EDIT_FRAGMENT);
            fragmentTransaction.commit();
        });

        addMatchesButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(getResources().getString(R.string.bundle_community), community);
            bundle.putSerializable(getResources().getString(R.string.bundle_uid), uid);

            MatchingFragmentCards matchingFragmentCards = new MatchingFragmentCards();
            matchingFragmentCards.setArguments(bundle);

            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
            fragmentTransaction.add(R.id.master_activity_fragment, matchingFragmentCards);
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_MATCHING_FRAGMENT);
            fragmentTransaction.commit();
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class CommunityASyncTask extends AsyncTask<Long, Void, ArrayList<Match>> {

        @Override
        protected ArrayList<Match> doInBackground(Long... longs) {
            if (longs.length < 1 || longs[0] == null) {
                return new ArrayList<>();
            }

            return MatchLoader.getMatchesOfUid(longs[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Match> matches) {
            super.onPostExecute(matches);

            Log.d(LOG_TAG, "Community size returning is " + matches.size());
            yourMatches.clear();
            yourMatches.addAll(matches);
            yourMatchesAdapter.notifyDataSetChanged();
        }
    }
}
