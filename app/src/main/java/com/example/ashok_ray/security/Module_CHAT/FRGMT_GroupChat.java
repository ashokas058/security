package com.example.ashok_ray.security.Module_CHAT;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ashok_ray.security.DATA_MODEL.MDL_Message;
import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.DATA_MODEL.MDL_User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by Ashok_Ray on 04-01-2019.
 */

public class FRGMT_GroupChat extends Fragment {
    String user_name;
    public void getusername(String user_name){
        this.user_name=user_name;
    }
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference,chatrootref;
    FirebaseDatabase firebaseDatabase;
    Button send;
    String currentuser_uid;
    String group_id;
    String chatuser,mydate,user_email,user_url;
    String message;
    EditText message_text;
    RecyclerView messageRecycler;
   ArrayList <MDL_Message> modelList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.chat_fragment,container,false);
        initViewComponents(view);
                return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerVw();
        initChatMessage();
        send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
            sendmessage(); }
}); }

    private void sendmessage() {
        if (!TextUtils.isEmpty(message_text.getText().toString())) {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            currentuser_uid = firebaseUser.getUid();
            databaseReference = firebaseDatabase.getReference("user").child(currentuser_uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    MDL_User mprofile = dataSnapshot.getValue(MDL_User.class);
                    String username = mprofile.getUser_name();
                    String email = mprofile.getUser_email();
                    String recv = message_text.getText().toString();
                    Date time = new Date();

                    long l_time = time.getTime();
                    String current = getTime(l_time);
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("chat").child("message").push();
                    String key = databaseReference.getKey();
                    chatuser = "message/" + key;
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    HashMap currenthash = new HashMap();
                    HashMap chatData = new HashMap();

                    chatData.put("from", uid);
                    chatData.put("username", username);
                    chatData.put("email", email);
                    //chatData.put("user_email",fetuserEmail(databaseReference));
                    chatData.put("time", current);
                    chatData.put("messagetext", recv);
                    currenthash.put(chatuser, chatData);
                    chatrootref = firebaseDatabase.getReference("chat");
                    chatrootref.updateChildren(currenthash, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            message_text.setText("");
                            if (databaseError != null) {

                                Log.d("chat_log", databaseError.getMessage().toString());
                            }
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
        {message_text.setHint("Enter the Message");}
    }
    private void initChatMessage(){
       modelList=new ArrayList<>();
       DatabaseReference reference=FirebaseDatabase.getInstance().getReference("chat").child("message");
       reference.keepSynced(true);
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot b:dataSnapshot.getChildren()){
                   MDL_Message n=b.getValue(MDL_Message.class);
                   modelList.add(n);

               }
               ADPTR_Message g=new ADPTR_Message(modelList,getContext());
               messageRecycler.setAdapter(g);
               g.notifyDataSetChanged();
               //messageRecycler.setHasFixedSize(true);
               messageRecycler.smoothScrollToPosition(messageRecycler.getAdapter().getItemCount());

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
   }
    private void initViewComponents(View view){
       send=view.findViewById(R.id.sendmessage);
       message_text=view.findViewById(R.id.message_id);
       messageRecycler=view.findViewById(R.id.message_recycler_id);
   }
    private void initRecyclerVw(){
       LinearLayoutManager line=new LinearLayoutManager(getContext());
       line.setStackFromEnd(true);
       messageRecycler.setLayoutManager(line);
       getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
   }
    public String getTime(long var){
        int seconds = (int) (var/ 1000) % 60 ;
        int minutes = (int) ((var/ (1000*60)) % 60);
        int hours   = (int) ((var/ (1000*60*60)) % 24);
        String t=Integer.toString(hours)+":"+Integer.toString(minutes)+":"+Integer.toString(seconds);
        return  t;
    }
}
