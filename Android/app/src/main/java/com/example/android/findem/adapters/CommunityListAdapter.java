package com.example.android.findem.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.findem.R;
import com.example.android.findem.models.Community;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.ui.main_content.CommunityFragment;

import java.util.ArrayList;

public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListAdapter.AdapterViewHolder> {
    private ArrayList<Community> communities;
    private Context context;
    private int uid;
    private FragmentManager fragmentManager;

    public void setState(ArrayList<Community> communities, Context context, int uid, FragmentManager fragmentManager) {
        this.communities = communities;
        this.context = context;
        this.uid = uid;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.community_rv, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterViewHolder holder, final int position) {
        holder.communityName.setText(communities.get(position).getName());
        holder.communityImage.setImageResource(R.drawable.profile_pic);

        holder.viewHolder.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(context.getResources().getString(R.string.bundle_uid), uid);
            bundle.putSerializable(context.getResources().getString(R.string.bundle_community),
                    communities.get(holder.getAdapterPosition()));

            CommunityFragment communityFragment = new CommunityFragment();
            communityFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.master_activity_fragment, communityFragment);
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_COMMUNITY_FRAGMENT);
            fragmentTransaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return communities.size();
    }

    protected class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView communityName;
        ImageView communityImage;
        ConstraintLayout viewHolder;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            communityName = itemView.findViewById(R.id.community_rv_name);
            communityImage = itemView.findViewById(R.id.community_rv_image);
            viewHolder = itemView.findViewById(R.id.community_rv_parent);
        }
    }
}
