package com.example.ebook_system.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.AddToCartActivity;
import com.example.ebook_system.DashboardActivity;
import com.example.ebook_system.MoreDetailsAboutTheBookForUsersActivity;
import com.example.ebook_system.R;
import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.helper.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ShowCartItemsForUsersAdapter extends RecyclerView.Adapter<ShowCartItemsForUsersAdapter.ShowCartItemsForUsersViewHolder> implements Filterable {
    Context context;
    List<Book> bookList;
    DBHelper DB;
    List<Book> bookListFull;
    public String user_email;
    List<User> userList;
    DashboardActivity dashboardActivity;
    AddToCartActivity addToCartActivity;

    public ShowCartItemsForUsersAdapter(Context context, List<Book> bookList){
        this.context = context;
        this.bookList = bookList;
        DB = new DBHelper(context);
        bookListFull = new ArrayList<>(bookList);
        dashboardActivity = new DashboardActivity();
        addToCartActivity= new AddToCartActivity();

    }


    @NonNull
    @Override
    public ShowCartItemsForUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_cart_items_for_users, parent, false);
        return new ShowCartItemsForUsersViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ShowCartItemsForUsersViewHolder holder, int position) {
        final Book book=bookList.get(position);

        holder.imageBook.setImageBitmap(book.getImage());
        holder.title.setText(book.getTitle());
        holder.authorFName.setText(book.getAuthorFName());
        holder.authorLName.setText(book.getAuthorLName());
        holder.no_of_pages.setText(Integer.toString(book.getNumber_of_pages()));
        holder.release_year.setText(book.getRelease_year());
        holder.language.setText(book.getLanguage());
        holder.price.setText(Float.toString(book.getPrice()));
        holder.category.setText(book.getCategory_name());

        holder.RemoveItemFromCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = bookList.get(position).getBook_id();
                String user_email = dashboardActivity.user_email;

                DB.removeCartItem(id, user_email);
                //bookList.remove(position);
                //notifyDataSetChanged();
                bookList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();

            }
        });
        float Price = bookList.get(position).getPrice();
        float TotPrice = Float.valueOf(Price);
        Intent intent= new Intent("ItemPrice");
        intent.putExtra("price", TotPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        /*float oneItemPrice =((Float.valueOf(book.getPrice())));
        Intent intent= new Intent("ItemPrice");
        intent.putExtra("price", oneItemPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, MoreDetailsAboutTheBookForUsersActivity.class);
                i.putExtra("title", bookList.get(position).getTitle());
                i.putExtra("desc", bookList.get(position).getDesc());
                i.putExtra("isbn", bookList.get(position).getISBN());

                Bitmap bitmap = bookList.get(position).getImage();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                i.putExtra("image", bs.toByteArray());

                context.startActivity(i);


                //i.putExtra("image", bookList.get(position).getImage());

                //i.putExtra("language", bookList.get(position).getLanguage());
                //i.putExtra("language", bookList.get(position).getAuthor());
                //i.putExtra("language", bookList.get(position).getCategory());
                //i.putExtra("price", bookList.get(position).getPrice());

                //i.putExtra("release_year", bookList.get(position).getRelease_year());
                //i.putExtra("pages", bookList.get(position).getNumber_of_pages());


            }
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
        //return 5;
    }


    public static class ShowCartItemsForUsersViewHolder extends RecyclerView.ViewHolder{
        TextView title, price, no_of_pages, release_year, language, category, authorFName, authorLName;
        //TextView title,language, author,category, price, no_of_pages, release_year;
        Button RemoveItemFromCartBtn;
        ImageView imageBook;

        public ShowCartItemsForUsersViewHolder(@NonNull View itemView) {
            super(itemView);

            imageBook = itemView.findViewById(R.id.imageBook);
            title = itemView.findViewById(R.id.textViewTitle);
            authorFName = itemView.findViewById(R.id.textViewAuthorFName);
            authorLName = itemView.findViewById(R.id.textViewAuthorLName);
            language = itemView.findViewById(R.id.textViewLanguage);
            category = itemView.findViewById(R.id.textViewCategory);
            price = itemView.findViewById(R.id.textViewPrice);
            no_of_pages = itemView.findViewById(R.id.textViewPages);
            release_year = itemView.findViewById(R.id.textViewReleaseYear);
            RemoveItemFromCartBtn = itemView.findViewById(R.id.RemoveItemFromCartBtn);

        }
    }
    @Override
    public Filter getFilter(){
        return bookListFilter;

    }
    private Filter bookListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Book> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(bookListFull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Book item : bookListFull){
                    if(item.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bookList.clear();
            bookList.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };


}
