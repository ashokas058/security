package com.example.ashok_ray.security.Module_SETTINGS;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.osafirebase;
import com.google.firebase.database.DatabaseReference;

public class ACT_UpdateUser extends AppCompatActivity {
static String uid,type;
DatabaseReference databaseReference;
EditText showEdit;
Button changebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
        showEdit=findViewById(R.id.show_editText_id);
        changebt=findViewById(R.id.change_bt);
        Intent b=getIntent();
        Bundle nb=b.getExtras();
         uid=nb.getString("uid");
         type=nb.getString("type");
      changebt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              databaseReference= osafirebase.databaseReferenceOSA.child("user").child(uid);
              if(uid!=null&&type!=null){
                  if (type=="changeUsername"){
                      String name=showEdit.getText().toString();
                      databaseReference.child("user_name").setValue(name);
                      Toast.makeText(ACT_UpdateUser.this, "updated Username", Toast.LENGTH_SHORT).show();

                  }
                  else{
                      String phone=showEdit.getText().toString();
                      databaseReference.child("Phone").setValue(phone);
                      Toast.makeText(ACT_UpdateUser.this, "updated Phone", Toast.LENGTH_SHORT).show();


                  }
              }

          }
      });
    }
    public void inputsate(){
        if(uid!=null&&type!=null){
            if (type=="changeUsername"){
                showEdit.setHint("Change Name");

            }
            if (type=="changePhonenumber"){
                showEdit.setHint("change contact");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
