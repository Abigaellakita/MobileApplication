package com.example.ebook_system.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ebook_system.R;
import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.model.BookContent;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    DBHelper DB;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_read_book, container, false);
        //final TextView textView = v.findViewById(R.id.section_label);
       /* pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        TextView mPageTv, mChapterTv, mTitleTv, mDetailTv, mBook;
        mPageTv = v.findViewById(R.id.pageTv);
        mChapterTv = v.findViewById(R.id.chapterTv);
        mTitleTv = v.findViewById(R.id.titleTv);
        mDetailTv = v.findViewById(R.id.detailTv);

        ArrayList<BookContent> bookContentArrayList;
        int page = 0;
        String chap ="", title="", detail="";
        bookContentArrayList = new ArrayList<>();
        bookContentArrayList.clear();
        DB = new DBHelper(getContext());
        DB.openDataBase();
        bookContentArrayList = DB.getBookContent();



        if(getArguments().getInt(ARG_SECTION_NUMBER) ==1){
            BookContent count = bookContentArrayList.get(0);
            page = page + count.getIds();
            chap = chap + count.getChapters();
            title = title + count.getTitles();
            detail = detail + count.getDetails().replace(",,,,", "\n");

            mPageTv.setText(page+"");
            mChapterTv.setText(chap);
            mTitleTv.setText(title);
            mDetailTv.setText(detail);

            return v;
        }
        if(getArguments().getInt(ARG_SECTION_NUMBER) ==2){
            BookContent count = bookContentArrayList.get(1);
            page = page + count.getIds();
            chap = chap + count.getChapters();
            title = title + count.getTitles();
            detail = detail + count.getDetails().replace(",,,,", "\n");

            mPageTv.setText(page+"");
            mChapterTv.setText(chap);
            mTitleTv.setText(title);
            mDetailTv.setText(detail);

            return v;
        }
        if(getArguments().getInt(ARG_SECTION_NUMBER) ==3){
            BookContent count = bookContentArrayList.get(2);
            page = page + count.getIds();
            chap = chap + count.getChapters();
            title = title + count.getTitles();
            detail = detail + count.getDetails().replace(",,,,", "\n");

            mPageTv.setText(page+"");
            mChapterTv.setText(chap);
            mTitleTv.setText(title);
            mDetailTv.setText(detail);

            return v;
        }
        if(getArguments().getInt(ARG_SECTION_NUMBER) ==4){
            BookContent count = bookContentArrayList.get(3);
            page = page + count.getIds();
            chap = chap + count.getChapters();
            title = title + count.getTitles();
            detail = detail + count.getDetails().replace(",,,,", "\n");

            mPageTv.setText(page+"");
            mChapterTv.setText(chap);
            mTitleTv.setText(title);
            mDetailTv.setText(detail);

            return v;
        }
        if(getArguments().getInt(ARG_SECTION_NUMBER) ==5){
            BookContent count = bookContentArrayList.get(4);
            page = page + count.getIds();
            chap = chap + count.getChapters();
            title = title + count.getTitles();
            detail = detail + count.getDetails().replace(",,,,", "\n");

            mPageTv.setText(page+"");
            mChapterTv.setText(chap);
            mTitleTv.setText(title);
            mDetailTv.setText(detail);

            return v;
        }
        return v;
    }
}