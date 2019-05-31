package com.example.android.findem.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.findem.R;
import com.example.android.findem.ui.main_content.EditProfileFragment;
import com.example.android.findem.ui.main_content.HomeFragment;
import com.example.android.findem.ui.messaging.MessageOverviewFragment;

import java.util.Objects;

public class MasterNavigator extends AppCompatActivity {
    private Bundle dataForFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long uid = Objects.requireNonNull(getIntent().getExtras()).getLong(getResources().getString(R.string.bundle_uid));
        Log.d("MASTER", String.valueOf(uid));

        dataForFragments = new Bundle();
        dataForFragments.putLong(getResources().getString(R.string.bundle_uid), uid);

        setContentView(R.layout.master_navigator);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = createNavigationBar();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // default to launch home screen, will need to fix this once we handle state changes
        setUpHomeFragment();
    }

    public BottomNavigationView.OnNavigationItemSelectedListener createNavigationBar() {
        return item -> {
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
                default:
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
