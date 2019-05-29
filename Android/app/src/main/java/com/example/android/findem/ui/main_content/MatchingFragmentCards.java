package com.example.android.findem.ui.main_content;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.findem.models.TempUsers;
import com.example.android.findem.ui.ActiveFragments;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import com.example.android.findem.R;
import com.example.android.findem.models.User;

import java.util.ArrayList;

public class MatchingFragmentCards extends Fragment {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.findem_card, container, false);


//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.findem_card);
        ArrayList<User> temps = TempUsers.getTempUsers();

        mSwipeView = (SwipePlaceHolderView)root.findViewById(R.id.swipeView);
        mContext = getContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.findem_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.findem_swipe_out_msg_view));


        for(User user : temps){
            mSwipeView.addView(new FindemCard(mContext, user, mSwipeView));
        }

        setOnClickListeners(root);

        return root;
    }


    private void setOnClickListeners(View root) {
        root.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        root.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });
    }
}