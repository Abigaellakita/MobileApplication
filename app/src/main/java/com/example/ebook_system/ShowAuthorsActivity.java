package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.adapter.ShowAuthorsAdapter;
import com.example.ebook_system.helper.Author;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowAuthorsActivity extends AppCompatActivity {
    DBHelper DB;
    RecyclerView recycler_show_all_authors;
    ImageView Back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_authors);
        Back_btn = findViewById(R.id.Back_btn);
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAuthorsActivity.this, ShowLanguagesActivity.class);
                startActivity(intent);
            }
        });
        DB = new DBHelper(ShowAuthorsActivity.this);
        recycler_show_all_authors= findViewById(R.id.recycler_show_all_authors);

        recycler_show_all_authors.setLayoutManager(new LinearLayoutManager(this));
        recycler_show_all_authors.setHasFixedSize(true);
        List<Author> authorList = DB.getAllAuthors();

        if(authorList.size() > 0){
            ShowAuthorsAdapter showAuthorsAdapter = new ShowAuthorsAdapter( this, authorList);
            recycler_show_all_authors.setAdapter(showAuthorsAdapter);

        }else {
            Toast.makeText(ShowAuthorsActivity.this, "No Authors in Database", Toast.LENGTH_SHORT).show();
        }
    }
}