package com.example.android.findem.ui.main_content;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.findem.R;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.utils.Connection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class CreateCommunityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.create_community, container, false);

        Button createCommunityBtn = root.findViewById(R.id.create_community_button);
        EditText communityName = root.findViewById(R.id.community_name);
        EditText communityDesc = root.findViewById(R.id.community_description);

        createCommunityBtn.setOnClickListener(v -> {

            JSONObject data = new JSONObject();
            try {
                data.put("imagepath", null);
                data.put("description", communityDesc.getText().toString());
                data.put("name", communityName.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            new CreateCommunityAsync().execute(data);
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();

            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(getArguments());

            fragmentTransaction.replace(R.id.master_activity_fragment, homeFragment);
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_CREATE_FRAGMENT);
            fragmentTransaction.commit();
        });

        return root;
    }

    private static class CreateCommunityAsync extends AsyncTask<JSONObject, Void, Void> {

        @Override
        protected Void doInBackground(JSONObject... jsonObjects) {

            Connection.postRequest("/community", jsonObjects[0]);

            return null;
        }
    }
}
