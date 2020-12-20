package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class ReadBookActivity extends AppCompatActivity {
    TextView textViewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        //textViewUrl = findViewById(R.id.textViewUrl);

        Intent i = getIntent();
        String url = i.getStringExtra("BookUrl");
        //textViewUrl.setText(url);
        //String name ="AISBook.pdf";

        PDFView pdfView = findViewById(R.id.pdf);
        pdfView.fromAsset(url).scrollHandle(new DefaultScrollHandle(this)).load();
        //pdfView.fromFile(new File(path)).defaultPage(pageNumber).onPageChange(this).load();
        /*

        File file = new File(url); // where "path" is a path to file on your sd card, example: /storage/sdcard0/yourFile.pdf
        //filename = file.getName();
        //setTitle(filename);
        PDFView pdfView  =(PDFView)findViewById(R.id.pdf);
        pdfView.fromFile(file).defaultPage(1).enableSwipe(true).load();
        pdfView.setVerticalScrollBarEnabled(true);*/


    }
}