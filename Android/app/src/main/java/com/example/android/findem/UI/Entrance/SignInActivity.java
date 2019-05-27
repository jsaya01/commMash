package com.example.android.findem.UI.Entrance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.findem.R;
import com.example.android.findem.UI.MasterNavigator;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in);
    }

    public void enterApplication(View view) {
        //TODO PROCESS LOGIN

        // will need to pass in props too about the cm.user information
        Intent intent = new Intent(this, MasterNavigator.class);
        intent.putExtra("uid", -1);
        startActivity(intent);
    }

}
