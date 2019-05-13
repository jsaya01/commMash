package com.example.android.findem.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.findem.R;
import com.example.android.findem.UI.Entrance.SignInActivity;
import com.example.android.findem.UI.Entrance.SignUpActivity;
import com.example.android.findem.UI.MainContent.EditProfileFragment;
import com.example.android.findem.UI.MainContent.HomeFragment;
import com.example.android.findem.UI.Messaging.MessageOverviewFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.opening_screen);
    }

    /** Called when the user taps the Send button */
    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

}
