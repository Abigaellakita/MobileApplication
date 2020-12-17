package com.example.ebook_system.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.R;
import com.example.ebook_system.ShowBooksUnderSingleCategoryActivity;
import com.example.ebook_system.helper.Category;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowCategoriesForUsersAdapter extends RecyclerView.Adapter<ShowCategoriesForUsersAdapter.CategoryViewHolder>{

    Context context;
    List<Category> categoryList;
    DBHelper DB;

    public ShowCategoriesForUsersAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        DB = new DBHelper(context);
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_books_items, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.textViewCategoryName.setText(categoryList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ShowBooksUnderSingleCategoryActivity.class);
                i.putExtra("cat_name", categoryList.get(position).getName());
                context.startActivity(i);



            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryName=itemView.findViewById(R.id.textViewCategoryName);


        }
    }
}
