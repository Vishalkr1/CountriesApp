package com.example.countriesapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.Photo;

import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<Photo> photoList;

    public GalleryAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public void updatePhotos(List<Photo> newPhotos){
        photoList.clear();
        photoList.addAll(newPhotos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_photo, parent, false);

        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.bind(photoList.get(position));

    }


    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        ImageView photos;

        TextView title;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            photos = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }

        void bind(Photo photo)
        {
            title.setText(photo.getTitle());
            Util.loadImage(photos, photo.getUrlS(), Util.getProgressDrawable(photos.getContext()));
        }
    }

}
