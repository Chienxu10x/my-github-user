package com.pby.androidfirebasecomicreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pby.androidfirebasecomicreader.R;
import com.pby.androidfirebasecomicreader.ViewComicActivity;
import com.pby.androidfirebasecomicreader.common.Common;
import com.pby.androidfirebasecomicreader.interfaces.IRecyclerItemClickListener;
import com.pby.androidfirebasecomicreader.model.Chapter;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder> {

    private Context context;
    private List<Chapter> chapterList;
    private LayoutInflater inflater;

    public MyChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_chapter, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.chapterNumberText.setText(chapterList.get(i).Name);

        myViewHolder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.chapterSelected = chapterList.get(position);
                Common.chapterIndex = position;
                context.startActivity(new Intent(context, ViewComicActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView chapterNumberText;
        IRecyclerItemClickListener recyclerItemClickListener;

        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            chapterNumberText = itemView.findViewById(R.id.text_chapter_number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
