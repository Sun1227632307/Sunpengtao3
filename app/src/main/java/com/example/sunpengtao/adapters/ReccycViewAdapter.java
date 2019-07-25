package com.example.sunpengtao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunpengtao.R;
import com.example.sunpengtao.beans.Shuju;

import java.util.ArrayList;

public class ReccycViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Shuju> shujus;
    private Context context;

    public ReccycViewAdapter(ArrayList<Shuju> shujus, Context context) {
        this.shujus = shujus;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycviewdapter_item1, parent, false);
        ViewHolder1 holder1 = new ViewHolder1(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        holder1.tv1.setText(shujus.get(position).getPass());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                a.OMclicked(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return shujus.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tv1;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
             iv = itemView.findViewById(R.id.iv);
             tv1 = itemView.findViewById(R.id.tv1);
        }
    }
    private A a;

    public void setA(A a) {
        this.a = a;
    }
    public interface A{
        void OMclicked(int position);

    }
}
