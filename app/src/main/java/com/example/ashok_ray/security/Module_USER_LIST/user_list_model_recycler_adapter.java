package com.example.ashok_ray.security.Module_USER_LIST;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.Module_CHAT.ACT_ChatPrivate;
import com.example.ashok_ray.security.DATA_MODEL.MDL_User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ashok on 5/4/2019.
 */

public class user_list_model_recycler_adapter extends RecyclerView.Adapter<user_list_model_recycler_adapter.user_list_holder>{

ArrayList<MDL_User> m_profile;


    public user_list_model_recycler_adapter(ArrayList<MDL_User> m_profile) {
        this.m_profile = m_profile;
    }

    @Override
    public user_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.user_list_model_recyclerview_layout,parent,false);
        return new user_list_holder(view,this.m_profile);
    }

    @Override
    public void onBindViewHolder(user_list_holder holder, int position) {

holder.status.setText(m_profile.get(position).getStatus());
holder.name.setText(m_profile.get(position).getUser_name());
String url=m_profile.get(position).getImage_url();
        Picasso.get().load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return m_profile.size();
    }

    public  static  class user_list_holder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView imageView;
        TextView name,status;
        ArrayList<MDL_User> m_pro_array;

        public user_list_holder(View itemView,ArrayList<MDL_User> m_p) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.chat_profile_pic_list_id);
            this.m_pro_array=m_p;
            name=itemView.findViewById(R.id.user_name_list__id);
            status=itemView.findViewById(R.id.user_status_id);
        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            String username=m_pro_array.get(pos).getUser_name();
            String url=m_pro_array.get(pos).getImage_url();
            String key=m_pro_array.get(pos).getKey();
            Intent intent=new Intent(view.getContext(), ACT_ChatPrivate.class);
            intent.putExtra("username",username);
            intent.putExtra("url",url);
            intent.putExtra("key",key);

            Context context=view.getContext();
            context.startActivity(intent);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
