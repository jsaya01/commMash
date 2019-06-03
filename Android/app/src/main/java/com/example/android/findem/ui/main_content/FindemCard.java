package com.example.android.findem.ui.main_content;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.findem.R;
import com.example.android.findem.models.Match;
import com.example.android.findem.models.User;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

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

    public FindemCard(Context context, User profile, SwipePlaceHolderView swipeView) {
        mContext = context;
        mProfile = profile;
        mSwipeView = swipeView;
    }

    @Resolve
    public void onResolved(){
        Glide.with(mContext).load(mProfile.getImagepath()).into(profileImageView);
        nameTxt.setText(mProfile.getFname());
        descriptionTxt.setText(mProfile.getDescription());
    }

    @SwipeOut
    public void onSwipedOut(){
        Log.d(LOG_TAG, "onSwipedOut");
        System.out.println("swipED OUT!!");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    public void onSwipeCancelState(){
        Log.d(LOG_TAG, "onSwipeCancelState");
        System.out.println("swiping CANCEL!!");
    }

    @SwipeIn
    public void onSwipeIn(){
        Log.d(LOG_TAG, "onSwipedIn");
        System.out.println("swipED IN!!");
    }

    @SwipeInState
    public void onSwipeInState(){
        Log.d(LOG_TAG, "onSwipeInState");
        System.out.println("swipe IN!!");

    }

    @SwipeOutState
    public void onSwipeOutState(){
        Log.d(LOG_TAG, "onSwipeOutState");
        System.out.println("swipe OUT!!");
    }
}