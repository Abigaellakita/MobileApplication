package com.example.ebook_system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.adapter.ShowBooksForUsersAdapter;
import com.example.ebook_system.adapter.ShowCartItemsForUsersAdapter;
import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class AddToCartActivity extends AppCompatActivity {
    RecyclerView cart_items_recycler;
    DBHelper DB;
    ShowBooksForUsersAdapter showBooksForUsersAdapter;
    ShowCartItemsForUsersAdapter showCartItemsForUsersAdapter;
    List<Book> bookList1;
    TextView totalPrice;
    Button Checkout;

    private float OverTotalPrice = 0;
    //public static String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        cart_items_recycler = findViewById(R.id.cart_items_recycler);
        totalPrice = findViewById(R.id.totalPrice);
        Checkout=findViewById(R.id.Checkout);
        DB = new DBHelper(this);


        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("UserEmail");
        //textView15.setText("All " + title + " Books");
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("ItemPrice"));

        bookList1 = DB.getCartItemsForUsers(userEmail);
        if (bookList1.size() > 0) {
            setShowBooksByCategoryForUserRecycler(bookList1);
        } else {
            Toast.makeText(this, "There are No Items to Cart", Toast.LENGTH_SHORT).show();
        }
        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*float price = intent.getFloatExtra("price", 0);
                OverTotalPrice = OverTotalPrice + price;
                String s = String.format("%.2f", OverTotalPrice);
                totalPrice.setText(String.valueOf(s));

                Intent intent1 = new Intent(AddToCartActivity.this, DashboardActivity.class);
                intent1.putExtra("TotPrice", OverTotalPrice);
                startActivity(intent1);*/

            }
        });

    }

    private void setShowBooksByCategoryForUserRecycler(List<Book> bookList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        cart_items_recycler.setLayoutManager(layoutManager);
        showCartItemsForUsersAdapter = new ShowCartItemsForUsersAdapter(this, bookList);
        cart_items_recycler.setAdapter(showCartItemsForUsersAdapter);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            float price = intent.getFloatExtra("price", 0);
            OverTotalPrice = OverTotalPrice + price;
            String s = String.format("%.2f", OverTotalPrice);
            totalPrice.setText(String.valueOf(s));

        }
    };

}