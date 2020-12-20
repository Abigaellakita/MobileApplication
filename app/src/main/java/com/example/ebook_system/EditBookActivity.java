package com.example.ebook_system;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

public class EditBookActivity extends AppCompatActivity {
    TextView book_title, book_desc, book_release_year, book_price, book_languageId,
            book_categoryId, book_authorId, book_pages, book_isbn, book_status, books_path;
    ImageView book_image, back_btn;
    Button updateButton,show_books_data ;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        DB = new DBHelper(this);

        book_title = findViewById(R.id.books_title);
        book_desc = findViewById(R.id.books_desc);
        book_languageId = findViewById(R.id.books_language);
        book_price = findViewById(R.id.books_price);
        book_image = findViewById(R.id.book_image);
        book_release_year = findViewById(R.id.books_release_year);
        book_categoryId = findViewById(R.id.books_category);
        book_authorId = findViewById(R.id.books_author);
        book_isbn = findViewById(R.id.books_isbn);
        book_pages = findViewById(R.id.books_number_pages);
        book_status=findViewById(R.id.books_status);
        books_path=findViewById(R.id.books_path);
        back_btn= findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(EditBookActivity.this, ShowAllBooksActivity.class);
                startActivity(intentBack);
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        String year = intent.getStringExtra("year");
        String isbn = intent.getStringExtra("isbn");
        String bookPath = intent.getStringExtra("BookUrl");
        int id = intent.getIntExtra("id", 0);
        int cat_id = intent.getIntExtra("category_id", 0);
        int language_id = intent.getIntExtra("language_id", 0);
        int pages = intent.getIntExtra("pages", 0);
        int author_id = intent.getIntExtra("author_id", 0);
        int status = intent.getIntExtra("status", 0);
        float rating = intent.getFloatExtra("rating", 0);
        float price = intent.getFloatExtra("price", 0);
        book_title.setText(title);
        book_desc.setText(desc);
        book_release_year.setText(year);
        book_isbn.setText(isbn);
        book_categoryId.setText(Integer.toString(cat_id));
        book_languageId.setText(Integer.toString(language_id));
        book_pages.setText(Integer.toString(pages));
        book_authorId.setText(Integer.toString(author_id));
        book_price.setText(Float.toString(price));
        book_status.setText(Integer.toString(status));
        books_path.setText(bookPath);

        Bitmap bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("image"), 0, intent.getByteArrayExtra("image").length);
        book_image.setImageBitmap(bitmap);

        updateButton = findViewById(R.id.edit);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String title = book_title.getText().toString();
                    String desc = book_desc.getText().toString();
                    float price = Float.parseFloat(book_price.getText().toString());
                    int language = Integer.parseInt(book_languageId.getText().toString());
                    int pages = Integer.parseInt(book_pages.getText().toString());
                    String year = book_release_year.getText().toString();
                    String isbn = book_isbn.getText().toString();
                    int author = Integer.parseInt(book_authorId.getText().toString());
                    int status = Integer.parseInt(book_status.getText().toString());
                    int category = Integer.parseInt(book_categoryId.getText().toString());
                    String book_Path = books_path.getText().toString();


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
                    else if(TextUtils.isEmpty(book_Path)){
                        books_path.setText(title+".pdf");
                    }
                    /*else if(book_image.getDrawable() == null || imageToStore ==null){
                        Toast.makeText(EditBookActivity.this, "Book Image is required", Toast.LENGTH_SHORT).show();
                    }*/
                    else {
                        DB.updateBook(new Book(id, title,desc,year,pages,price,isbn,status,imageToStore, category,author,language, book_Path));
                        Toast.makeText(EditBookActivity.this, "", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(EditBookActivity.this, ShowAllBooksActivity.class);
                        startActivity(intent1);
                        //Category cat = new Category("Romance");
                        //long book_category = DB.createCategory(cat);
                        //Author author1 = new Author("Joyce", "Meyer");
                        //DB.createCategory(new Category("Romance"));
                        // DB.createBook(new Book(title, desc, year, pages, price,isbn, imageToStore, {})) ;
                    }

                }catch (Exception e){
                    Toast.makeText(EditBookActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
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



}