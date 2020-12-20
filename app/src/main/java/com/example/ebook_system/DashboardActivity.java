package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.adapter.RecentlyViewedAdapter;
import com.example.ebook_system.adapter.ShowBooksForUsersAdapter;
import com.example.ebook_system.adapter.ShowCategoriesForUsersAdapter;
import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.Category;
import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    DBHelper DB;
    RecyclerView showBooksForUsersRecyclerView, categoryRecyclerView, recentlyViewedRecyclerView;
    ShowBooksForUsersAdapter showBooksForUsersAdapter;

    List<Book> bookList1;
    ImageView view_cart, user_logout;
    private float totAmt;
    public static String user_email;

    ShowCategoriesForUsersAdapter showCategoryForUsersAdapter;
    List<Category> categoryList;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    ImageView allCategory;
    TextView userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        DB = new DBHelper(this);

        showBooksForUsersRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        recentlyViewedRecyclerView = findViewById(R.id.recently_viewed_recycler);
        allCategory = findViewById(R.id.all_category);
        view_cart=findViewById(R.id.view_cart);
        userEmail=findViewById(R.id.User_Email);
        Intent intent1=getIntent();
        String user_Email = intent1.getStringExtra("email");
        userEmail.setText("Welcome " + user_Email);
        user_logout=findViewById(R.id.user_logout);
        user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(DashboardActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        user_email = intent.getStringExtra("email");
        //totAmt = getIntent().getFloatExtra("TotPrice", 0);
        //Toast.makeText(this, "Tot amt = $" + totAmt, Toast.LENGTH_SHORT).show();


        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(DashboardActivity.this, AddToCartActivity.class);
                intent1.putExtra("UserEmail", user_email);
                startActivity(intent1);
            }
        });


        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(DashboardActivity.this, AllCategoryActivity.class);
                //startActivity(i);
            }
        });
        //showBooksForUsersAdapter = new ShowBooksForUsersAdapter(this, user_email);

        //bookList1 = new ArrayList<>();
        //bookList1 = DB.getBooksListForUsers();

        //bookList1=DB.getBooksUsers();
        //bookList1=DB.getBooksForUsers();
        bookList1=DB.getAllBooksListForUsers();



        //adding data to model
        categoryList = DB.getAllCategoriesForUsers();


        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Just one day", "A book about love, heartbreak, travel, identity, and the “accidents” of fate, Just One Day shows us how sometimes in order to get found, you first have to get lost. . . and how often the people we are seeking are much closer than we know. The first in a sweepingly romantic duet of novels.", "English","$ 34", R.drawable.rom_1));

        setShowBooksForUserRecycler(bookList1);
        setShowCategoriesForUsersRecycler(categoryList);
        setRecentlyViewedRecycler(recentlyViewedList);
    }

    private void setShowBooksForUserRecycler(List<Book> bookList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false );
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        showBooksForUsersRecyclerView.setLayoutManager(layoutManager);
        showBooksForUsersAdapter = new ShowBooksForUsersAdapter(this, bookList);
        showBooksForUsersRecyclerView.setAdapter(showBooksForUsersAdapter);
    }
    private void setShowCategoriesForUsersRecycler(List<Category> categoryDataList ) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        categoryRecyclerView.setLayoutManager(layoutManager);
        showCategoryForUsersAdapter = new ShowCategoriesForUsersAdapter(this, categoryDataList);
        categoryRecyclerView.setAdapter(showCategoryForUsersAdapter);
    }
    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList ) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recentlyViewedRecyclerView.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this, recentlyViewedDataList);
        recentlyViewedRecyclerView.setAdapter(recentlyViewedAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                showBooksForUsersAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}