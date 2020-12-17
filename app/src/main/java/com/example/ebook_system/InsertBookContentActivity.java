package com.example.ebook_system;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.model.BookContent;

public class InsertBookContentActivity extends AppCompatActivity {
    EditText mPageTv, mChapterTv, mTitleTv, mDetailTv, mBook;
    Button save;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book_content);
        DB = new DBHelper(this);
        mPageTv = findViewById(R.id.editTextPageNo);
        mChapterTv = findViewById(R.id.editTextChapter);
        mTitleTv = findViewById(R.id.editTextTitle);
        mDetailTv = findViewById(R.id.editTextDetails);
        mBook = findViewById(R.id.editTextBook);
        save=findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBookContent();
            }
        });

    }
    public void saveBookContent(){


        try {

            String title = mTitleTv.getText().toString();
            String chapter = mChapterTv.getText().toString();
            String details = mDetailTv.getText().toString();
            int pageNo = Integer.parseInt(mPageTv.getText().toString());
            int book = Integer.parseInt(mBook.getText().toString());

            if(TextUtils.isEmpty(title)) {
                mTitleTv.setError("Title Field is Required");
                return;
            }
            if(pageNo < 0) {
                mPageTv.setError("Field is Required");
                return;
            }
            else if(TextUtils.isEmpty(chapter)) {
                mChapterTv.setError("Chapter is required");
                return;
            }
            else if(TextUtils.isEmpty(details)) {
                mDetailTv.setError("Details required");
                return;
            }
            else if(book < 0) {
                mBook.setError("Book Id is required");
                return;
            }

            else {
                DB.insertBookContent(new BookContent(pageNo,chapter,title,details,book));

            }

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}