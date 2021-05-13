package com.example.ashok_ray.security;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashok_ray.security.Module_HOME.ACT_Home;
import com.squareup.picasso.Picasso;

public class reader_interface extends AppCompatActivity {
TextView title,fulltext;
ImageView sub_image;
FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_interface);
        title=findViewById(R.id.top_id11);
        fulltext=findViewById(R.id.full_id_11);
        sub_image=findViewById(R.id.imageView3);
        floatingActionButton=findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_terminal_id,new terminal()).commit();
            }
        });
Bundle b=getIntent().getExtras();
//String tit=b.getString("top_title");
title.setText(b.getString("top_title"));
fulltext.setText(b.getString("full_text"));
String url=b.getString("image_url");
        Picasso.get().load(url).into(sub_image);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homein= new Intent(getApplicationContext(), ACT_Home.class);
        startActivity(homein);
    }
}
