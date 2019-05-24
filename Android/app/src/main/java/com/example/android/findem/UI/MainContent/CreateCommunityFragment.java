package com.example.android.findem.UI.MainContent;

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

import java.io.IOException;
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

            //TODO need to post to the DB here

            System.out.println(communityName.getText().toString());
            System.out.println(communityDesc.getText().toString());
            System.out.println(tags.getText().toString());
            System.out.println("clicked on button");
        });

        return root;
    }
}
