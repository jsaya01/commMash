package com.example.android.findem.ui.entrance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.findem.R;
import com.example.android.findem.models.User;
import com.example.android.findem.ui.MasterNavigator;
import com.example.android.findem.utils.Connection;
import com.example.android.findem.utils.UserLoader;

import org.json.JSONException;
import org.json.JSONObject;


public class SignUpActivity extends AppCompatActivity {
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

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

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
        new GetUserAsync(username, getApplicationContext()).execute(data);
    }

    @SuppressLint("StaticFieldLeak")
    private class GetUserAsync extends AsyncTask<JSONObject, Void, User> {
        private String username;
        private Context context;

        public GetUserAsync(String username, Context context) {
            this.username = username;
            this.context = context;
        }

        @Override
        protected User doInBackground(JSONObject... jsonObjects) {
            // Get the uid for starting the home activity
            Connection.postRequest("/user", jsonObjects[0]);
            return UserLoader.getUserByUserName(username);
        }

        @Override
        protected void onPostExecute(User u) {
            super.onPostExecute(u);

            // Transition to Home
            Intent intent = new Intent(context, MasterNavigator.class);
            intent.putExtra(context.getResources().getString(R.string.bundle_uid), u.getUid());
            startActivity(intent);
        }
    }
}
