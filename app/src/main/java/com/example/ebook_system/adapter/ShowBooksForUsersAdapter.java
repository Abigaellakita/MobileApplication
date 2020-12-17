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
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.MoreDetailsAboutTheBookForUsersActivity;
import com.example.ebook_system.R;
import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ShowBooksForUsersAdapter extends RecyclerView.Adapter<ShowBooksForUsersAdapter.ShowBooksForUsersViewHolder> implements Filterable {
    Context context;
    List<Book> bookList;
    DBHelper DB;
    List<Book> bookListFull;

    public ShowBooksForUsersAdapter(Context context, List<Book> bookList){
        this.context = context;
        this.bookList = bookList;
        DB = new DBHelper(context);
        bookListFull = new ArrayList<>(bookList);
    }

    /*public ShowBooksForUsersAdapter(Context context, List<Book> bookList, List<Category> categoryList) {
        this.context = context;
        this.bookList = bookList;
        this.categoryList = categoryList;
    }*/

    @NonNull
    @Override
    public ShowBooksForUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_books_for_users_row_items, parent, false);
        return new ShowBooksForUsersViewHolder(view);


        /*LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show_books_row_items, parent,false);
        ShowBooksViewHolder viewHolder = new ShowBooksViewHolder(view);
        return viewHolder ;*/
    }

    @Override
    public void onBindViewHolder(@NonNull ShowBooksForUsersViewHolder holder, int position) {
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

        holder.addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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


    public static class ShowBooksForUsersViewHolder extends RecyclerView.ViewHolder{
        TextView title, price, no_of_pages, release_year, language, category, authorFName, authorLName;
        //TextView title,language, author,category, price, no_of_pages, release_year;
        Button moreDetailsBtn, addCartBtn;
        ImageView imageBook;

        public ShowBooksForUsersViewHolder(@NonNull View itemView) {
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
            moreDetailsBtn = itemView.findViewById(R.id.moreDetailsBtn);
            addCartBtn= itemView.findViewById(R.id.AddCartBtn);
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
