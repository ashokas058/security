package com.example.ashok_ray.security.Module_CHAT;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ashok_ray.security.DATA_MODEL.MDL_User;
import com.example.ashok_ray.security.Module_CHAT.ADPTR_Message;
import com.example.ashok_ray.security.DATA_MODEL.MDL_Message;
import com.example.ashok_ray.security.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ACT_PrivateChat extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText message_text;
    Button clck;
    String username,key,url;
    RecyclerView messageRecycler;
    ArrayList<MDL_Message>  modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_chat_private);
        getSupportActionBar().setTitle(username);
        message_text=findViewById(R.id.send_msg_id);
        clck=findViewById(R.id.send_msg_button_id);
        messageRecycler=findViewById(R.id.message_private_recycler_id);
        clck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
    private void sendmessage() {
        if (!TextUtils.isEmpty(message_text.getText().toString())) {

            String currentuser_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
             databaseReference = FirebaseDatabase.getInstance().getReference("user").child(currentuser_uid);
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
                    String chatuser = "message/" + key;
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
                    DatabaseReference chatrootref = FirebaseDatabase.getInstance().getReference("chat");
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


//----------------------------------------------------------


        }
        else
        {message_text.setText("Enter the Message");}
    }
    public String getTime(long var){
        int seconds = (int) (var/ 1000) % 60 ;
        int minutes = (int) ((var/ (1000*60)) % 60);
        int hours   = (int) ((var/ (1000*60*60)) % 24);
        String t=Integer.toString(hours)+":"+Integer.toString(minutes)+":"+Integer.toString(seconds);
        return  t;
    }

    @Override
    protected void onStart() {
        super.onStart();
        username=getIntent().getExtras().getString("username");
        url=getIntent().getExtras().getString("url");
        key=getIntent().getExtras().getString("key");

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
                ADPTR_Message g=new ADPTR_Message(modelList,getApplicationContext());
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
}
