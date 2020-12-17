package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    String title, desc, language, price, image;
    int imageUrl;
    TextView titleTv, descTv, languageTv, priceTv;
    ImageView book_image1,cart_imageV, back_btn, shopping_cart;
    Button buy_now;
    DBHelper DB;
    List<Book> bookList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        DB = new DBHelper(this);

        titleTv = findViewById(R.id.book_det_title);
        descTv = findViewById(R.id.book_det_desc);
        languageTv = findViewById(R.id.book_det_language);
        priceTv = findViewById(R.id.book_det_price);
        cart_imageV = findViewById(R.id.cart_imageV);
        back_btn = findViewById(R.id.back_btn1);
        shopping_cart= findViewById(R.id.shopping_cart);
        buy_now = findViewById(R.id.buy_now);

        book_image1 = findViewById(R.id.book_image1);

        bookList1 = DB.getAllBooksListForUsers();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        desc = intent.getStringExtra("desc");
        language=intent.getStringExtra("language");
        price=intent.getStringExtra("price");
        imageUrl = intent.getIntExtra("image", R.drawable.book7);

        titleTv.setText(title);
        descTv.setText(desc);
        languageTv.setText(language);
        priceTv.setText(price);

        book_image1.setImageResource(imageUrl);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProductDetailsActivity.this, DashboardActivity.class);
                startActivity(intent1);
                finish();
            }
        });



    }
}