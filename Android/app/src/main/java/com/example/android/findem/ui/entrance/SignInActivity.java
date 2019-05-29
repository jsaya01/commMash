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
        String username = ((EditText) findViewById(R.id.signin_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.signin_password)).getText().toString();
        new SigninAsync(username, password, getApplicationContext()).execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class SigninAsync extends AsyncTask<Void, Void, User> {
        private String username;
        private String password;
        private Context context;

        public SigninAsync(String username, String password, Context context) {
            this.username = username;
            this.password = password;
            this.context = context;
        }

        @Override
        protected User doInBackground(Void... a) {
            // Get the uid for starting the home activity
            return UserLoader.getUserByUserName(username);
        }

        @Override
        protected void onPostExecute(User u) {
            super.onPostExecute(u);

            // Transition to Home
            if (u == null) {
                Toast.makeText(context, "Invalid username.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(!u.getPassword().equals(password)) {
                Toast.makeText(context, "Invalid password.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(context, MasterNavigator.class);
            intent.putExtra(context.getResources().getString(R.string.bundle_uid), u.getUid());
            Log.e("SIGNIN", String.valueOf(u.getUid()));
            startActivity(intent);
        }
    }
}
