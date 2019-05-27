package com.example.android.findem.UI.MainContent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.findem.R;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class CreateCommunityFragment extends Fragment {

    private Button createCommunityBtn;
    private EditText communityName;
    private EditText communityDesc;
    private EditText tags;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.create_community, container, false);

        createCommunityBtn = root.findViewById(R.id.create_community_button);
        communityName = root.findViewById(R.id.community_name);
        communityDesc = root.findViewById(R.id.community_description);
        tags = root.findViewById(R.id.addTags);

        createCommunityBtn.setOnClickListener((v) -> {
            HashMap<String, String> data = new HashMap<>();
            data.put("name", communityName.getText().toString());
            data.put("imagepath", null);
            data.put("description", communityDesc.getText().toString());

            new CreateCommunityAsync().execute(data.toString());

            System.out.println(communityName.getText().toString());
            System.out.println(communityDesc.getText().toString());
            System.out.println(tags.getText().toString());
            System.out.println("clicked on button");
        });

        return root;
    }

    private static class CreateCommunityAsync extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            try {
                URL url = new URL("https://findem-back.herokuapp.com/cm.community");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(strings[0]);
                writer.flush();
                writer.close();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
