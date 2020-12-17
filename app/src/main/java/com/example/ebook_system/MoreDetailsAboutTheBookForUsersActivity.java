package com.example.ebook_system;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MoreDetailsAboutTheBookForUsersActivity extends AppCompatActivity {
    TextView book_det_title, book_det_desc, book_det_isbn;
    ImageView book_image1, back_btn1;
    String title, desc, isbn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details_about_the_book_for_users);
        book_det_title = findViewById(R.id.book_det_title);
        book_det_desc = findViewById(R.id.book_det_desc);
        book_det_isbn = findViewById(R.id.book_det_isbn);
        book_image1 = findViewById(R.id.book_image1);
        back_btn1=findViewById(R.id.back_btn1);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        desc = intent.getStringExtra("desc");
        isbn = intent.getStringExtra("isbn");

        Bitmap bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("image"), 0, intent.getByteArrayExtra("image").length);
        book_image1.setImageBitmap(bitmap);

        if(intent.hasExtra("image")){
            ImageView imageView = new ImageView(this);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("image"), 0, intent.getByteArrayExtra("image").length);
            imageView.setImageBitmap(bitmap1);
        }
        //Bitmap image = intent.getParcelableExtra("image");
        book_det_title.setText(title);
        book_det_desc.setText(desc);
        book_det_isbn.setText(isbn);
        //book_image1.setImageBitmap(image);
        back_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MoreDetailsAboutTheBookForUsersActivity.this, DashboardActivity.class );
                startActivity(intent1);
            }
        });


    }
}