package com.spinproducts.freshpics.uiutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spinproducts.freshpics.R;
import com.spinproducts.freshpics.models.Picture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Picture> pictureList;

    public PictureAdapter(Context context, ArrayList<Picture> pictureList) {

        this.context = context;
        this.pictureList = pictureList;
    }

    @NonNull
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_card, null);
        return new ViewHolder(productCard);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.ViewHolder holder, int position) {

        Picture picture = pictureList.get(position);

        holder.tvTitle.setText(picture.getTitle());

        String pictureUrl = picture.getUrl();
        Picasso.with(context).load(pictureUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fit()
                .into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {

        if (pictureList != null) {
            return pictureList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView ivPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            ivPicture = itemView.findViewById(R.id.iv_picture);
        }
    }
}
