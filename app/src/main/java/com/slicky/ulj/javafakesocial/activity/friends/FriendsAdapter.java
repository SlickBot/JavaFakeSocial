package com.slicky.ulj.javafakesocial.activity.friends;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.slicky.ulj.javafakesocial.FakeUtils;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.model.person.Person;
import com.squareup.picasso.Picasso;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SlickyPC on 24.5.2017
 */
class FriendsAdapter extends RecyclerView.Adapter<FriendsViewHolder> {

    private FriendsActivity activity;
    private RecyclerView recycler;

    private ArrayList<Person> friendsList;

    FriendsAdapter(FriendsActivity activity, RecyclerView recycler) {
        this.activity = activity;
        this.recycler = recycler;
        friendsList = new ArrayList<>();
    }

    void setFriends(List<Person> friends) {
        if (friends != null) {
            friendsList.clear();
            friendsList.addAll(friends);
            notifyDataSetChanged();
        }
    }

    @Override
    public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_item, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recycler.getChildLayoutPosition(view);
                Person friend = friendsList.get(itemPosition);
                activity.openFriendProfile(friend);
            }
        });

        return new FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendsViewHolder holder, int position) {
        Person friend = friendsList.get(position);

        holder.getFriendName().setText(FakeUtils.getFullPersonNameWithTitle(friend));
        holder.getFriendInfo().setText(FakeUtils.getPersonInfo(friend));
        Picasso.with(activity)
                .load(friend.getPicture().getMedium())
                .placeholder(R.drawable.ic_user)
                .transform(new CropCircleTransformation())
                .into(holder.getFriendImage());
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}
