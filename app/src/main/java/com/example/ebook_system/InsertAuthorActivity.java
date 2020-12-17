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

import com.example.ebook_system.helper.Author;
import com.example.ebook_system.helper.DBHelper;

public class InsertAuthorActivity extends AppCompatActivity {
    EditText author_fName, author_lName;
    Button insert_btn, view_btn;
    ImageView back_button;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_author);
        DB = new DBHelper(this);
        author_fName = findViewById(R.id.author_fName);
        author_lName = findViewById(R.id.author_lName);
        insert_btn = findViewById(R.id.insert_btn);
        view_btn=findViewById(R.id.view_btn);
        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertAuthorActivity.this, ShowAuthorsActivity.class );
                startActivity(intent);
            }
        });
        back_button=findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertAuthorActivity.this, ShowLanguagesActivity.class);
                startActivity(intent);
            }
        });
        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertAuthor();
            }
        });
    }
    public void insertAuthor(){
        String fName = author_fName.getText().toString();
        String lName= author_lName.getText().toString();
        if (TextUtils.isEmpty(fName)){
            author_fName.setError("Author First Name is required");
            return;
        }
        else if (TextUtils.isEmpty(lName)){
            author_lName.setError("Author Last Name is required");
            return;
        }else {
            Author author = new Author(fName, lName);
            Boolean checkIfAuthorExists = DB.checkAuthor(author);
            if(!checkIfAuthorExists){
                Boolean insert = DB.createAuthor(author);
                if(insert){
                    Toast.makeText(this, "Data Successfully inserted", Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                }
                else {
                    Toast.makeText(this, "Failed to insert Data", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Author under That Name already Exists", Toast.LENGTH_SHORT).show();
                startActivity(getIntent());
            }
        }
    }
}