package com.example.ashok_ray.security.Module_PROFILE;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashok_ray.security.DATA_MODEL.MDL_News;
import com.example.ashok_ray.security.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ashok_Ray on 12-01-2019.
 */

public class ADPTR_News extends RecyclerView.Adapter<ADPTR_News.newsviewholder> {
    ArrayList<MDL_News> news=new ArrayList<MDL_News>();
    Context context;

    public ADPTR_News(ArrayList<MDL_News> news, Context context) {
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
