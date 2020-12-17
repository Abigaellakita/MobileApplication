package com.example.ebook_system.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.ProductDetailsActivity;
import com.example.ebook_system.R;
import com.example.ebook_system.model.RecentlyViewed;

import java.util.List;

public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedViewHolder> {
    Context context;
    List<RecentlyViewed> recentlyViewedList;

    public Integer imageUrl;


    public RecentlyViewedAdapter(Context context, List<RecentlyViewed> recentlyViewedList) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
    }
    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewed_items, parent, false);

        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedAdapter.RecentlyViewedViewHolder holder, int position) {


        holder.title.setText(recentlyViewedList.get(position).getTitle());
        holder.ImageV.setBackgroundResource(recentlyViewedList.get(position).getImageUrl());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProductDetailsActivity.class);
                i.putExtra("title", recentlyViewedList.get(position).getTitle());
                i.putExtra("desc", recentlyViewedList.get(position).getDescription());
                i.putExtra("language", recentlyViewedList.get(position).getLanguage());
                i.putExtra("price", recentlyViewedList.get(position).getPrice());
                i.putExtra("image", recentlyViewedList.get(position).getImageUrl());
                context.startActivity(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        return recentlyViewedList.size();
    }

    public static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{
        TextView title, description, price, language;
        ImageView ImageV;
        ConstraintLayout bg;
        //LinearLayout linearLayout;

        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            ImageV = itemView.findViewById(R.id.recently_viewed_image);

        }
    }

}
