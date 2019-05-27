package com.example.android.findem.ui.entrance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.findem.R;
import com.example.android.findem.ui.MasterNavigator;
import com.example.android.findem.utils.Connection;
import com.example.android.findem.utils.UserLoader;

import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.android.findem.models.User;


public class SignUpActivity extends AppCompatActivity {
    static String SIGNUP_ID = "SIGNUP";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up);
    }

    public void signupClicked(View view) {
        String firstName = ((EditText)findViewById(R.id.signup_first_name_et)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.signup_last_name_et)).getText().toString();
        String username = ((EditText)findViewById(R.id.sign_up_username_et)).getText().toString();
        String password = ((EditText)findViewById(R.id.sign_up_password_et)).getText().toString();
        String confirmPassword = ((EditText)findViewById(R.id.sign_up_confirm_password_et)).getText().toString();

        // Add validation here...

        // Create User From EditTexts
        JSONObject data = new JSONObject();
        try {
            data.put("fname", firstName);
            data.put("lname", lastName);
            data.put("username", username);
            data.put("password", password);
            data.put("description", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Send POST
        new SignupAsync().execute(data);
        new GetUserAsync(username, getApplicationContext()).execute(data);
    }

    private static class SignupAsync extends AsyncTask<JSONObject, Void, Void> {
        @Override
        protected Void doInBackground(JSONObject... jsonObjects) {
            Connection.postRequest("/user", jsonObjects[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetUserAsync extends AsyncTask<JSONObject, Void, Void> {
        private String username;
        private Context context;

        public GetUserAsync(String username, Context context) {
            this.username = username;
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
