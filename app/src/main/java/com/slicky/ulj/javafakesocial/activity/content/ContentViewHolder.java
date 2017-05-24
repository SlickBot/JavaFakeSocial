package com.slicky.ulj.javafakesocial.activity.content;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.slicky.ulj.javafakesocial.R;

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

        ownerImage = (ImageView) itemView.findViewById(R.id.owner_image);
        ownerName = (TextView) itemView.findViewById(R.id.owner_name);
        postedAt = (TextView) itemView.findViewById(R.id.posted_at);
        content = (TextView) itemView.findViewById(R.id.content);
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
