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

import com.example.ebook_system.helper.Category;
import com.example.ebook_system.helper.DBHelper;

public class InsertCategoryActivity extends AppCompatActivity {
    EditText category_name;
    Button insertBtn, viewBtn;
    ImageView back_Btn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_category);
        DB = new DBHelper(InsertCategoryActivity.this);
        category_name = findViewById(R.id.cat_Name);
        insertBtn = findViewById(R.id.insert_btn);
        viewBtn = findViewById(R.id.view_btn);
        back_Btn = findViewById(R.id.back_Btn);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertCategoryActivity.this, ShowLanguagesActivity.class);
                startActivity(intent);
            }
        });
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Category_name = category_name.getText().toString();
                if(TextUtils.isEmpty(Category_name)){
                    category_name.setError("Name field is required");
                    return;
                }
                else {

                    Category category = new Category(Category_name);
                    Boolean checkIfCategoryNameExists = DB.checkCategory(category);
                    if(!checkIfCategoryNameExists){
                        Boolean insert = DB.createCategory(category);
                        if(insert){
                            Toast.makeText(InsertCategoryActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(getIntent());
                        }
                        else {
                            Toast.makeText(InsertCategoryActivity.this, "Insertion failed", Toast.LENGTH_SHORT ).show();

                        }

                    }else{
                        Toast.makeText(InsertCategoryActivity.this, "Category " + Category_name + " already exists", Toast.LENGTH_SHORT ).show();

                    }


                }
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertCategoryActivity.this, ShowCategoriesActivity.class);
                startActivity(intent);


            }
        });
    }
}