package com.example.ebook_system.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowBooksByCategoryForUsersAdapter extends RecyclerView.Adapter<ShowBooksByCategoryForUsersAdapter.ShowBooksByCategoryForUsersViewHolder> {
    Context context;
    List<Book> bookList;
    DBHelper DB;
    @NonNull
    @Override
    public ShowBooksByCategoryForUsersAdapter.ShowBooksByCategoryForUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowBooksByCategoryForUsersAdapter.ShowBooksByCategoryForUsersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class ShowBooksByCategoryForUsersViewHolder extends RecyclerView.ViewHolder{

        public ShowBooksByCategoryForUsersViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
