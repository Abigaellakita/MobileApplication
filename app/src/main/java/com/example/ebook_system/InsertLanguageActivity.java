package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.helper.Language;

public class InsertLanguageActivity extends AppCompatActivity {
    EditText language_name;
    Button insertBtn, viewBtn;
    ImageView back_BTN;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_language);
        DB =new DBHelper(this);
        language_name=findViewById(R.id.cat_Name);
        insertBtn=findViewById(R.id.insert_btn);
        viewBtn=findViewById(R.id.view_btn);
        back_BTN = findViewById(R.id.back_BTN);
        back_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertLanguageActivity.this, ShowLanguagesActivity.class);
                startActivity(intent);
            }
        });
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertLanguage();
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InsertLanguageActivity.this, ShowLanguageActivity.class);
                startActivity(intent);
            }
        });

    }
    public void insertLanguage(){
        String name = language_name.getText().toString();
        if(TextUtils.isEmpty(name)){
            language_name.setError("Language Name is Required");
            return;
        }else {
            Language languageName = new Language(name);
            Boolean checkIfLanguagesExists = DB.checkLanguage(languageName);
            if(!checkIfLanguagesExists){
                Boolean insert = DB.createLanguage(languageName);
                if(insert){
                    Toast.makeText(this, "Data Successfully inserted", Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                }
                else {
                    Toast.makeText(this, "Failed to insert Data", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, name + " already exists", Toast.LENGTH_SHORT).show();
                startActivity(getIntent());
            }
        }

    }
}