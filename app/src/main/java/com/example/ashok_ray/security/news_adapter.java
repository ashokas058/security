package com.example.ashok_ray.security;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Ashok_Ray on 12-01-2019.
 */

public class news_adapter extends RecyclerView.Adapter<news_adapter.newsviewholder> {
    ArrayList<news_model_class> news=new ArrayList<news_model_class>();
    Context context;

    public news_adapter(ArrayList<news_model_class> news, Context context) {
        this.news = news;
        this.context = context;
    }

    @Override
    public newsviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        view=inflater.inflate(R.layout.news_recycler_model_xml,parent,false);

        return new newsviewholder(view);
    }

    @Override
    public void onBindViewHolder(newsviewholder holder, int position) {
holder.tit.setText(news.get(position).getTitle());
holder.fullnews.setText(news.get(position).getFullnews());
String url=news.get(position).getUrl();
        Picasso.get().load(url).into(holder.pro);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class newsviewholder extends RecyclerView.ViewHolder{
TextView tit;
TextView fullnews;
ImageView pro;
        public newsviewholder(View itemView) {
            super(itemView);
            pro=itemView.findViewById(R.id.temp_image_news_id);
            tit=itemView.findViewById(R.id.news_tit_id);
             fullnews=itemView.findViewById(R.id.full_news_id);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
