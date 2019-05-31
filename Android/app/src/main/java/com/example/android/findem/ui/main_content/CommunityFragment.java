package com.example.android.findem.ui.main_content;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.findem.R;
import com.example.android.findem.models.Community;
import com.example.android.findem.adapters.MatchListAdapter;
import com.example.android.findem.models.Match;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.utils.Connection;
import com.example.android.findem.utils.MatchLoader;

import java.util.ArrayList;


public class CommunityFragment extends Fragment {
    private MatchListAdapter yourMatchesAdapter;
    private TextView profileEditButton;
    private Group addMatchesButton;

    private ArrayList<Match> yourMatches = new ArrayList<>();

    private int uid;
    private Community community;

    private static final String LOG_TAG = "CommunityFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle userBundle = this.getArguments();

        uid = userBundle.getInt(getResources().getString(R.string.bundle_uid));

        //TODO: comment out bottom line and replace community
        community = (Community)userBundle.getSerializable(getResources().getString(R.string.bundle_community));
        /*community = new Community("swimmers",
                "https://github.com/jsaya01/commMash/blob/master/images/community_test.jpeg",
                "we swim and we scared");
        */
        View root = inflater.inflate(R.layout.community, container, false);
        setUpWorld(root);

        return root;
    }

    private void setUpWorld(View root) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView matchesOverviewRv = root.findViewById(R.id.community_matches_rv);
        matchesOverviewRv.setLayoutManager(linearLayoutManager);
        matchesOverviewRv.setHasFixedSize(true);
        yourMatchesAdapter = new MatchListAdapter();
        yourMatchesAdapter.setState(yourMatches, getContext(), uid, getFragmentManager());
        matchesOverviewRv.setAdapter(yourMatchesAdapter);

        profileEditButton = root.findViewById(R.id.community_my_profile_btn);
        addMatchesButton = root.findViewById(R.id.community_add_matches_grp);

        TextView communityDescription = root.findViewById(R.id.community_description);

        Bundle bundle = this.getArguments();
        setOnClickListeners();

        if (bundle != null) {
            // set the community's title, description, and image
            getActivity().setTitle(community.getName());
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
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.master_activity_fragment, new EditProfileFragment());
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_EDIT_FRAGMENT);
            fragmentTransaction.commit();
        });

        addMatchesButton.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.master_activity_fragment, new MatchingFragment());
            fragmentTransaction.replace(R.id.master_activity_fragment, new MatchingFragmentCards());

            fragmentTransaction.addToBackStack(ActiveFragments.TAG_MATCHING_FRAGMENT);
            fragmentTransaction.commit();
        });
    }


    private static class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @SuppressLint("StaticFieldLeak")
        ImageView image;

        public ImageAsyncTask(ImageView bmImage) {
            this.image = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            return Connection.downloadImage(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bmImage) {
            image.setImageBitmap(bmImage);

            Log.d(LOG_TAG, "Set community image.");
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class CommunityASyncTask extends AsyncTask<Integer, Void, ArrayList<Match>> {

        @Override
        protected ArrayList<Match> doInBackground(Integer... integers) {
            if (integers.length < 1 || integers[0] == null) {
                return new ArrayList<Match>();
            }

            return MatchLoader.getMatchesOfUid(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Match> matches) {
            super.onPostExecute(matches);

            Log.d(LOG_TAG, "Community size returning is " + matches.size());
            yourMatches.addAll(matches);
            yourMatchesAdapter.notifyDataSetChanged();
        }
    }
}
