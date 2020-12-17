package com.example.ebook_system;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

public class InsertBookActivity extends AppCompatActivity {

    EditText book_title, book_desc, book_release_year, book_price, book_languageId,
    book_categoryId, book_authorId, book_pages, book_isbn;
    ImageView book_image, back_btn;
    Button insert,show_books_data ;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    DBHelper DB;
    //Button show_books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        DB = new DBHelper(this);

        book_title = findViewById(R.id.books_title);
        book_desc = findViewById(R.id.books_desc);
        book_languageId = findViewById(R.id.books_language);
        book_price = findViewById(R.id.books_price);
        book_image = findViewById(R.id.book_image);
        back_btn = findViewById(R.id.back_btn);
        book_release_year = findViewById(R.id.books_release_year);
        book_categoryId = findViewById(R.id.books_category);
        book_authorId = findViewById(R.id.books_author);
        book_price = findViewById(R.id.books_price);
        book_isbn = findViewById(R.id.books_isbn);
        book_pages = findViewById(R.id.books_number_pages);
        insert = findViewById(R.id.insert);
        show_books_data = findViewById(R.id.show_books_data);
        show_books_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertBookActivity.this, ShowAllBooksActivity.class);
                startActivity(intent);
            }
        });

        /*Intent intent = getIntent();
        final String title = intent.getStringExtra(RecentlyViewedAdapter.KEY_TITLE);
        final String desc = intent.getStringExtra(RecentlyViewedAdapter.KEY_DESC);
        final String language = intent.getStringExtra(RecentlyViewedAdapter.KEY_LANGUAGE);
        final String price = intent.getStringExtra(RecentlyViewedAdapter.KEY_PRICE);
        final String image = intent.getStringExtra(RecentlyViewedAdapter.KEY_IMAGE);
        int image = intent.getIntExtra(RecentlyViewedAdapter."KEY_IMAGE");*/

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBook();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(InsertBookActivity.this, DashboardActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
    public void chooseImage(View bookImage){
        try {
            Intent bookImg = new Intent();
            bookImg.setType("image/*");
            bookImg.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(bookImg, PICK_IMAGE_REQUEST);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData() != null ){
                  imageFilePath=data.getData();
                  imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                  book_image.setImageBitmap(imageToStore);
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void createBook(){
        try {
            String title = book_title.getText().toString();
            String desc = book_desc.getText().toString();
            float price = Float.parseFloat(book_price.getText().toString());
            int language = Integer.parseInt(book_languageId.getText().toString());
            int pages = Integer.parseInt(book_pages.getText().toString());
            String year = book_release_year.getText().toString();
            String isbn = book_isbn.getText().toString();
            int author = Integer.parseInt(book_authorId.getText().toString());
            //long category = Long.parseLong(String.valueOf(Long.parseLong(book_categoryId.getText().toString())));
            int category = Integer.parseInt(book_categoryId.getText().toString());


            if(TextUtils.isEmpty(title)) {
                book_title.setError("Title Field is Required");
                return;
            }
            else if(TextUtils.isEmpty(desc)) {
                book_desc.setError("Description is required");
                return;
            }
            else if(price < 0) {
                book_price.setError("Price is required");
                return;
            }
            else if(language < 0) {
                book_languageId.setError("Language Id is required");
                return;
            }
            else if(pages < 0) {
                book_pages.setError("Number of pages is required");
                return;
            }
            else if(TextUtils.isEmpty(year)) {
                book_release_year.setError("Release year is required");
                return;
            }
            else if(TextUtils.isEmpty(isbn)) {
                book_isbn.setError("ISBN number is required");
                return;
            }
            else if(author < 0) {
                book_authorId.setError("Author Id is required");
                return;
            }
            else if(category < 0) {
                book_categoryId.setError("Category Id is required");
                return;
            }
            else if(book_image.getDrawable() == null && imageToStore ==null){
                Toast.makeText(this, "Book Image is required", Toast.LENGTH_SHORT).show();
            }
            else {
                DB.insertBook(new Book(title,desc,year,pages,price,isbn,imageToStore, category,author,language));
                Toast.makeText(InsertBookActivity.this, "", Toast.LENGTH_SHORT).show();
                startActivity(getIntent());
                //Category cat = new Category("Romance");
                //long book_category = DB.createCategory(cat);
                //Author author1 = new Author("Joyce", "Meyer");
                //DB.createCategory(new Category("Romance"));
               // DB.createBook(new Book(title, desc, year, pages, price,isbn, imageToStore, {})) ;
            }

        }catch (Exception e){
            Toast.makeText(this, "Please Select an Image", Toast.LENGTH_SHORT).show();
        }
    }

}