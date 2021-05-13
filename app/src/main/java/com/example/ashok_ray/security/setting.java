package com.example.ashok_ray.security;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashok_ray.security.Module_HOME.ACT_Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class setting extends AppCompatActivity implements View.OnClickListener {
    ImageView temp;
    TextView change_username,change_phone,sshServer;
    Switch phoneNumber,onLine;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        temp=findViewById(R.id.pro_change_id);
        temp.setOnClickListener(this);
        change_phone=findViewById(R.id.change_phone_user_id);
        sshServer=findViewById(R.id.ssh_server_setting_id);
        sshServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ssh_setting.class);
                startActivity(intent);
            }
        });
        change_phone.setOnClickListener(this);
        change_username=findViewById(R.id.change_name_user_id);
        change_username.setOnClickListener(this);
        phoneNumber=findViewById(R.id.switch_contact_id);
        phoneNumber.setChecked(true);
        onLine=findViewById(R.id.switch_online_id);
        onLine.setChecked(true);
        phoneNumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){


                }
            }
        });
        onLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri ur = data.getData();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            final String uid = firebaseUser.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("user").child(uid);

            storageReference = FirebaseStorage.getInstance().getReference("Profile_pic").child("propic" + uid);
            storageReference.putFile(ur).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String url = taskSnapshot.getDownloadUrl().toString();
                    databaseReference.child("image_url").setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });


            try {
                Bitmap bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ur);
                temp.setImageBitmap(bit);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(this, "Eroor in uploading", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent=new Intent(getApplicationContext(), ACT_Home.class);
        startActivity(homeIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
            if (uid!=null){
                databaseReference = FirebaseDatabase.getInstance().getReference("user").child(uid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("image_url")){
                            String url=dataSnapshot.child("image_url").getValue().toString();
                            Picasso.get().load(url).into(temp);
                        }
                        else{
                            Toast.makeText(setting.this, "upload Profile", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        }
        else{
            //Intent ACT_Home =new Intent(getApplicationContext(), com.example.ashok_ray.security.ACTIVITY.ACT_Home.class);
            Toast.makeText(this, "unable to load Profile", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pro_change_id:
                Intent b=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                b.setType("image/*");
                startActivityForResult(b,101);
                break;
            case R.id.change_name_user_id:
                if (FirebaseAuth.getInstance().getCurrentUser()!=null)
                { String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    if (uid!=null){
                        Intent intent=new Intent(getApplicationContext(),changeUser.class);
                        Bundle bb=new Bundle();
                        bb.putString("uid",uid);
                        bb.putString("type","changeUsername");
                        intent.putExtras(bb);
                        startActivity(intent);
                    }
                }
                else {}
                break;
            case R.id.change_phone_user_id:
                if (FirebaseAuth.getInstance().getCurrentUser()!=null)
                { String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    if (uid!=null){
                        Intent intent=new Intent(getApplicationContext(),changeUser.class);
                        Bundle bb=new Bundle();
                       bb.putString("uid",uid);
                       bb.putString("type","changePhonenumber");
                       intent.putExtras(bb);
                        startActivity(intent);
                    }
                }
                else {}
                break;





        }
    }
}
