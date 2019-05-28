package com.example.android.findem.ui.main_content;

import android.support.v4.app.Fragment;


public class CommunityFragment extends Fragment {
//    private RecyclerView matchesOverviewRv;
//    private MatchesListAdapter matchesListAdapter;
//    private TextView profileEditButton;
//    private Group addMatchesButton;
//    private ImageView communityImage;
//    private TextView communityDescription;
//    private Bundle userBundle;
//
//    private ArrayList<User> yourMatches = new ArrayList<>();
//
//    private int uid;
//    private Community community;
//
//    private static final String LOG_TAG = "CommunityFragment";
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
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//
//        matchesOverviewRv = root.findViewById(R.id.community_matches_rv);
//        matchesOverviewRv = root.findViewById(R.id.home_your_communities_rv);
//        matchesOverviewRv.setLayoutManager(linearLayoutManager);
//        matchesOverviewRv.setHasFixedSize(true);
//        matchesOverviewAdapter = new MatchesListAdapter();
//        matchesOverviewAdapter.setCommunities(yourMatches);
//        matchesOverviewRv.setAdapter(matchesListAdapter);
//
//        profileEditButton = root.findViewById(R.id.community_my_profile_btn);
//        addMatchesButton = root.findViewById(R.id.community_add_matches_grp);
//
//        communityImage = root.findViewById(R.id.community_image);
//        communityDescription = root.findViewById(R.id.community_description);
//
//        Bundle bundle = this.getArguments();
//        setOnClickListeners();
//
//        if (bundle != null) {
//            // set the community's title, description, and image
//            getActivity().setTitle(community.getName());
//            communityDescription.setText(community.getDescription());
//
//            new CommunityFragment.ImageAsyncTask(communityImage).execute(community.getImagepath());
//        } else {
//            Log.e(LOG_TAG, "Error retrieving community from bundle");
//            Toast.makeText(getContext(), "Could not access the community.", Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//    private void setOnClickListeners() {
//        profileEditButton.setOnClickListener(v -> {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.master_activity_fragment, new EditProfileFragment());
//            fragmentTransaction.addToBackStack(ActiveFragments.TAG_EDIT_PROFILE_FRAGMENT);
//            fragmentTransaction.commit();
//        });
//
//        addMatchesButton.setOnClickListener(v -> {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.master_activity_fragment, new MatchingFragment());
//            fragmentTransaction.addToBackStack(ActiveFragments.TAG_MATCHING_FRAGMENT);
//            fragmentTransaction.commit();
//        });
//    }
//
//
//    // modify to accept a Community object
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
