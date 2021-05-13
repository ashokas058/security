package com.example.ashok_ray.security.Module_LEARN;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashok_ray.security.R;

import java.util.ArrayList;

/**
 * Created by Ashok on 5/7/2019.
 */

public class learn_adapter extends RecyclerView.Adapter<learn_adapter.learn_hl> {
ArrayList<learn_model> lrn_model_array;
Context context;

    public learn_adapter(ArrayList<learn_model> lrn_model_array, Context context) {
        this.lrn_model_array = lrn_model_array;
        this.context = context;
    }

    @Override
    public learn_hl onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.learn_fine_recycler_layout,parent,false);
        return new learn_hl(view);
    }

    @Override
    public void onBindViewHolder(learn_hl holder, int position) {
holder.t1.setText(lrn_model_array.get(position).getTopic());
        holder.t1.setText(lrn_model_array.get(position).getTopic());
        holder.t2.setText(lrn_model_array.get(position).getTopic());
        holder.head.setText(lrn_model_array.get(position).getHead());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static  class learn_hl extends RecyclerView.ViewHolder implements View.OnClickListener{
ImageView imageView;
TextView t1,t2,head;

        public learn_hl(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.book_cover_pic_id);
            t1=itemView.findViewById(R.id.topic);
            t2=itemView.findViewById(R.id.fulltext_id);
            head=itemView.findViewById(R.id.head_id);

        }

        @Override
        public void onClick(View view) {
          int pos =getAdapterPosition();
          String topic,fulltop,url ;
        }
    }
}
