package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ShowLanguagesActivity extends AppCompatActivity {
    Button add_cat, add_language, add_author, add_book;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_languages);
        add_cat = findViewById(R.id.add_cat);
        add_language=findViewById(R.id.add_language);
        add_author=findViewById(R.id.add_author);
        add_book=findViewById(R.id.add_book);

        /*Intent intent = getIntent();
        String name = intent.getStringExtra("cat_name");
        int id = intent.getIntExtra("id", 0);
        textView19.setText(Integer.toString(id));*/




        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBook = new Intent(ShowLanguagesActivity.this, InsertBookActivity.class);
                startActivity(intentBook);
            }
        });
        add_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCat = new Intent(ShowLanguagesActivity.this, InsertCategoryActivity.class);
                startActivity(intentCat);
            }
        });
        add_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAuthor = new Intent(ShowLanguagesActivity.this, InsertAuthorActivity.class);
                startActivity(intentAuthor);
            }
        });
        add_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLanguage = new Intent(ShowLanguagesActivity.this, InsertLanguageActivity.class);
                startActivity(intentLanguage);
            }
        });
    }
}