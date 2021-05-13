package com.example.ashok_ray.security;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashok_ray.security.Module_HOME.ACT_Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register extends AppCompatActivity {
    EditText fullname;
    EditText email_reg;
    EditText pass_reg;
    EditText conf_pass_reg;
    FirebaseAuth firebaseAuth;
    DatabaseReference mreference;
    FirebaseDatabase mdatabase;
    FirebaseUser current_user;
    String uid;
    Context context;
    ProgressDialog progressDialog;
    String email_patern, pass_temp, conf_pass_temp, email_temp;
    Button register_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(register.this);
        progressDialog.setTitle("Registering");
        progressDialog.setMessage("Please wait .....");
        setContentView(R.layout.activity_register);
        //--------------------------------------
        fullname = findViewById(R.id.fullname_reg_id);
        email_reg = findViewById(R.id.email_reg_id);
        pass_reg = findViewById(R.id.pass_reg_id);
        conf_pass_reg = findViewById(R.id.confpass_reg_id);
        register_bt = findViewById(R.id.reg_reg_bt);
        //-------------------------------------
        email_patern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        firebaseAuth = FirebaseAuth.getInstance();
        context=register.this;
        progressDialog.setTitle("Registering");

        progressDialog.setMessage("Please wait");
        //---------------------------------------
        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String full_name = fullname.getText().toString().trim();
                final String email_temp = email_reg.getText().toString().trim();
                final String pass_temp = pass_reg.getText().toString().trim();
                final String conf_pass_temp = conf_pass_reg.getText().toString().trim();
                if(isempty(fullname)) {
                    fullname.setError("Enter Full Name");
                }
                    if(isemail(email_reg)==false) {
                        email_reg.setError("Enter Valid Email");
                    }
                        else if(ispasswordsame(pass_reg,conf_pass_reg)==false) {
                        pass_reg.setError("Password not Same");
                        conf_pass_reg.setError("Password Not Same");
                    }
                        else {
                        progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email_reg.getText().toString().trim(),pass_reg.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.hide();
                                current_user=firebaseAuth.getCurrentUser();
                                uid=current_user.getUid();
                                mdatabase=FirebaseDatabase.getInstance();
                                mreference=mdatabase.getReference("user").child(uid);
                                HashMap newhashmap=new HashMap();
                                newhashmap.put("user_name",full_name);
                                newhashmap.put("user_email",email_temp);
                                newhashmap.put("key",uid);
                                mreference.setValue(newhashmap);
                                Intent home_activity=new Intent(register.this, ACT_Home.class);
                                startActivity(home_activity);
                            }
                            else if(task.getException().getMessage().equals("The email address is already in use by another account.\"")){
                                Toast.makeText(register.this,"The email address is already in use by another account.\"",Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }
            }
        });
    }
        //-------------------------------------------------------


    boolean isempty(EditText text) {
        CharSequence char_value = text.getText().toString().trim();
        return (TextUtils.isEmpty(char_value));
    }

    boolean isemail(EditText email) {
        String em=email.getText().toString().trim();
        return(em.matches(email_patern));
    }

    boolean ispasswordsame(EditText passd, EditText c_passwd) {
        String f_passwd = passd.getText().toString().trim();
        String confirm_passwd = c_passwd.getText().toString().trim();
        if (f_passwd.length() > 6 && f_passwd.equals(confirm_passwd))
            return true;
        else
            return false;
    }
}
