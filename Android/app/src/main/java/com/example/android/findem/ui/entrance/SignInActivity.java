package com.example.android.findem.ui.entrance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.findem.R;
import com.example.android.findem.models.User;
import com.example.android.findem.ui.MasterNavigator;
import com.example.android.findem.utils.Connection;
import com.example.android.findem.utils.UserLoader;

import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
    static String SIGNIN_ID = "SIGNIN";
    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
    }

    public void enterApplication(View view) {
        Intent intent = new Intent(this, MasterNavigator.class);
        intent.putExtra(getResources().getString(R.string.bundle_uid), 3);
        startActivity(intent);
    }

    public void signinClicked(View view) {
        String username = ((EditText)findViewById(R.id.signin_username)).getText().toString();
        String password = ((EditText)findViewById(R.id.signin_password)).getText().toString();
        try {
            new SigninAsync(username, password, getApplicationContext()).execute();
        } catch (Exception e) {
            Log.e(SIGNIN_ID, e.getClass().toString());
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
        }

//        if (!password.equals(user.getPassword())) {
//            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
//            return;
//        }
        new SigninAsync(username, password, getApplicationContext()).execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class SigninAsync extends AsyncTask<JSONObject, Void, Void> {
        private String username;
        private String password;
        private Context context;

        public SigninAsync(String username, String password, Context context) {
            this.username = username;
            this.password = password;
            this.context = context;
        }

        @Override
        protected Void doInBackground(JSONObject... jsonObjects) {
            // Get the uid for starting the home activity
            user = UserLoader.getUserByUserName(username);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Transition to Home
            Intent intent = new Intent(context, MasterNavigator.class);
            intent.putExtra("uid", user.getUid());
            startActivity(intent);
        }
    }
}
