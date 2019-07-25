package com.example.app2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.app2.R;
import com.example.app2.beans.Fuli;
import com.example.app2.beans.ResultsBean;

import java.util.ArrayList;

public class RecycViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean read;
    ArrayList<ResultsBean> resultsBeans = new ArrayList<>();
    private Context context;

    public RecycViewAdapter(ArrayList<ResultsBean> resultsBeans, Context context) {
        this.resultsBeans = resultsBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycviewadapter_item1, parent, false);
        ViewHolder1 holder1 = new ViewHolder1(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder1 holder1 = (ViewHolder1) holder;
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(context).load(resultsBeans.get(position).getUrl()).apply(requestOptions).into(holder1.iv);
        holder1.tv1.setText(resultsBeans.get(position).getDesc());
        holder1.tv2.setText(resultsBeans.get(position).getSource());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                a.Onclicked(view,position);
                return false;
            }
        });
//        holder1.rb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                a.Onclicked(view,position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder{
         private ImageView iv;
         private TextView tv1;
         private TextView tv2;
         private RadioGroup rg;
         private RadioButton rb;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
             iv = itemView.findViewById(R.id.iv);
             tv1 = itemView.findViewById(R.id.tv1);
             tv2 = itemView.findViewById(R.id.tv2);
             rg = itemView.findViewById(R.id.rg);
             rb = itemView.findViewById(R.id.rb);
        }
    }
    private A a;

    public void setA(A a) {
        this.a = a;
    }
    public interface  A{
        void Onclicked(View view,int position);
    }
}
