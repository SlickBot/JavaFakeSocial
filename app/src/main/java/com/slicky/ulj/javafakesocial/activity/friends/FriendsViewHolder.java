package com.slicky.ulj.javafakesocial.activity.friends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.slicky.ulj.javafakesocial.R;

/**
 * Created by SlickyPC on 24.5.2017
 */
class FriendsViewHolder extends RecyclerView.ViewHolder {

    private final ImageView friendImage;
    private final TextView friendName;
    private final TextView friendInfo;

    FriendsViewHolder(View itemView) {
        super(itemView);

        friendImage = (ImageView) itemView.findViewById(R.id.friend_image);
        friendName = (TextView) itemView.findViewById(R.id.friend_name);
        friendInfo = (TextView) itemView.findViewById(R.id.friend_info);
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
