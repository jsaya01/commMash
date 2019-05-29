package com.example.android.findem.ui.main_content;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.findem.R;
import com.example.android.findem.models.Community;
import com.example.android.findem.models.User;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.utils.Connection;

import java.util.ArrayList;

public class MatchingFragment extends Fragment {
//    private TextView communityNameBtn;
//    private ImageView userImage;
//    private TextView userName;
//    private TextView userBio;
//
//    private ImageView mehBtn;
//    private ImageView foundemBtn;
//
//    private Bundle userBundle;
//
//    private ArrayList<User> yourMatches = new ArrayList<>();
//
//    private int uid;
//    private Community community;
//
//    private static final String LOG_TAG = "MatchingFragment";
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        userBundle = this.getArguments();
//
//        uid = userBundle.getInt(getResources().getString(R.string.bundle_uid));
//        community = (Community)userBundle.getSerializable(getResources().getString(R.string.bundle_community));
//
//        View root = inflater.inflate(R.layout.community, container, false);
//        setUpWorld(root);
//
//        return root;
//    }
//
//    private void setUpWorld(View root) {
//        communityNameBtn = root.findViewById(R.id.match_finding_community_back_btn);
//
//        userImage = root.findViewById(R.id.match_finding_user_img);
//        userName = root.findViewById(R.id.match_finding_user_name_txt);
//        userBio = root.findViewById(R.id.match_finding_user_bio_txt);
//
//        mehBtn = root.findViewById(R.id.community_image);
//        foundemBtn = root.findViewById(R.id.community_description);
//
//        Bundle bundle = this.getArguments();
//        setOnClickListeners();
//
//        if (bundle != null) {
//            // set the user's name, description, and image
//            getActivity().setTitle(community.getName());
//            userName.setText(user.getFName());
//            userBio.setText(user.getDescription());
//
//            new MatchingFragment.ImageAsyncTask(userImage).execute(user.getImagepath());
//        } else {
//            Log.e(LOG_TAG, "Error retrieving community from bundle");
//            Toast.makeText(getContext(), "Could not access the community.", Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//    private void setOnClickListeners() {
//        communityNameBtn.setOnClickListener(v -> {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.master_activity_fragment, new CommunityFragment());
//            fragmentTransaction.addToBackStack(ActiveFragments.TAG_COMMUNITY_FRAGMENT);
//            fragmentTransaction.commit();
//        });
//
//        mehBtn.setOnClickListener(v -> {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.master_activity_fragment, new MatchingFragment());
//            fragmentTransaction.addToBackStack(ActiveFragments.TAG_MATCHING_FRAGMENT);
//            fragmentTransaction.commit();
//        });
//
//        foundemBtn.setOnClickListener(v -> {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.master_activity_fragment, new MatchingFragment());
//            fragmentTransaction.addToBackStack(ActiveFragments.TAG_MATCHING_FRAGMENT);
//            fragmentTransaction.commit();
//        });
//    }


//    private static class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView image;
//
//        public ImageAsyncTask(ImageView bmImage) {
//            this.image = bmImage;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            return Connection.downloadImage(urls[0]);
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bmImage) {
//            image.setImageBitmap(bmImage);
//
//            Log.d(LOG_TAG, "Set community image.");
//        }
//    }
//
//    private static class CommunityASyncTask extends AsyncTask<Integer, Void, ArrayList<User>> {
//
//        @Override
//        protected ArrayList<User> doInBackground(Integer... integers) {
//            if (integers.length < 1 || integers[0] == null) {
//                return null;
//            }
//
//            return UserLoader.getAllUsers(integers[0]);
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<User> community) {
//            super.onPostExecute(community);
//
//            Log.d(LOG_TAG, "Community size returning is " + community.size());
//            yourMatches.addAll(community);
//        }
//    }
}
