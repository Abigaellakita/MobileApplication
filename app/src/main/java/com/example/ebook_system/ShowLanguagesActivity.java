package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ShowLanguagesActivity extends AppCompatActivity {
    CardView add_cat, add_language, add_author, add_book, cardLogout, cardUserOrders;
    ImageView backButton;
    TextView user_email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_languages);
        add_cat = findViewById(R.id.add_cat);
        add_language=findViewById(R.id.add_language);
        add_author=findViewById(R.id.add_author);
        cardLogout=findViewById(R.id.cardLogout);
        cardUserOrders=findViewById(R.id.cardUserOrders);
        user_email=findViewById(R.id.userEmail_tv);
        Intent intent=getIntent();
        String userEmail = intent.getStringExtra("email");
        user_email.setText("Welcome " + userEmail);
        add_book=findViewById(R.id.add_book);
        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(ShowLanguagesActivity.this, "Logged Out successfully", Toast.LENGTH_SHORT).show();
            }
        });
        cardUserOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowLanguagesActivity.this, "No orders yet", Toast.LENGTH_SHORT).show();

            }
        });
        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowLanguagesActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

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