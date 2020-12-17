package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.adapter.ShowBooksForUsersAdapter;
import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowBooksUnderSingleCategoryActivity extends AppCompatActivity {
    RecyclerView booksUnderRomanceCatRecycler;
    TextView textView15;
    DBHelper DB;
    ShowBooksForUsersAdapter showBooksForUsersAdapter;
    List<Book> bookList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books_under_single_category);
        DB=  new DBHelper(this);
        booksUnderRomanceCatRecycler = findViewById(R.id.booksUnderSingleCategoryRecycler);
        textView15 = findViewById(R.id.textView15);

        Intent intent = getIntent();
        String title = intent.getStringExtra("cat_name");
        textView15.setText("All " + title + " Books");

        bookList1 =DB.getAllBooksByCategory(title);
        if (bookList1.size() > 0){
            setShowBooksByCategoryForUserRecycler(bookList1);
        }
        else {
            Toast.makeText(this, "No Books under " + title + " Category", Toast.LENGTH_SHORT).show();
        }


    }

    private void setShowBooksByCategoryForUserRecycler(List<Book> bookList ) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false );
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        booksUnderRomanceCatRecycler .setLayoutManager(layoutManager);
        showBooksForUsersAdapter = new ShowBooksForUsersAdapter(this, bookList);
        booksUnderRomanceCatRecycler .setAdapter(showBooksForUsersAdapter);
    }
}