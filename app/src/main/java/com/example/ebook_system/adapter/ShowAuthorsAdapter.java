package com.example.ebook_system.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook_system.EditAuthorActivity;
import com.example.ebook_system.R;
import com.example.ebook_system.helper.Author;
import com.example.ebook_system.helper.DBHelper;

import java.util.List;

public class ShowAuthorsAdapter extends RecyclerView.Adapter<ShowAuthorsAdapter.ShowAuthorsViewHolder> {
    List<Author> authorList;
    DBHelper DB;
    Context context;

    public ShowAuthorsAdapter(Context context, List<Author> authorList) {
        this.authorList = authorList;
        this.context = context;
        DB = new DBHelper(context);
    }

    @NonNull
    @Override
    public ShowAuthorsAdapter.ShowAuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show_authors_list, parent,false);
        ShowAuthorsViewHolder viewHolder = new ShowAuthorsAdapter.ShowAuthorsViewHolder(view);
        return viewHolder ;

    }

    @Override
    public void onBindViewHolder(@NonNull ShowAuthorsAdapter.ShowAuthorsViewHolder holder, int position) {
        final Author author = authorList.get(position);
        holder.id.setText(Integer.toString(author.getAuthor_id()));
        holder.fName.setText(author.getFirst_name());
        holder.lName.setText(author.getLast_name());
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditAuthorActivity.class);
                i.putExtra("f_name", authorList.get(position).getFirst_name());
                i.putExtra("l_name", authorList.get(position).getLast_name());
                i.putExtra("id", authorList.get(position).getAuthor_id());
                context.startActivity(i);
                /*String FName = holder.fName.getText().toString();
                String LName = holder.lName.getText().toString();
                DB.updateAuthor(new Author(author.getAuthor_id(), FName, LName));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());*/

            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deleteAuthor(author.getAuthor_id());
                authorList.remove(position);
                notifyDataSetChanged();


            }
        });


    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    public class ShowAuthorsViewHolder extends RecyclerView.ViewHolder {
        TextView id, fName, lName;
        //EditText fName, lName;
        Button editBtn, deleteBtn;

        public ShowAuthorsViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            fName=itemView.findViewById(R.id.f_name);
            lName=itemView.findViewById(R.id.l_name);
            editBtn = itemView.findViewById(R.id.edit);
            deleteBtn = itemView.findViewById(R.id.delete);
        }
    }
}
