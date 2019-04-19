package com.ulj.slicky.javafakesocial.activity.friends;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulj.slicky.javafakesocial.FakeUtils;
import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.model.person.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by SlickyPC on 24.5.2017
 */
class FriendsAdapter extends RecyclerView.Adapter<FriendsViewHolder> {

    private FriendsActivity activity;
    private RecyclerView recycler;

    private List<Person> friends;

    FriendsAdapter(FriendsActivity activity, RecyclerView recycler) {
        this.activity = activity;
        this.recycler = recycler;
        friends = new ArrayList<>();
    }

    void setFriends(List<Person> friends) {
        this.friends = friends;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_item, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recycler.getChildLayoutPosition(view);
                Person friend = friends.get(itemPosition);
                activity.openFriendProfile(friend);
            }
        });

        return new FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        Person friend = friends.get(position);

        holder.getFriendName().setText(FakeUtils.getFullPersonName(friend));
        holder.getFriendInfo().setText(FakeUtils.getPersonInfo(friend));
        Picasso.with(activity)
                .load(friend.getPicture().getMedium())
                .placeholder(R.drawable.ic_user)
                .transform(new CropCircleTransformation())
                .into(holder.getFriendImage());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

}
