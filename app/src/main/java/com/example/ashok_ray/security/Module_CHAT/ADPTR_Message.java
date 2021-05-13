package com.example.ashok_ray.security.Module_CHAT;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ashok_ray.security.DATA_MODEL.MDL_Message;
import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.osafirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ashok_Ray on 28-01-2019.
 */

public class ADPTR_Message extends RecyclerView.Adapter<ADPTR_Message.messageviewholder> {
    ArrayList<MDL_Message> modelMessage =new ArrayList<>();
    DatabaseReference databaseReference;
    Context context;

    public ADPTR_Message(ArrayList<MDL_Message> modelMessage, Context context) {
        this.modelMessage = modelMessage;
        this.context=context;
    }

    @Override
    public messageviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.chat_layout,parent,false);
        return new messageviewholder(view);
    }

    @Override
    public void onBindViewHolder(final messageviewholder holder, int position) {
        MDL_Message model=modelMessage.get(position);
        String currentUser_uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        String messageFrom=model.getFrom();
        databaseReference= osafirebase.databaseReferenceOSA.child("user").child(messageFrom);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("image_url")){
                    String url=dataSnapshot.child("image_url").getValue().toString();
                    Picasso.get().load(url).into(holder.reciver_pic);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(currentUser_uid.equals(messageFrom)){
           holder.reciver_layout.setVisibility(View.INVISIBLE);
            holder.sender_massage.setText(model.getMessagetext());
            holder.sender_username.setText("from "+model.getUsername());
            holder.sender_time.setText(model.getTime());

        }
        else{
           holder.sender_layout.setVisibility(View.INVISIBLE);
            holder.reciver_massage.setText(model.getMessagetext());
            holder.reciver_username.setText("from "+model.getUsername());
            holder.reciver_time.setText(model.getTime());




        }


    }

    @Override
    public int getItemCount() {
        return modelMessage.size();
    }

    public static class messageviewholder extends RecyclerView.ViewHolder {
        TextView reciver_massage,reciver_time,reciver_username,sender_massage,sender_time,sender_username;
        RelativeLayout reciver_layout,sender_layout;

        CircleImageView reciver_pic;
        public messageviewholder(View itemView) {
            super(itemView);
            reciver_layout=itemView.findViewById(R.id.reciver_relative_id);
            sender_layout=itemView.findViewById(R.id.sender_realtive_id);
            reciver_massage=itemView.findViewById(R.id.reciver_message_id);
            reciver_username=itemView.findViewById(R.id.reciver_username_id);
            reciver_time=itemView.findViewById(R.id.reciver_message_time_id);
            reciver_pic=itemView.findViewById(R.id.reciver_pic_id);
            sender_massage=itemView.findViewById(R.id.sender_message_id);
            sender_time=itemView.findViewById(R.id.sender_time_id);
            sender_username=itemView.findViewById(R.id.sender_user_id);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
