package com.example.wanandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Datas> mList;

    public MyRecyclerViewAdapter(ArrayList<Datas> list) {
        mList = list;
    }


    @Override
    public int getItemCount() {
        return mList.size() ;
    }


    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Datas data = mList.get(position);
        holder.textView1.setText(data.getTitle());
        holder.textView2.setText("分类:"+data.getSuperChapterName()+"   时间:"+data.getNiceDate());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_title);
            textView2 = itemView.findViewById(R.id.text_info);

        }
    }
}
