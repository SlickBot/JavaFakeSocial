package com.ulj.slicky.javafakesocial.activity.friends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulj.slicky.javafakesocial.R;

/**
 * Created by SlickyPC on 24.5.2017
 */
class FriendsViewHolder extends RecyclerView.ViewHolder {

    private final ImageView friendImage;
    private final TextView friendName;
    private final TextView friendInfo;

    FriendsViewHolder(View itemView) {
        super(itemView);

        friendImage = itemView.findViewById(R.id.friend_image);
        friendName = itemView.findViewById(R.id.friend_name);
        friendInfo = itemView.findViewById(R.id.friend_info);
    }

    ImageView getFriendImage() {
        return friendImage;
    }

    TextView getFriendName() {
        return friendName;
    }

    TextView getFriendInfo() {
        return friendInfo;
    }
}
