package com.example.ashok_ray.security.Module_PROFILE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ashok_ray.security.DATA_MODEL.MDL_Learn;
import com.example.ashok_ray.security.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ashok_Ray on 25-12-2018.
 */

public class ADPTR_Learn extends RecyclerView.Adapter<ADPTR_Learn.cardviewholder>{
    Context context;
    ArrayList<MDL_Learn>model;
    ADPTR_Learn(){}
    public ADPTR_Learn(Context context, ArrayList<MDL_Learn>model) {
        this.context=context;
        this.model=model;
    }

    @Override
    public cardviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view;
        LayoutInflater inflater=LayoutInflater.from(parent.getContext()) ;
        view=inflater.inflate(R.layout.recycler_layout_design,parent,false);

        return new cardviewholder(view,parent.getContext(),this.model);
    }

    @Override
    public void onBindViewHolder(final cardviewholder holder, int position) {
        holder.top_title.setText(model.get(position).getTitle_top());
        String imageurl=model.get(position).getImage_url();
        Picasso.get().load(imageurl).into(holder.load_img);
        holder.fulltext.setText(model.get(position).getText_data());
        View view=holder.itemView;
        final Context context=holder.itemView.getContext();
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class cardviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView top_title;
    ImageView load_img;
    TextView fulltext;
    ArrayList<MDL_Learn> mode;
    Context context;

        public cardviewholder(View itemView, Context context, ArrayList<MDL_Learn> model) {
            super(itemView);
            itemView.setOnClickListener(this);
            top_title=itemView.findViewById(R.id.cat_top_id);
             load_img=itemView.findViewById(R.id.temp_image_id);
             fulltext=itemView.findViewById(R.id.fulltext_id);
             this.context=context;
            mode=model;

        }

        @Override
        public void onClick(View view) {
            int post=getAdapterPosition();
            MDL_Learn m=mode.get(post);
            Bundle bundle=new Bundle();
            bundle.putString("top_title",m.getTitle_top());
            bundle.putString("full_text",m.getText_data());
            bundle.putString("image_url",m.getImage_url());
Intent intent=new Intent(context, ACT_LearnForm.class);
intent.putExtras(bundle);
context.startActivity(intent);
            //Toast.makeText(context, m.getText_data(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public int getItemViewType(int position) {
        return position;

    }

}
