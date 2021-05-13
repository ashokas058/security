package com.example.ashok_ray.security;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ssh_setting extends AppCompatActivity {
Button setUp;
EditText hostName_edit,userName_edit,password_edit,port_edit;
int port;
String port_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssh_setting);
        hostName_edit=findViewById(R.id.hostname_id);
        port_edit=findViewById(R.id.port_id);
        userName_edit=findViewById(R.id.username_id_ssh);
        password_edit=findViewById(R.id.password_ssh_id);
        setUp=findViewById(R.id.setup_id_ssh);
        setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hostname=hostName_edit.getText().toString();
                port=Integer.parseInt(port_edit.getText().toString());
                port_str=port_edit.getText().toString();
                String username=userName_edit.getText().toString();
                String password=password_edit.getText().toString();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("ssh");
                HashMap hashMap=new HashMap();
                hashMap.put("hostname",hostname);
                hashMap.put("port",port_str);
                hashMap.put("username",username);
                hashMap.put("password",password);
                databaseReference.setValue(hashMap);
                //ssh_connection sf=new ssh_connection();

            }
        });
    }



}
