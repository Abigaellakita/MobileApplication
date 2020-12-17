package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.adapter.ShowBooksAdapter;
import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowAllBooksActivity extends AppCompatActivity {
    private RecyclerView books_recycler;
    private DBHelper DB;
    ImageView back_btn;
    //private ShowBooksAdapter showBooksAdapter;
    //Button show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_books);
        back_btn=findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAllBooksActivity.this, ShowLanguagesActivity.class);
                startActivity(intent);
            }
        });

        try {

        DB = new DBHelper(ShowAllBooksActivity.this);
        books_recycler = findViewById(R.id.books_recycler);
        books_recycler.setLayoutManager(new LinearLayoutManager(this));
        books_recycler.setHasFixedSize(true);
        List<Book> bookList = DB.getBooksList();

        if (bookList.size() > 0) {
            ShowBooksAdapter showBooksAdapter = new ShowBooksAdapter(this, bookList);
            books_recycler.setAdapter(showBooksAdapter);

        } else {
            Toast.makeText(ShowAllBooksActivity.this, "No Books in Database", Toast.LENGTH_SHORT).show();
        }
    }catch (Exception e){
            Toast.makeText(ShowAllBooksActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }

}