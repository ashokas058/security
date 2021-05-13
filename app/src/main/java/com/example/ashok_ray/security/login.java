package com.example.ashok_ray.security;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashok_ray.security.Module_HOME.ACT_Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText user_edt;
    EditText paswd_edt;
    Button login_bt;
    String password_str;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String email_patern1;
    TextView click_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_edt=findViewById(R.id.usrname_id); //Findout field
        paswd_edt=findViewById(R.id.pass_id);
        login_bt=findViewById(R.id.log_bt);
        email_patern1 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        progressDialog=new ProgressDialog(login.this);
        progressDialog.setTitle("LogingIn");
        progressDialog.setMessage("Please Waite");
        click_regist=findViewById(R.id.regist_click_id);
        click_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regist_intent=new Intent(getApplicationContext(),register.class);
                startActivity(regist_intent);
            }
        });

        //-----------------------------------------
        firebaseAuth=FirebaseAuth.getInstance();
        //-------------------------------------------------------
        //----------------------------------------------------------
            login_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String mailpattern = "[a-zA-Z0-9._-]+@[a-z]+[.]+[a-z]+";
                    final String email_str=user_edt.getText().toString().trim();   //String defined
                    password_str=paswd_edt.getText().toString().trim();
                    if(isemail(user_edt)==false) {
                        user_edt.setError("Enter Valid Email");
                    }
                    else if(password_str.length()<6){
                        paswd_edt.setError("Enter Valid Password");
                    }
                    else {
                        progressDialog.show();
                        firebaseAuth.signInWithEmailAndPassword(email_str, password_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.hide();
                                    Intent main_home=new Intent(login.this, ACT_Home.class);
                                    startActivity(main_home);
                                } else {
                                    progressDialog.hide();
                                    //Toast.makeText(login.this, "Invalid", Toast.LENGTH_LONG).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }



                }


            });


    }
    boolean isempty(EditText text) {
        CharSequence char_value = text.getText().toString().trim();
        return (TextUtils.isEmpty(char_value));
    }

    boolean isemail(EditText email) {
        String em=email.getText().toString().trim();
        return(em.matches(email_patern1));
    }
}
