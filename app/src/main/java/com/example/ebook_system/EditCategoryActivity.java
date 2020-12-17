package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.Category;
import com.example.ebook_system.helper.DBHelper;

public class EditCategoryActivity extends AppCompatActivity {
    TextView textView13, textView19;
    Button UpdateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        UpdateButton = findViewById(R.id.edit_btn);
        textView13 = findViewById(R.id.cat_Name);
        Intent intent = getIntent();
        String name = intent.getStringExtra("cat_name");
        int id = intent.getIntExtra("id", 0);
        textView13.setText(name);
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(EditCategoryActivity.this);
                String strName = textView13.getText().toString();
                if (TextUtils.isEmpty(strName)){
                    textView13.setError("Required");
                    return;
                }
                else {
                    DB.updateCat(new Category(id, strName));
                    Intent intent1 = new Intent(EditCategoryActivity.this, ShowCategoriesActivity.class);
                    startActivity(intent1);

                }

            }
        });


    }
}