package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.Author;
import com.example.ebook_system.helper.DBHelper;

public class EditAuthorActivity extends AppCompatActivity {
    TextView Author_fName, Author_lName;
    Button UpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_author);
        Author_fName = findViewById(R.id.f_Name);
        Author_lName = findViewById(R.id.l_Name);
        UpdateButton = findViewById(R.id.edit_btn);
        Intent intent = getIntent();
        String fName = intent.getStringExtra("f_name");
        String lName = intent.getStringExtra("l_name");
        int id = intent.getIntExtra("id", 0);
        Author_fName.setText(fName);
        Author_lName.setText(lName);
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(EditAuthorActivity.this);
                String strFName = Author_fName.getText().toString();
                String strLName = Author_lName.getText().toString();
                if (TextUtils.isEmpty(strFName)){
                    Author_fName.setError("Required");
                    return;
                }
                else if (TextUtils.isEmpty(strLName)){
                    Author_lName.setError("Required");
                    return;
                }
                else {
                    DB.updateAuthor(new Author(id, strFName, strLName));
                    Intent intent1 = new Intent(EditAuthorActivity.this, ShowAuthorsActivity.class);
                    startActivity(intent1);

                }

            }
        });
    }
}