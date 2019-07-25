package com.example.app4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.app4.R;
import com.example.app4.beans.Shuju;

import java.util.ArrayList;

public class RecycViewAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Shuju.DataBean.DatasBean> datasBeans = new ArrayList<>();
    private Context context;

    public RecycViewAdatper(ArrayList<Shuju.DataBean.DatasBean> datasBeans, Context context) {
        this.datasBeans = datasBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_onefragmentadapter_item1, parent,false);
        ViewHolder1 holder1 = new ViewHolder1(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(context).load(datasBeans.get(position).getEnvelopePic()).apply(requestOptions).into(holder1.iv);
        holder1.tv1.setText(datasBeans.get(position).getAuthor());
        holder1.tv2.setText(datasBeans.get(position).getChapterName());
    }

    @Override
    public int getItemCount() {
        return datasBeans.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder{
      private ImageView iv;
      private TextView tv1;
      private TextView tv2;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
             iv = itemView.findViewById(R.id.iv);
             tv1 = itemView.findViewById(R.id.tv1);
             tv2 = itemView.findViewById(R.id.tv2);
        }
    }
}
