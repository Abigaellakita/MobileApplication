package com.example.ebook_system.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.EditCategoryActivity;
import com.example.ebook_system.R;
import com.example.ebook_system.helper.Category;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowCategoriesAdapter extends RecyclerView.Adapter<ShowCategoriesAdapter.ViewHolder> {
    Context context;
    List<Category> categoryList;
    DBHelper DB;
    public ShowCategoriesAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        DB = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show_categories_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.cat_Id.setText(Integer.toString(category.getCat_id()));
        holder.cat_name.setText(category.getName());

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditCategoryActivity.class);
                i.putExtra("cat_name", categoryList.get(position).getName());
                i.putExtra("id", categoryList.get(position).getCat_id());
                context.startActivity(i);
                //String strName = holder.cat_name.getText().toString();
                //DB.updateCat(new Category(category.getCat_id(), strName));

                //notifyDataSetChanged();
                //((Activity) context).finish();
                //context.startActivity(((Activity) context).getIntent());

                //Intent intent = new Intent(context, EditCategoryActivity.class);
                //intent.putExtra("name", strName);
                //context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deleteCategory(category.getCat_id());
                categoryList.remove(position);
                notifyDataSetChanged();


            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cat_Id, cat_name;
        //EditText cat_name;
        Button editBtn, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_Id = itemView.findViewById(R.id.cat_id);
            cat_name = itemView.findViewById(R.id.cat_name);
            editBtn = itemView.findViewById(R.id.edit);
            deleteBtn = itemView.findViewById(R.id.delete);
        }
    }

}
