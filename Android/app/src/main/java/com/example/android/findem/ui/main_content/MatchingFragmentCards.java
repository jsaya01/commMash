package com.example.android.findem.ui.main_content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.android.findem.models.Community;
import com.example.android.findem.utils.CommunityUserLoader;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import com.example.android.findem.R;
import com.example.android.findem.models.User;

import java.util.ArrayList;
import java.util.List;


public class MatchingFragmentCards extends Fragment {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private Community community;
    private long uid;


    private List<User> allUsers = new ArrayList<>();

    private static final String LOG_TAG = "MatchingFragmentCards";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.findem_card, container, false);
        Bundle communityBundle = this.getArguments();

        this.community = (Community)communityBundle.getSerializable(getResources().getString(R.string.bundle_community));
        this.uid = (long)communityBundle.getSerializable(getResources().getString(R.string.bundle_uid));

        setUpWorld(root, communityBundle);

        setOnClickListeners(root);

        return root;
    }

    private void setUpWorld(View root, Bundle bundle){
        mSwipeView = root.findViewById(R.id.swipeView);
        mContext = getContext();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.findem_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.findem_swipe_out_msg_view));

        if (bundle != null){
            new MatchingFragmentCards.MatchingASyncTask().execute(community.getCid());
        }
    }


    private void setOnClickListeners(View root) {
        root.findViewById(R.id.rejectBtn).setOnClickListener(v -> mSwipeView.doSwipe(false));

        root.findViewById(R.id.acceptBtn).setOnClickListener(v -> mSwipeView.doSwipe(true));
    }


    @SuppressLint("StaticFieldLeak")
    private class MatchingASyncTask extends AsyncTask<Long, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Long... longs) {
            if (longs.length < 1 || longs[0] == null) {
                return new ArrayList<>();
            }

            return CommunityUserLoader.getAllUsers(longs[0]);
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);

            Log.d(LOG_TAG, "Users size returning is " + users.size());
            allUsers.addAll(users);

            for(User user : allUsers){
                mSwipeView.addView(new FindemCard(mContext, user, mSwipeView, uid));
            }
        }
    }
}