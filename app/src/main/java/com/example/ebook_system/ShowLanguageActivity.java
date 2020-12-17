package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.adapter.ShowLanguagesAdapter;
import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.helper.Language;

import java.util.List;

public class ShowLanguageActivity extends AppCompatActivity {
    DBHelper DB;
    RecyclerView recycler_show_all_languages;
    ImageView Back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_language);
        Back_btn = findViewById(R.id.Back_btn);
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowLanguageActivity.this, ShowLanguagesActivity.class);
                startActivity(intent);
            }
        });
        DB = new DBHelper(this);
        recycler_show_all_languages=findViewById(R.id.recycler_show_all_languages);

        recycler_show_all_languages.setLayoutManager(new LinearLayoutManager(this));
        recycler_show_all_languages.setHasFixedSize(true);
        List<Language> languageList = DB.getAllLanguages();

        if(languageList.size() > 0){
            ShowLanguagesAdapter showLanguagesAdapter = new ShowLanguagesAdapter(this,languageList);
            recycler_show_all_languages.setAdapter(showLanguagesAdapter);

        }else {
            Toast.makeText(ShowLanguageActivity.this, "No Languages in Database", Toast.LENGTH_SHORT).show();
        }
    }
}