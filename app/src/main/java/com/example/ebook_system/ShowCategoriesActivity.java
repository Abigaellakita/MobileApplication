package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.adapter.ShowCategoriesAdapter;
import com.example.ebook_system.helper.Category;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowCategoriesActivity extends AppCompatActivity {
    RecyclerView book_categories;
    DBHelper DB;
    ImageView Back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);
        Back_btn = findViewById(R.id.Back_btn);
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowCategoriesActivity.this, ShowLanguagesActivity.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(ShowCategoriesActivity.this);
        book_categories = findViewById(R.id.recycler_show_all_categories);
        book_categories.setLayoutManager(new LinearLayoutManager(this));
        book_categories.setHasFixedSize(true);
        List<Category> categoryList = DB.getAllCategories();

        if(categoryList.size() > 0){
            ShowCategoriesAdapter showCategoriesAdapter = new ShowCategoriesAdapter(this,categoryList);
            book_categories.setAdapter(showCategoriesAdapter);

        }else {
            Toast.makeText(ShowCategoriesActivity.this, "No Categories in Database", Toast.LENGTH_SHORT).show();
        }
    }
}