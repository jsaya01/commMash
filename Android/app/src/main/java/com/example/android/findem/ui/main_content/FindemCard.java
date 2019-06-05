package com.example.android.findem.ui.main_content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.findem.R;
import com.example.android.findem.models.Matches;
import com.example.android.findem.models.User;
import com.example.android.findem.utils.Connection;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import org.json.JSONException;
import org.json.JSONObject;

@Layout(R.layout.findem_card_view)
public class FindemCard {
    private static final String LOG_TAG = "FindemCardEvent";

    @View(R.id.profileImageView)
    public ImageView profileImageView;

    @View(R.id.nameTxt)
    public TextView nameTxt;

    @View(R.id.descriptionTxt)
    public TextView descriptionTxt;

    private User mProfile;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private long uid;

    public FindemCard(Context context, User profile, SwipePlaceHolderView swipeView, long uid) {
        this.mContext = context;
        this.mProfile = profile;
        this.mSwipeView = swipeView;
        this.uid = uid;
    }

    @Resolve
    public void onResolved(){
        nameTxt.setText(mProfile.getFname());
        descriptionTxt.setText(mProfile.getDescription());
    }

    @SwipeOut
    public void onSwipedOut(){
        Log.d(LOG_TAG, "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    public void onSwipeCancelState(){
        Log.d(LOG_TAG, "onSwipeCancelState");
    }

    @SwipeIn
    public void onSwipeIn(){
        Log.d(LOG_TAG, "onSwipedIn");
        // make a new Matches() object
        Matches m = new Matches(uid, mProfile.getUid());
        JSONObject data = new JSONObject();

        try {
            data.put("uid1", m.getUid1());
            data.put("uid2", m.getUid2());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Send POST
        new FindemCard.GetMatchesAsync().execute(data);
    }

    @SwipeInState
    public void onSwipeInState(){
        Log.d(LOG_TAG, "onSwipeInState" + mContext.getPackageCodePath());
    }

    @SwipeOutState
    public void onSwipeOutState(){
        Log.d(LOG_TAG, "onSwipeOutState");
    }

    @SuppressLint("StaticFieldLeak")
    private class GetMatchesAsync extends AsyncTask<JSONObject, Void, Matches> {

        public GetMatchesAsync() {
            //This is empty cause it is
        }

        @Override
        protected Matches doInBackground(JSONObject... jsonObjects) {
            Connection.postRequest("/matches", jsonObjects[0]);
            return null;
        }

    }
}