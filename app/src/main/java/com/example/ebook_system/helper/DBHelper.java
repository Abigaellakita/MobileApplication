package com.example.ebook_system.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = DBHelper.class.getName();
    Context context;
    static Context mCtx;
    //Database Name
    private static final String DBNAME = "EbookSystem.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    //Table Names

    public static final String TABLE_CATEGORY = "categories";
    public static final String TABLE_BOOK = "books";
    //public static final String TABLE_BOOK_CONTENT ="book_content";
    public static final String TABLE_AUTHOR ="authors";
    public static final String TABLE_LANGUAGE ="languages";
    public static final String TABLE_USER ="users";
    public static final String TABLE_CART_ITEMS = "cart_items";


    // Common column names

    public static final String KEY_LAST_UPDATED= "last_updated";
    public static final String KEY_CREATED_AT = "created_at";

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    //Users Table -columns names
    //public static final String KEY_USER_ID ="user_id";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_PASSWORD = "password";


    // USERS Table create statement

    private String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER
            + " (" + KEY_USER_EMAIL + " TEXT PRIMARY KEY," +
            KEY_USER_PASSWORD + " TEXT," +
            KEY_LAST_UPDATED + " DATETIME," +
            KEY_CREATED_AT + " DATETIME )";
    //Drop table users
    private final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER;
    //CATEGORY Table - column names
    public static final String KEY_CATEGORY_ID = "category_id";
    public static final String KEY_CATEGORY_NAME = "category_name";

    // CART_ITEMS Table columns
    public static final  String KEY_ITEM_ID = "book_id";
    public static final String KEY_EMAIL = "user_email";

    //create table CART ITEMS statement
    private String CREATE_TABLE_CART_ITEMS = "CREATE TABLE " + TABLE_CART_ITEMS
            + " (" + KEY_ITEM_ID + " INTEGER REFERENCES " + TABLE_BOOK + " , " +
            KEY_EMAIL + " TEXT REFERENCES " + TABLE_USER + " , " +
            KEY_LAST_UPDATED + " DATETIME," +
            KEY_CREATED_AT + " DATETIME )";
    private final String DROP_TABLE_CART_ITEMS = "DROP TABLE IF EXISTS " + TABLE_CART_ITEMS;

    // Category table create statement
    private String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY
            + " (" + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_CATEGORY_NAME + " TEXT UNIQUE," +
            KEY_LAST_UPDATED + " DATETIME," +
            KEY_CREATED_AT + " DATETIME )";
    // Drop table category
    private final String DROP_TABLE_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_CATEGORY;

    //AUTHOR Table - column names
    public static final String KEY_AUTHOR_ID = "author_id";
    public static final String KEY_AUTHOR_fNAME = "first_name";
    public static final String KEY_AUTHOR_lNAME = "last_name";

    // Author table create statement
    private String CREATE_TABLE_AUTHOR = "CREATE TABLE " + TABLE_AUTHOR
            + " (" + KEY_AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_AUTHOR_fNAME + " TEXT," +
            KEY_AUTHOR_lNAME + " TEXT," +
            KEY_LAST_UPDATED + " DATETIME," +
            KEY_CREATED_AT + " DATETIME )";
    // Drop table Author
    private final String DROP_TABLE_AUTHOR = "DROP TABLE IF EXISTS " + TABLE_AUTHOR;

    //Language Table - column names
    public static final String KEY_LANGUAGE_ID = "language_id";
    public static final String KEY_LANGUAGE_NAME = "language_name";

    // Language table create statement
    private String CREATE_TABLE_LANGUAGE = "CREATE TABLE " + TABLE_LANGUAGE
            + " (" + KEY_LANGUAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_LANGUAGE_NAME + " TEXT UNIQUE," +
            KEY_LAST_UPDATED + " DATETIME," +
            KEY_CREATED_AT + " DATETIME )";
    // Drop table language
    private final String DROP_TABLE_LANGUAGE = "DROP TABLE IF EXISTS " + TABLE_LANGUAGE;

    //Book table -column names
    public static final String KEY_BOOK_ID = "book_id";
    public static final String KEY_STATUS = "status";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESC = "description";
    public static final String KEY_RELEASE_YEAR = "released_year";
    public static final String KEY_NUMBER_OF_PAGES = "no_pages";
    public static final String KEY_PRICE = "price";
    public static final String KEY_ISBN = "isbn";
    public static final String KEY_RATING = "rating";
    public static final String KEY_IMAGE = "book_image";
    public static final String KEY_CAT_ID = "category_id";
    public static final String AUTHOR_ID = "author_id";
    public static final String LANGUAGE_ID = "language_id";
    public static final String KEY_BOOK_CONTENT = "book_pdf";

    // Book table create statement
    private String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK
            + " (" + KEY_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_TITLE + " TEXT UNIQUE," +
            KEY_DESC + " TEXT," +
            KEY_RELEASE_YEAR + " Text," +
            KEY_NUMBER_OF_PAGES + " INTEGER," +
            KEY_PRICE + " REAL," +
            KEY_ISBN + " TEXT, " +
            KEY_RATING + " REAL, " +
            KEY_STATUS + " INTEGER, " +
            KEY_IMAGE + " BLOB, " +
            KEY_BOOK_CONTENT + " TEXT, " +
            KEY_LAST_UPDATED + " DATETIME," +
            KEY_CREATED_AT + " DATETIME," +
            AUTHOR_ID + " INTEGER REFERENCES " + TABLE_AUTHOR + " , " +
            LANGUAGE_ID + " INTEGER REFERENCES " + TABLE_LANGUAGE + " , " +
            KEY_CAT_ID + " INTEGER REFERENCES " + TABLE_CATEGORY +
             ");";


    // Drop table book
    private final String DROP_TABLE_BOOK = "DROP TABLE IF EXISTS " + TABLE_BOOK;

    //BookContent table -column names
    /*
    public static final String KEY_ID = "PageNo";
    public static final String KEY_CHAPTER = "Chapter";
    public static final String KEY_CHAPTER_TITLE = "Title";
    public static final String KEY_DETAILS = "DetailOfTitle";
    public static final String BOOK_ID = "book_id";

    // create table bookContent statement
    /*private String CREATE_TABLE_BOOK_CONTENT = "CREATE TABLE " + TABLE_BOOK_CONTENT
            + " (" + KEY_ID + " INTEGER," +
            KEY_CHAPTER + " TEXT," +
            KEY_CHAPTER_TITLE + " TEXT," +
            KEY_DETAILS + " TEXT," +
            BOOK_ID + " INTEGER REFERENCES " + TABLE_BOOK +
            ");";
    // Drop table bookContent
    private final String DROP_TABLE_BOOK_CONTENT = "DROP TABLE IF EXISTS " + TABLE_BOOK_CONTENT;*/




    public DBHelper(Context context){
        super(context, DBNAME, null, 19);
        this.context = context;
        mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        //myDB.execSQL("create Table users(email TEXT primary key, password TEXT)");
        myDB.execSQL(CREATE_TABLE_USER);
        myDB.execSQL(CREATE_TABLE_CATEGORY);
        myDB.execSQL(CREATE_TABLE_BOOK);
        //myDB.execSQL(CREATE_TABLE_BOOK_CONTENT);
        myDB.execSQL(CREATE_TABLE_AUTHOR);
        myDB.execSQL(CREATE_TABLE_LANGUAGE);
        myDB.execSQL(CREATE_TABLE_CART_ITEMS);



    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        //myDB.execSQL("drop Table if exists users");
        myDB.execSQL(DROP_TABLE_USER);
        myDB.execSQL(DROP_TABLE_CATEGORY);
        myDB.execSQL(DROP_TABLE_BOOK);
        myDB.execSQL("drop Table if exists users1");
        myDB.execSQL("drop Table if exists book_content");
        myDB.execSQL(DROP_TABLE_AUTHOR);
        myDB.execSQL(DROP_TABLE_LANGUAGE);
        myDB.execSQL(DROP_TABLE_CART_ITEMS);


        //create new tables
        onCreate(myDB);

    }
    /*public Boolean insertData(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        long result = myDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;


    }*/
    public Boolean insertCartItem(int id, String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ITEM_ID, id);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_CREATED_AT, getDateTime());
        contentValues.put(KEY_LAST_UPDATED, getDateTime());
        long result = myDB.insert(TABLE_CART_ITEMS, null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean checkCartItem(int id, String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String selectQuery ="SELECT  * FROM " + TABLE_CART_ITEMS + " WHERE " + KEY_ITEM_ID + " = ?" + " AND "
                + KEY_EMAIL + " =? ";
        //Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[] {email});
        Cursor cursor = myDB.rawQuery(selectQuery, new String[] {String.valueOf(id), email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean insertData(User user){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_EMAIL, user.getEmail());
        contentValues.put(KEY_USER_PASSWORD, user.getPassword());
        contentValues.put(KEY_CREATED_AT, getDateTime());
        contentValues.put(KEY_LAST_UPDATED, getDateTime());
        long result = myDB.insert(TABLE_USER, null, contentValues);
        if(result==-1) return false;
        else
            return true;


    }
    public Boolean updatePassword(User user){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_PASSWORD, user.getPassword());
        contentValues.put(KEY_LAST_UPDATED, getDateTime());
        long result = myDB.update(TABLE_USER, contentValues, KEY_USER_EMAIL + " = ?" , new String[] {user.getEmail()} );
        if(result==-1) return false;
        else
            return true;
    }
    /*public Boolean updatePassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password", password);
        long result = myDB.update("users", contentValues, "email = ?" , new String[] {email} );
        if(result==-1) return false;
        else
            return true;
    }*/
    /*public Boolean checkEmail(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }*/
    public Boolean checkEmail(User user){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sqlQuery = " SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER_EMAIL + " = ? ";
        Cursor cursor = myDB.rawQuery(sqlQuery, new String[] {user.getEmail()});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean checkEmailPassword(User user){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sqlQuery = " SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER_EMAIL + " = ? AND "
                + KEY_USER_PASSWORD + " = ? ";
        Cursor cursor = myDB.rawQuery(sqlQuery, new String[]{user.getEmail(), user.getPassword()});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;

    }
    /*public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ? and password = ?", new String[]{email, password});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;

    }*/
    /*
     * Creating a book
     */

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    /*
     * Creating category
     */
    //long
    public Boolean createCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_CATEGORY_NAME, category.getName());
        values.put(DBHelper.KEY_CREATED_AT, getDateTime());
        values.put(DBHelper.KEY_LAST_UPDATED, getDateTime());

        // insert row
        long category_id = db.insert(DBHelper.TABLE_CATEGORY, null, values);
        if(category_id==-1){
            return false;
        }else {
            return true;
        }

        //return category_id;
    }
    public Boolean createAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_AUTHOR_fNAME, author.getFirst_name());
        values.put(DBHelper.KEY_AUTHOR_lNAME, author.getLast_name());
        values.put(DBHelper.KEY_CREATED_AT, getDateTime());
        values.put(DBHelper.KEY_LAST_UPDATED, getDateTime());

        // insert row
        long author_id = db.insert(DBHelper.TABLE_AUTHOR, null, values);
        if(author_id==-1){
            return false;
        }else {
            return true;
        }

        //return category_id;
    }
    public Boolean createLanguage(Language language) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_LANGUAGE_NAME, language.getName());
        values.put(DBHelper.KEY_CREATED_AT, getDateTime());
        values.put(DBHelper.KEY_LAST_UPDATED, getDateTime());

        // insert row
        long language_id = db.insert(DBHelper.TABLE_LANGUAGE, null, values);
        if(language_id==-1){
            return false;
        }else {
            return true;
        }

        //return category_id;
    }
    /**
     * check if category exists
     * */

    public Boolean checkCategory(Category category){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String selectQuery ="SELECT  * FROM " + TABLE_CATEGORY + " WHERE " + KEY_CATEGORY_NAME + " = ?";
        //Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[] {email});
        Cursor cursor = myDB.rawQuery(selectQuery, new String[] {category.getName()});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean checkLanguage(Language language){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String selectQuery ="SELECT  * FROM " + TABLE_LANGUAGE + " WHERE " + KEY_LANGUAGE_NAME + " = ?";
        //Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[] {email});
        Cursor cursor = myDB.rawQuery(selectQuery, new String[] {language.getName()});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean checkAuthor(Author author){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String selectQuery ="SELECT  * FROM " + TABLE_AUTHOR + " WHERE " + KEY_AUTHOR_fNAME + " = ?" + " AND "
                + KEY_AUTHOR_lNAME + " =? ";
        //Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[] {email});
        Cursor cursor = myDB.rawQuery(selectQuery, new String[] {author.getFirst_name(), author.getLast_name()});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    /**
     * getting all categories
     * */
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Category t = new Category();
                t.setCat_id(c.getInt((c.getColumnIndex(KEY_CATEGORY_ID))));
                t.setName(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));

                // adding to categories list
                categories.add(t);
            } while (c.moveToNext());
        }
        c.close();
        return categories;
    }

    public List<Language> getAllLanguages() {
        List<Language> languages = new ArrayList<Language>();
        String selectQuery = "SELECT  * FROM " + TABLE_LANGUAGE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Language t = new Language();
                t.setLanguage_id(c.getInt((c.getColumnIndex(KEY_LANGUAGE_ID))));
                t.setName(c.getString(c.getColumnIndex(KEY_LANGUAGE_NAME)));

                // adding to languages list
                languages.add(t);
            } while (c.moveToNext());
        }
        c.close();
        return languages;
    }
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<Author>();
        String selectQuery = "SELECT  * FROM " + TABLE_AUTHOR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Author t = new Author();
                t.setAuthor_id(c.getInt((c.getColumnIndex(KEY_AUTHOR_ID))));
                t.setFirst_name(c.getString(c.getColumnIndex(KEY_AUTHOR_fNAME)));
                t.setLast_name(c.getString(c.getColumnIndex(KEY_AUTHOR_lNAME)));

                // adding to languages list
                authors.add(t);
            } while (c.moveToNext());
        }
        c.close();
        return authors;
    }
    public List<Category> getAllCategoriesForUsers() {
        List<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT " + KEY_CATEGORY_NAME + " FROM " + TABLE_CATEGORY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Category t = new Category();
                //t.setCat_id(c.getInt((c.getColumnIndex(KEY_CATEGORY_ID))));
                t.setName(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));

                // adding to categories list
                categories.add(t);
            } while (c.moveToNext());
        }
        c.close();
        return categories;
    }
    /*
     * Updating a category
     */

    public void updateCat(Category category){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_NAME, category.getName());
        values.put(KEY_LAST_UPDATED, getDateTime());

        try {
            db.update(TABLE_CATEGORY, values, KEY_CATEGORY_ID + " = ?",
                    new String[] { String.valueOf(category.getCat_id()) });
            Toast.makeText(context, "Category name updated successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Failed to update, Category name must be unique", Toast.LENGTH_SHORT).show();
        }

    }
    public void updateBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStoreBitmap = book.getImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_DESC, book.getDesc());
        values.put(KEY_RELEASE_YEAR, book.getRelease_year());
        values.put(KEY_NUMBER_OF_PAGES, book.getNumber_of_pages());
        values.put(KEY_PRICE, book.getPrice());
        values.put(KEY_ISBN, book.getISBN());
        values.put(KEY_STATUS, book.getStatus());
        values.put(KEY_IMAGE, imageInBytes);
        values.put(KEY_LAST_UPDATED, getDateTime());
        values.put(KEY_CAT_ID, book.getCat_id());
        values.put(AUTHOR_ID, book.getAuthor_id());
        values.put(LANGUAGE_ID, book.getLanguage_id());
        values.put(KEY_BOOK_CONTENT, book.getBook_url());


        try {
            db.update(TABLE_BOOK, values, KEY_BOOK_ID + " = ?",
                    new String[] { String.valueOf(book.getBook_id()) });
            Toast.makeText(context, "Book updated successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Failed to update, Book must be unique", Toast.LENGTH_SHORT).show();
        }

    }
    public void updateLanguage(Language language){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LANGUAGE_NAME, language.getName());
        values.put(KEY_LAST_UPDATED, getDateTime());

        try {
            db.update(TABLE_LANGUAGE, values, KEY_LANGUAGE_ID + " = ?",
                    new String[] { String.valueOf(language.getLanguage_id()) });
            Toast.makeText(context, "Language name updated successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Failed to update, Language name must be unique", Toast.LENGTH_SHORT).show();
        }

    }
    public void updateAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AUTHOR_fNAME, author.getFirst_name());
        values.put(KEY_AUTHOR_lNAME, author.getLast_name());
        values.put(KEY_LAST_UPDATED, getDateTime());

        try {
            db.update(TABLE_AUTHOR, values, KEY_AUTHOR_ID + " = ?",
                    new String[] { String.valueOf(author.getAuthor_id()) });
            Toast.makeText(context, "Author name updated successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Failed to update, author names must be unique", Toast.LENGTH_SHORT).show();
        }

    }
    /*
     * Deleting a category
     */
    public void deleteCategory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_CATEGORY, KEY_CATEGORY_ID + " = ?",
                    new String[] { String.valueOf(id) });
            Toast.makeText(context, "Category Name deleted and all books under that Category", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Couldn't delete this Category Name", Toast.LENGTH_SHORT).show();
        }


    }
    public void deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_BOOK, KEY_BOOK_ID + " = ?",
                    new String[] { String.valueOf(id) });
            Toast.makeText(context, "Book deleted", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Couldn't delete this Book", Toast.LENGTH_SHORT).show();
        }

    }
    public void removeCartItem(int id, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        try{

            db.delete(TABLE_CART_ITEMS, KEY_ITEM_ID + " = ?" + " AND " + KEY_EMAIL + " = ?",
                    new String[] { String.valueOf(id), email });
            Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Couldn't remove item", Toast.LENGTH_SHORT).show();
        }

    }
    public void deleteLanguage(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_LANGUAGE, KEY_LANGUAGE_ID + " = ?",
                    new String[] { String.valueOf(id) });
            Toast.makeText(context, "Language Name deleted and all books under that Language", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Error: Couldn't delete this Language Name", Toast.LENGTH_SHORT).show();
        }


    }
    public void deleteAuthor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_AUTHOR, KEY_AUTHOR_ID + " = ?",
                    new String[] { String.valueOf(id) });
            Toast.makeText(context, "Author deleted and all books under that Author", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Error: Couldn't delete Author", Toast.LENGTH_SHORT).show();
        }


    }
    public void insertBook(Book book){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = book.getImage();
            byteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            imageInBytes = byteArrayOutputStream.toByteArray();
            //bookPdfBytes = byteArrayOutputStream.toByteArray(book.getBook_url());

            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, book.getTitle());
            values.put(KEY_DESC, book.getDesc());
            values.put(KEY_RELEASE_YEAR, book.getRelease_year());
            values.put(KEY_NUMBER_OF_PAGES, book.getNumber_of_pages());
            values.put(KEY_PRICE, book.getPrice());
            values.put(KEY_ISBN, book.getISBN());
            values.put(KEY_RATING, book.getRating());
            values.put(KEY_STATUS, book.getStatus());
            values.put(KEY_IMAGE, imageInBytes);
            values.put(KEY_LAST_UPDATED, getDateTime());
            values.put(KEY_CREATED_AT, getDateTime());
            values.put(KEY_CAT_ID, book.getCat_id());
            values.put(AUTHOR_ID, book.getAuthor_id());
            values.put(LANGUAGE_ID, book.getLanguage_id());
            values.put(KEY_BOOK_CONTENT, book.getBook_url());


            long checkIfQueryRuns = db.insert(DBHelper.TABLE_BOOK, null, values);
            if(checkIfQueryRuns !=-1){
                Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();
                db.close();
            }
            else {
                Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){
            Toast.makeText(context, "Could not insert Data, check if if you have selected the Image", Toast.LENGTH_SHORT).show();
        }

    }

    public void insertBooks(Book book){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = book.getImage();
            byteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            imageInBytes = byteArrayOutputStream.toByteArray();

            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, book.getTitle());
            values.put(KEY_DESC, book.getDesc());
            values.put(KEY_RELEASE_YEAR, book.getRelease_year());
            values.put(KEY_NUMBER_OF_PAGES, book.getNumber_of_pages());
            values.put(KEY_PRICE, book.getPrice());
            values.put(KEY_ISBN, book.getISBN());
            values.put(KEY_RATING, book.getRating());
            values.put(KEY_STATUS, book.getStatus());
            values.put(KEY_IMAGE, imageInBytes);
            values.put(KEY_LAST_UPDATED, getDateTime());
            values.put(KEY_CREATED_AT, getDateTime());
            values.put(KEY_CAT_ID, book.getCat_id());
            values.put(AUTHOR_ID, book.getAuthor_id());
            values.put(LANGUAGE_ID, book.getLanguage_id());


            long checkIfQueryRuns = db.insert(DBHelper.TABLE_BOOK, null, values);
            if(checkIfQueryRuns !=-1){
                Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();
                db.close();
            }
            else {
                Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){
            Toast.makeText(context, "Could not insert Data, check if if you have selected the Image", Toast.LENGTH_SHORT).show();
        }

    }
    public List<Book> getBooksList(){
        List<Book> books = new ArrayList<Book>();
        //String selectQuery = "SELECT " + KEY_BOOK_ID + ", " + KEY_TITLE + ", " + KEY_IMAGE + " FROM " + TABLE_BOOK;
        String selectQuery = "SELECT * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                Book b = new Book();

                b.setBook_id(c.getInt(c.getColumnIndex(KEY_BOOK_ID)));
                b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setRelease_year(c.getString(c.getColumnIndex(KEY_RELEASE_YEAR)));
                b.setNumber_of_pages(c.getInt(c.getColumnIndex(KEY_NUMBER_OF_PAGES)));
                b.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));
                b.setISBN(c.getString(c.getColumnIndex(KEY_ISBN)));
                b.setRating(c.getFloat(c.getColumnIndex(KEY_RATING)));
                b.setStatus(c.getInt(c.getColumnIndex(KEY_STATUS)));
                b.setLast_updated(c.getString(c.getColumnIndex(KEY_LAST_UPDATED)));
                b.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                byte[] imageBytes = c.getBlob(c.getColumnIndex(KEY_IMAGE));
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                b.setImage(objectBitmap);
                b.setBook_url(c.getString(c.getColumnIndex(KEY_BOOK_CONTENT)));
                b.setAuthor_id(c.getInt(c.getColumnIndex(AUTHOR_ID)));
                b.setLanguage_id(c.getInt(c.getColumnIndex(LANGUAGE_ID)));
                b.setCat_id(c.getInt(c.getColumnIndex(KEY_CAT_ID)));

                books.add(b);
            }while (c.moveToNext());
        }
        c.close();
        return books;
    }
    public List<Book> getBooksListForUsers(){
        List<Book> books = new ArrayList<Book>();
        //String selectQuery = "SELECT " + KEY_BOOK_ID + ", " + KEY_TITLE + ", " + KEY_IMAGE + " FROM " + TABLE_BOOK;
        String selectQuery = "SELECT " + KEY_IMAGE
                + ", " + KEY_TITLE + ", "
                + KEY_ISBN + ", "
                + KEY_NUMBER_OF_PAGES + ", "
                + KEY_RELEASE_YEAR + ", "
                + KEY_PRICE + " FROM " + TABLE_BOOK;
        /*String selectQuery = "SELECT tb. " + KEY_IMAGE + ", tb. "
                + KEY_TITLE + ", tb. "
                + KEY_DESC + ",tb. " + KEY_NUMBER_OF_PAGES + ",tb. "
                + KEY_RELEASE_YEAR + ", tb. " + KEY_PRICE + ", tc. "
                + KEY_CATEGORY_NAME + " FROM " + TABLE_BOOK + " tb "
                + " INNER JOIN " + TABLE_CATEGORY + " tc " + " ON tc. "
                + KEY_CATEGORY_ID + " = tb." + KEY_CAT_ID ;*/
                //+ " WHERE tb. " + KEY_BOOK_ID + " =?" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                Book b = new Book();
                byte[] imageBytes = c.getBlob(c.getColumnIndex(KEY_IMAGE));
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                b.setImage(objectBitmap);
                b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                //b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setNumber_of_pages(c.getInt(c.getColumnIndex(KEY_NUMBER_OF_PAGES)));
                b.setRelease_year(c.getString(c.getColumnIndex(KEY_RELEASE_YEAR)));
                b.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));

                b.setISBN(c.getString(c.getColumnIndex(KEY_ISBN)));

                //b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));


                books.add(b);
            }while (c.moveToNext());
        }
        c.close();
        return books;
    }
    public List<Book> getBooksUsers(){
        List<Book> books = new ArrayList<Book>();
        List<Category> categories=new ArrayList<Category>();
        //String selectQuery = "SELECT " + KEY_BOOK_ID + ", " + KEY_TITLE + ", " + KEY_IMAGE + " FROM " + TABLE_BOOK;
        //String selectQuery = "SELECT " + KEY_IMAGE + ", " + KEY_TITLE + ", " + KEY_NUMBER_OF_PAGES + ", " + KEY_RELEASE_YEAR + ", " + KEY_PRICE + " FROM " + TABLE_BOOK;
        String selectQuery = "SELECT tb. " + KEY_IMAGE + ", tb. "
                + KEY_TITLE + ", tb. "
                + KEY_DESC + ",tb. " + KEY_ISBN + ", tb. " + KEY_NUMBER_OF_PAGES + ",tb. "
                + KEY_RELEASE_YEAR + ", tb. " + KEY_PRICE
                //+ ", tc. "
                //+ KEY_CATEGORY_NAME
                + ", ta. "
                + KEY_AUTHOR_fNAME
                + ", ta. "
                + KEY_AUTHOR_lNAME
                //+ ", tl. "
                //+ KEY_LANGUAGE_NAME
                + " FROM " + TABLE_BOOK + " tb "
                + " INNER JOIN " + TABLE_AUTHOR + " ta " + " ON tb. "
                + AUTHOR_ID + " = ta." + KEY_AUTHOR_ID;
                //+ " INNER JOIN "
                //+ TABLE_AUTHOR + "ta " + " ON tb. " + AUTHOR_ID + " = ta. " + KEY_AUTHOR_ID;
                //+ " INNER JOIN " + TABLE_LANGUAGE + "tl " + " ON tb. " + LANGUAGE_ID
                //+ " = tl. " + KEY_LANGUAGE_ID ;
                //+ " WHERE tb. " + KEY_BOOK_ID + " =?" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                Book b = new Book();

                byte[] imageBytes = c.getBlob(c.getColumnIndex(KEY_IMAGE));
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                b.setImage(objectBitmap);
                b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setNumber_of_pages(c.getInt(c.getColumnIndex(KEY_NUMBER_OF_PAGES)));
                b.setRelease_year(c.getString(c.getColumnIndex(KEY_RELEASE_YEAR)));
                b.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));
                b.setISBN(c.getString(c.getColumnIndex(KEY_ISBN)));
                //b.setCategory_name(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));
                b.setAuthorFName(c.getString(c.getColumnIndex(KEY_AUTHOR_fNAME)));
                b.setAuthorLName(c.getString(c.getColumnIndex(KEY_AUTHOR_lNAME)));
                //b.setLanguage(c.getString(c.getColumnIndex(KEY_LANGUAGE_NAME)));




                //b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                


                books.add(b);

            }while (c.moveToNext());
        }
        c.close();
        return books;
    }
    public List<Book> getBooksForUsers(){
        List<Book> books = new ArrayList<Book>();
        List<Category> categories=new ArrayList<Category>();
        //String selectQuery = "SELECT " + KEY_BOOK_ID + ", " + KEY_TITLE + ", " + KEY_IMAGE + " FROM " + TABLE_BOOK;
        //String selectQuery = "SELECT " + KEY_IMAGE + ", " + KEY_TITLE + ", " + KEY_NUMBER_OF_PAGES + ", " + KEY_RELEASE_YEAR + ", " + KEY_PRICE + " FROM " + TABLE_BOOK;
        String selectQuery = "SELECT tb. " + KEY_IMAGE + ", tb. "
                + KEY_TITLE + ", tb. "
                + KEY_DESC + ",tb. " + KEY_ISBN + ", tb. " + KEY_NUMBER_OF_PAGES + ",tb. "
                + KEY_RELEASE_YEAR + ", tb. " + KEY_PRICE
                + ", tc. "
                + KEY_LANGUAGE_NAME
                //+ ", ta. "
                //+ KEY_AUTHOR_fNAME
                //+ ", ta. "
                //+ KEY_AUTHOR_lNAME
                + ", tl. "
                + KEY_CATEGORY_NAME
                + " FROM " + TABLE_BOOK + " tb "
                + " INNER JOIN " + TABLE_LANGUAGE + " tc " + " ON tc. "
                + KEY_LANGUAGE_ID + " = tb." + LANGUAGE_ID
                //+ " INNER JOIN "
                //+ TABLE_AUTHOR + "ta " + " ON tb. " + AUTHOR_ID + " = ta. " + KEY_AUTHOR_ID
                + " INNER JOIN " + TABLE_CATEGORY + " tl " + " ON tb. " + KEY_CAT_ID
                + " = tl. " + KEY_CATEGORY_ID ;
               //+ " WHERE tb. " + KEY_BOOK_ID + " =?" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                Book b = new Book();

                byte[] imageBytes = c.getBlob(c.getColumnIndex(KEY_IMAGE));
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                b.setImage(objectBitmap);
                b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setNumber_of_pages(c.getInt(c.getColumnIndex(KEY_NUMBER_OF_PAGES)));
                b.setRelease_year(c.getString(c.getColumnIndex(KEY_RELEASE_YEAR)));
                b.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));
                b.setISBN(c.getString(c.getColumnIndex(KEY_ISBN)));
                b.setCategory_name(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));
                //b.setAuthorFName(c.getString(c.getColumnIndex(KEY_AUTHOR_fNAME)));
                //b.setAuthorLName(c.getString(c.getColumnIndex(KEY_AUTHOR_lNAME)));
                b.setLanguage(c.getString(c.getColumnIndex(KEY_LANGUAGE_NAME)));




                //b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));



                books.add(b);

            }while (c.moveToNext());
        }
        c.close();
        return books;
    }
    public List<Book> getAllBooksByCategory(String category_name){
        List<Book> books = new ArrayList<Book>();
        String selectQuery = "SELECT tb. " + KEY_IMAGE + ", tb. "
                + KEY_TITLE + ", tb. "
                + KEY_DESC  + ", tb. " + KEY_BOOK_ID + ", tb. " + KEY_ISBN + ", tb." + KEY_BOOK_CONTENT
                + ",tb. " + KEY_NUMBER_OF_PAGES + ",tb. "
                + KEY_RELEASE_YEAR + ", tb. " + KEY_PRICE
                + ", ta. " + KEY_AUTHOR_fNAME + ", ta. " + KEY_AUTHOR_lNAME
                + ", tl. " + KEY_LANGUAGE_NAME
                + ", tc. "
                + KEY_CATEGORY_NAME + " FROM " + TABLE_BOOK + " tb "
                + " JOIN " + TABLE_CATEGORY + " tc " + " ON tb. "
                + KEY_CAT_ID + " = tc." + KEY_CATEGORY_ID
                + " JOIN " + TABLE_LANGUAGE + " tl " + " ON tb. " + LANGUAGE_ID + " = tl." + KEY_LANGUAGE_ID
                + " JOIN " + TABLE_AUTHOR + " ta " + " ON tb. " + AUTHOR_ID + " = ta." + KEY_AUTHOR_ID
                + " WHERE tc." + KEY_CATEGORY_NAME + " = '" + category_name + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                Book b = new Book();
                byte[] imageBytes = c.getBlob(c.getColumnIndex(KEY_IMAGE));
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                b.setImage(objectBitmap);
                b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setNumber_of_pages(c.getInt(c.getColumnIndex(KEY_NUMBER_OF_PAGES)));
                b.setRelease_year(c.getString(c.getColumnIndex(KEY_RELEASE_YEAR)));
                b.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));
                b.setLanguage(c.getString(c.getColumnIndex(KEY_LANGUAGE_NAME)));
                b.setCategory_name(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));

                b.setAuthorFName(c.getString(c.getColumnIndex(KEY_AUTHOR_fNAME)));
                b.setAuthorLName(c.getString(c.getColumnIndex(KEY_AUTHOR_lNAME)));

                //b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setBook_id(c.getInt(c.getColumnIndex(KEY_BOOK_ID)));
                b.setISBN(c.getString(c.getColumnIndex(KEY_ISBN)));
                b.setBook_url(c.getString(c.getColumnIndex(KEY_BOOK_CONTENT)));


                books.add(b);
            }while (c.moveToNext());
        }
        c.close();
        return books;

    }
    public List<Book> getAllBooksListForUsers(){
        List<Book> books = new ArrayList<Book>();
        String selectQuery = "SELECT tb. " + KEY_IMAGE + ", tb. "
                + KEY_TITLE + ", tb. "
                + KEY_DESC
                + ", tb. " + KEY_BOOK_ID
                + ", tb. " + KEY_ISBN
                + ",tb. " + KEY_BOOK_CONTENT
                + ",tb. " + KEY_NUMBER_OF_PAGES + ",tb. "
                + KEY_RELEASE_YEAR + ", tb. " + KEY_PRICE
                + ", ta. " + KEY_AUTHOR_fNAME + ", ta. " + KEY_AUTHOR_lNAME
                + ", tl. " + KEY_LANGUAGE_NAME
                + ", tc. "
                + KEY_CATEGORY_NAME + " FROM " + TABLE_BOOK + " tb "
                + " JOIN " + TABLE_CATEGORY + " tc " + " ON tb. "
                + KEY_CAT_ID + " = tc." + KEY_CATEGORY_ID
                + " JOIN " + TABLE_LANGUAGE + " tl " + " ON tb. " + LANGUAGE_ID + " = tl." + KEY_LANGUAGE_ID
                + " JOIN " + TABLE_AUTHOR + " ta " + " ON tb. " + AUTHOR_ID + " = ta." + KEY_AUTHOR_ID;
                //+ " WHERE tc." + KEY_CATEGORY_NAME + " = '" + category_name + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                Book b = new Book();
                byte[] imageBytes = c.getBlob(c.getColumnIndex(KEY_IMAGE));
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                b.setImage(objectBitmap);
                b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                b.setBook_id(c.getInt(c.getColumnIndex(KEY_BOOK_ID)));
                b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setNumber_of_pages(c.getInt(c.getColumnIndex(KEY_NUMBER_OF_PAGES)));

                b.setISBN(c.getString(c.getColumnIndex(KEY_ISBN)));

                b.setRelease_year(c.getString(c.getColumnIndex(KEY_RELEASE_YEAR)));
                b.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));
                b.setLanguage(c.getString(c.getColumnIndex(KEY_LANGUAGE_NAME)));
                b.setCategory_name(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));

                b.setAuthorFName(c.getString(c.getColumnIndex(KEY_AUTHOR_fNAME)));
                b.setAuthorLName(c.getString(c.getColumnIndex(KEY_AUTHOR_lNAME)));

                b.setBook_url(c.getString(c.getColumnIndex(KEY_BOOK_CONTENT)));

                //b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));


                books.add(b);
            }while (c.moveToNext());
        }
        c.close();
        return books;

    }
    public List<Book> getCartItemsForUsers(String user_email){
        List<Book> books = new ArrayList<Book>();
        String selectQuery = "SELECT tb. " + KEY_IMAGE + ", tb. "
                + KEY_TITLE + ", tb. "
                + KEY_DESC
                + ", tb. " + KEY_BOOK_ID
                + ", tb. " + KEY_ISBN
                + ",tb. " + KEY_BOOK_CONTENT
                + ",tb. " + KEY_NUMBER_OF_PAGES + ",tb. "
                + KEY_RELEASE_YEAR + ", tb. " + KEY_PRICE
                + ", ti. " + KEY_EMAIL
                + ", ta. " + KEY_AUTHOR_fNAME + ", ta. " + KEY_AUTHOR_lNAME
                + ", tl. " + KEY_LANGUAGE_NAME
                + ", tc. "
                + KEY_CATEGORY_NAME + " FROM " + TABLE_CART_ITEMS + " ti "
                + " JOIN " + TABLE_BOOK + " tb " + " ON ti. " + KEY_ITEM_ID + " = tb." + KEY_BOOK_ID
                + " JOIN " + TABLE_CATEGORY + " tc " + " ON tb. "
                + KEY_CAT_ID + " = tc." + KEY_CATEGORY_ID
                + " JOIN " + TABLE_LANGUAGE + " tl " + " ON tb. " + LANGUAGE_ID + " = tl." + KEY_LANGUAGE_ID
                + " JOIN " + TABLE_AUTHOR + " ta " + " ON tb. " + AUTHOR_ID + " = ta." + KEY_AUTHOR_ID
                + " WHERE ti. " + KEY_EMAIL + " = '" + user_email + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                Book b = new Book();
                byte[] imageBytes = c.getBlob(c.getColumnIndex(KEY_IMAGE));
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                b.setImage(objectBitmap);
                b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                b.setBook_id(c.getInt(c.getColumnIndex(KEY_BOOK_ID)));
                b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                b.setNumber_of_pages(c.getInt(c.getColumnIndex(KEY_NUMBER_OF_PAGES)));

                b.setISBN(c.getString(c.getColumnIndex(KEY_ISBN)));

                b.setRelease_year(c.getString(c.getColumnIndex(KEY_RELEASE_YEAR)));
                b.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));
                b.setLanguage(c.getString(c.getColumnIndex(KEY_LANGUAGE_NAME)));
                b.setCategory_name(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));

                b.setAuthorFName(c.getString(c.getColumnIndex(KEY_AUTHOR_fNAME)));
                b.setAuthorLName(c.getString(c.getColumnIndex(KEY_AUTHOR_lNAME)));

                b.setBook_url(c.getString(c.getColumnIndex(KEY_BOOK_CONTENT)));

                //b.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));


                books.add(b);
            }while (c.moveToNext());
        }
        c.close();
        return books;

    }

    /*public void insertBookContent(BookContent bookContent){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_ID, bookContent.getIds());
            values.put(KEY_CHAPTER, bookContent.getChapters());
            values.put(KEY_CHAPTER_TITLE, bookContent.getTitles());
            values.put(KEY_DETAILS, bookContent.getDetails());
            values.put(BOOK_ID, bookContent.getBook_id());

            long bookContentId = db.insert(DBHelper.TABLE_BOOK_CONTENT, null, values);
            if(bookContentId !=1){
                Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
                db.close();
            }else {
                Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

            }

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<BookContent> getBookContent(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BookContent> bookContentArrayList = new ArrayList<>();
        String selectQuery = "SELECT " + KEY_ID + ", " + KEY_CHAPTER + ", " + KEY_CHAPTER_TITLE + ", " + KEY_DETAILS + " FROM " + TABLE_BOOK_CONTENT;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c !=null){
            while (c.moveToNext()){
                BookContent count = new BookContent(c.getInt(0), c.getString(2), c.getString(3), c.getString(4));
                bookContentArrayList.add(count);
            }
            c.close();
            db.close();
        }
        return bookContentArrayList;
    }*/
    public void copyDatabaseFromAssets() throws IOException{
        InputStream myInput = mCtx.getAssets().open(DBNAME);
        String outFileName = getDatabasePath();
        File f = new File(mCtx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists()){
            f.mkdir();
        }
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);

        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    private static String getDatabasePath() {
        return mCtx.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DBNAME;
    }
    public SQLiteDatabase openDataBase () throws SQLException{
        File dbFile = mCtx.getDatabasePath(DBNAME);
        if (!dbFile.exists()){
            try {
                copyDatabaseFromAssets();
                Toast.makeText(mCtx, "copying success from assets folder", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                throw  new RuntimeException("Error creating source db", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    //drop database
    public void dropDb(SQLiteDatabase myBB){
        //Drop Table
        myBB.execSQL(DROP_TABLE_CATEGORY);
        //Create Table Again
        myBB.execSQL(CREATE_TABLE_CATEGORY);
    }

}
