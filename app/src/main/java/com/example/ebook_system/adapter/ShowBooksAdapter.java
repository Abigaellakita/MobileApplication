package com.example.ebook_system.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.EditBookActivity;
import com.example.ebook_system.R;
import com.example.ebook_system.helper.Book;
import com.example.ebook_system.helper.DBHelper;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ShowBooksAdapter extends RecyclerView.Adapter<ShowBooksAdapter.ShowBooksViewHolder> {
    List<Book> bookList;
    Context context;
    DBHelper DB;


    public ShowBooksAdapter(Context context, List<Book> bookList) {
        this.bookList = bookList;
        this.context = context;
        DB = new DBHelper(context);
    }

    @NonNull
    @Override
    public ShowBooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.show_books_row_items, parent, false);
        //return new ShowBooksAdapter.ShowBooksViewHolder(view);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show_books_row_items, parent,false);
        ShowBooksViewHolder viewHolder = new ShowBooksViewHolder(view);
        return viewHolder ;
        //return new ShowBooksViewHolder(LayoutInflater.from(context).inflate(R.layout.show_books_row_items,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ShowBooksViewHolder holder, int position) {
        final Book book=bookList.get(position);
        holder.Id.setText(Integer.toString(book.getBook_id()));
        holder.title.setText(book.getTitle());
        holder.image.setImageBitmap(book.getImage());
        //holder.desc.setText(book.getDesc());
        /*holder.release_year.setText(book.getRelease_year());
        holder.no_of_pages.setText(Integer.toString(book.getNumber_of_pages()));
        holder.price.setText(Float.toString(book.getPrice()));
        holder.isbn.setText(book.getISBN());
        holder.rating.setText(Float.toString(book.getRating()));
        holder.status.setText(Integer.toString(book.getStatus()));*/
        //holder.image.setImageBitmap(book.getImage());
        //holder.last_updated.setText(book.getLast_updated());
        //holder.created_at.setText(book.getCreated_at());
        //holder.category.setText(Integer.toString(book.getCat_id()));
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditBookActivity.class);
                i.putExtra("title", bookList.get(position).getTitle());
                i.putExtra("desc", bookList.get(position).getDesc());
                i.putExtra("language_id", bookList.get(position).getLanguage_id());
                i.putExtra("author_id", bookList.get(position).getAuthor_id());
                i.putExtra("category_id", bookList.get(position).getCat_id());
                i.putExtra("price", bookList.get(position).getPrice());
                i.putExtra("pages", bookList.get(position).getNumber_of_pages());
                i.putExtra("year", bookList.get(position).getRelease_year());
                i.putExtra("isbn", bookList.get(position).getISBN());
                i.putExtra("status", bookList.get(position).getStatus());
                i.putExtra("rating", bookList.get(position).getRating());
                i.putExtra("id", bookList.get(position).getBook_id());

                Bitmap bitmap = bookList.get(position).getImage();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                i.putExtra("image", bs.toByteArray());

                context.startActivity(i);
                //String strName = holder.cat_name.getText().toString();
                //DB.updateCat(new Category(category.getCat_id(), strName));

                //notifyDataSetChanged();
                //((Activity) context).finish();
                //context.startActivity(((Activity) context).getIntent());

                //Intent intent = new Intent(context, EditCategoryActivity.class);
                //intent.putExtra("name", strName);
                //context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB.deleteBook(book.getBook_id());
                bookList.remove(position);
                notifyDataSetChanged();


            }
        });



    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
    public class ShowBooksViewHolder extends RecyclerView.ViewHolder{
        TextView Id, title;
        ImageView image;
        //TextView desc, language, author,category, price, no_of_pages, release_year, isbn, status, rating, created_at, last_updated;
        Button editBtn, deleteBtn;

        public ShowBooksViewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.books_id);
            title = itemView.findViewById(R.id.books_title);
            image = itemView.findViewById(R.id.image);
            /*desc = itemView.findViewById(R.id.books_desc);
            author = itemView.findViewById(R.id.books_author);
            language = itemView.findViewById(R.id.books_language);
            category = itemView.findViewById(R.id.books_category);
            price = itemView.findViewById(R.id.books_price);
            no_of_pages = itemView.findViewById(R.id.books_number_pages);
            release_year = itemView.findViewById(R.id.books_release_year);
            isbn = itemView.findViewById(R.id.books_isbn);
            status = itemView.findViewById(R.id.books_status);
            rating = itemView.findViewById(R.id.books_rating);
            created_at = itemView.findViewById(R.id.books_created_at);
            last_updated = itemView.findViewById(R.id.books_last_updated);*/
            //image = itemView.findViewById(R.id.image);
            editBtn = itemView.findViewById(R.id.edit);
            deleteBtn = itemView.findViewById(R.id.delete);
        }
    }
}
