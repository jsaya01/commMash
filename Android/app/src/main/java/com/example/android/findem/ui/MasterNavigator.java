package com.example.android.findem.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.findem.R;
import com.example.android.findem.ui.main_content.EditProfileFragment;
import com.example.android.findem.ui.main_content.HomeFragment;
import com.example.android.findem.ui.messaging.MessageOverviewFragment;

public class MasterNavigator extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private int uid;
    private Bundle dataForFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uid = getIntent().getExtras().getInt("uid");
        dataForFragments = new Bundle();
        dataForFragments.putInt(getResources().getString(R.string.bundle_uid), uid);

        setContentView(R.layout.master_navigator);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        mOnNavigationItemSelectedListener = createNavigationBar(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // default to launch home screen, will need to fix this once we handle state changes
        setUpHomeFragment();
    }

    public BottomNavigationView.OnNavigationItemSelectedListener createNavigationBar(final Context context) {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setUpHomeFragment();
                        return true;
                    case R.id.navigation_messages:
                        setUpMessagesFragment();
                        return true;
                    case R.id.navigation_settings:
                        setUpSettingsFragment();
                        return true;
                }
                return false;
            }
        };
    }

    public void setUpHomeFragment() {
        HomeFragment home = new HomeFragment();
        home.setArguments(dataForFragments);
        getSupportFragmentManager().beginTransaction().replace(R.id.master_activity_fragment, home, ActiveFragments.TAG_HOME_FRAGMENT).commit();
    }

    public void setUpMessagesFragment() {
        MessageOverviewFragment messages = new MessageOverviewFragment();
        messages.setArguments(dataForFragments);
        getSupportFragmentManager().beginTransaction().replace(R.id.master_activity_fragment, messages, ActiveFragments.TAG_MESSAGES_FRAGMENT).commit();
    }

    public void setUpSettingsFragment() {
        EditProfileFragment editProfile = new EditProfileFragment();
        editProfile.setArguments(dataForFragments);
        getSupportFragmentManager().beginTransaction().replace(R.id.master_activity_fragment, editProfile, ActiveFragments.TAG_SETTINGS_FRAGMENT).commit();
    }
}
