package com.slicky.ulj.javafakesocial.activity.content;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.slicky.ulj.javafakesocial.FakeUtils;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.model.content.Content;
import com.slicky.ulj.javafakesocial.model.person.Person;
import com.squareup.picasso.Picasso;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SlickyPC on 19.5.2017
 */
class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {

    private ContentActivity activity;
    private RecyclerView recycler;

    private List<Content> contents;

    ContentAdapter(ContentActivity activity, RecyclerView recycler) {
        this.activity = activity;
        this.recycler = recycler;
        contents = new ArrayList<>();
    }

    void setContent(List<Content> contents) {
        this.contents = contents;
        notifyDataSetChanged();
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_item, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recycler.getChildLayoutPosition(view);
                Content content = contents.get(itemPosition);
                activity.openDetails(content);
            }
        });

        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        Content content = contents.get(position);
        Person owner = content.getOwner();
        Date postedAt = new Date(content.getPostedAt());

        holder.getOwnerName().setText(FakeUtils.getFullPersonName(owner));
        holder.getPostedAt().setText(FakeUtils.getFormattedWithTime(postedAt));
        holder.getContent().setText(content.getText());

        Picasso.with(activity)
                .load(owner.getPicture().getMedium())
                .placeholder(R.drawable.ic_user)
                .transform(new CropCircleTransformation())
                .into(holder.getOwnerImage());
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
