package com.example.android.findem.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.findem.Models.Community;
import com.example.android.findem.R;

import java.util.ArrayList;

public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListAdapter.AdapterViewHolder> {
    private ArrayList<Community> communities;

    public void setCommunities(ArrayList<Community> communities) {
        this.communities = communities;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.community_rv, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.communityName.setText(communities.get(position).getName());
        holder.communityImage.setImageResource(R.drawable.profile_pic);
    }

    @Override
    public int getItemCount() {
        return communities.size();
    }

    protected class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView communityName;
        ImageView communityImage;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            communityName = itemView.findViewById(R.id.community_rv_name);
            communityImage = itemView.findViewById(R.id.community_rv_image);
        }
    }
}
