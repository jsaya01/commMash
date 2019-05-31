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
import com.example.android.findem.models.Match;
import com.example.android.findem.ui.ActiveFragments;
import com.example.android.findem.ui.messaging.MessageChatFragment;

import java.util.ArrayList;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.AdapterViewHolder> {
    private ArrayList<Match> matches;
    private Context context;
    private long uid;
    private FragmentManager fragmentManager;

    public void setState(ArrayList<Match> matches, Context context, long uid, FragmentManager fragmentManager) {
        this.matches = matches;
        this.context = context;
        this.uid = uid;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.message_overview_rv, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterViewHolder holder, final int position) {
        holder.firstName.setText(matches.get(position).getFname());
        holder.dt.setText(matches.get(position).getDt().toString());
        holder.lastMessage.setText(matches.get(position).getLastMessage());
        holder.profilePicture.setImageResource(R.drawable.profile_pic);

        holder.viewHolder.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putLong(context.getResources().getString(R.string.bundle_uid), uid);
            bundle.putSerializable(context.getResources().getString(R.string.bundle_match),
                    matches.get(holder.getAdapterPosition()));

            MessageChatFragment chatFragment = new MessageChatFragment();
            chatFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.master_activity_fragment, chatFragment);
            fragmentTransaction.addToBackStack(ActiveFragments.TAG_COMMUNITY_FRAGMENT);
            fragmentTransaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    protected class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        ImageView profilePicture;
        TextView lastMessage;
        TextView dt;
        ConstraintLayout viewHolder;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.message_rv_first_name_field_ep);
            profilePicture = itemView.findViewById(R.id.message_rv_profile_image_ep);
            lastMessage = itemView.findViewById(R.id.message_rv_last_message);
            dt = itemView.findViewById(R.id.message_rv_dt_last_sent);
            viewHolder = itemView.findViewById(R.id.message_rv_holder);
        }
    }
}
