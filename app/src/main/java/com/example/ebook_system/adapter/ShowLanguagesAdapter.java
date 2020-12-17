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

import com.example.ebook_system.EditLanguageActivity;
import com.example.ebook_system.R;
import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.helper.Language;

import java.util.List;

public class ShowLanguagesAdapter extends RecyclerView.Adapter<ShowLanguagesAdapter.AllLanguageViewHolder>{

    Context context;
    List<Language> languageList;
    DBHelper DB;

    public ShowLanguagesAdapter(Context context, List<Language> languageList) {
        this.context = context;
        this.languageList = languageList;
        DB = new DBHelper(context);
    }


    @NonNull
    @Override
    public AllLanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show_languages_list, parent,false);
        AllLanguageViewHolder viewHolder = new ShowLanguagesAdapter.AllLanguageViewHolder(view);
        return viewHolder ;

    }

    @Override
    public void onBindViewHolder(@NonNull AllLanguageViewHolder holder, int position) {

        final Language language = languageList.get(position);
        holder.id.setText(Integer.toString(language.getLanguage_id()));
        holder.name.setText(language.getName());
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditLanguageActivity.class);
                i.putExtra("name", languageList.get(position).getName());
                i.putExtra("id", languageList.get(position).getLanguage_id());
                context.startActivity(i);

                /*String strName = holder.name.getText().toString();
                DB.updateLanguage(new Language(language.getLanguage_id(), strName));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());*/

            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deleteLanguage(language.getLanguage_id());
                languageList.remove(position);
                notifyDataSetChanged();


            }
        });



    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public static class AllLanguageViewHolder extends RecyclerView.ViewHolder{
        //EditText  name;
        TextView id, name;
        Button editBtn, deleteBtn;

        public AllLanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            editBtn = itemView.findViewById(R.id.edit);
            deleteBtn = itemView.findViewById(R.id.delete);

        }
    }
}
