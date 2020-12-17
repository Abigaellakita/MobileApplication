package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.helper.Language;

public class EditLanguageActivity extends AppCompatActivity {
    TextView textView13, textView19;
    Button UpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_language);
        UpdateButton = findViewById(R.id.edit_btn);
        textView13 = findViewById(R.id.language_Name);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);
        textView13.setText(name);
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(EditLanguageActivity.this);
                String strName = textView13.getText().toString();
                if (TextUtils.isEmpty(strName)){
                    textView13.setError("Required");
                    return;
                }
                else {
                    DB.updateLanguage(new Language(id, strName));
                    Intent intent1 = new Intent(EditLanguageActivity.this, ShowLanguageActivity.class);
                    startActivity(intent1);

                }

            }
        });
    }
}