package com.ulj.slicky.javafakesocial.activity.content;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulj.slicky.javafakesocial.R;

/**
 * Created by SlickyPC on 19.5.2017
 */
class ContentViewHolder extends RecyclerView.ViewHolder {

    private final ImageView ownerImage;
    private final TextView ownerName;
    private final TextView postedAt;
    private final TextView content;

    ContentViewHolder(View itemView) {
        super(itemView);

        ownerImage = itemView.findViewById(R.id.content_owner_image);
        ownerName = itemView.findViewById(R.id.content_owner_name);
        postedAt = itemView.findViewById(R.id.content_posted_at);
        content = itemView.findViewById(R.id.content_content);
    }

    ImageView getOwnerImage() {
        return ownerImage;
    }

    TextView getOwnerName() {
        return ownerName;
    }

    TextView getPostedAt() {
        return postedAt;
    }

    TextView getContent() {
        return content;
    }

}
