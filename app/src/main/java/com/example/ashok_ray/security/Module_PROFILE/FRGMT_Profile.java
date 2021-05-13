package com.example.ashok_ray.security.Module_PROFILE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.login;
import com.example.ashok_ray.security.model_profile;
import com.example.ashok_ray.security.model_recycler;
import com.example.ashok_ray.security.news_adapter;
import com.example.ashok_ray.security.news_model_class;
import com.example.ashok_ray.security.recycler_adapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ashok_Ray on 24-02-2019.
 */

public class FRGMT_Profile extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    RecyclerView recycler_instant_lern, recycler_news;
    //static final String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase database;
    DatabaseReference reference, newsreference;
    ImageView profile_pic;
    TextView profile_name;
    model_profile profile_object;
    ArrayList<model_recycler> model_ob_list = new ArrayList<model_recycler>();
    ArrayList<news_model_class> newshold = new ArrayList<news_model_class>();
    String image;

    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pro_ugrade, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler_instant_lern = view.findViewById(R.id.recycler_horizon1);
        recycler_news = view.findViewById(R.id.recycler_horizon2);
        profile_pic = view.findViewById(R.id.new_profile1id);
        profile_name = view.findViewById(R.id.new_profile1_name_);
        recycler_instant_lern.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
       recycler_news.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onStart() {
        super.onStart();

        load_profile(profile_pic, profile_name);

//load_profile(profile,profile_name);
      //  context = getContext();
try {
    firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase f = FirebaseDatabase.getInstance();
    reference = FirebaseDatabase.getInstance().getReference("user").child("beginners");
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                model_recycler ob = data.getValue(model_recycler.class);
                model_ob_list.add(ob);
            }
            recycler_adapter madapter = new recycler_adapter(context, model_ob_list);
            recycler_instant_lern.setAdapter(madapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    reference=FirebaseDatabase.getInstance().getReference("user").child("news");
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot dat:dataSnapshot.getChildren()){
                news_model_class news_mod=dat.getValue(news_model_class.class);
                newshold.add(news_mod);

            }
            news_adapter adapter=new news_adapter(newshold,getContext());
            recycler_news.setAdapter(adapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
        /*
        if (FirebaseAuth.getInstance() != null) {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("user").child("beginners");
                reference.keepSynced(true);
                FirebaseRecyclerAdapter<model_recycler, FRGMT_Profile.lernholder> firebaseadapter = new FirebaseRecyclerAdapter<model_recycler, FRGMT_Profile.lernholder>(model_recycler.class, R.layout.recycler_layout_design, FRGMT_Profile.lernholder.class, reference) {
                    @Override
                    protected void populateViewHolder(FRGMT_Profile.lernholder viewHolder, model_recycler model, int position) {
                        viewHolder.setname(model.getTitle_top());
                        viewHolder.setimage(model.getImage_url());
                        viewHolder.setfulltext(model.getText_data());
                    }
                };
                recycler_instant_lern.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recycler_instant_lern.setAdapter(firebaseadapter);
                newsreference = database.getReference("user").child("news");
                newsreference.keepSynced(true);
                FirebaseRecyclerAdapter<news_model_class, FRGMT_Profile.newsholder> newsadapter = new FirebaseRecyclerAdapter<news_model_class, FRGMT_Profile.newsholder>(news_model_class.class, R.layout.news_recycler_model_xml, FRGMT_Profile.newsholder.class, newsreference) {
                    @Override
                    protected void populateViewHolder(FRGMT_Profile.newsholder viewHolder, news_model_class model, int position) {
                        viewHolder.setnewsHead(model.getTitle());
                        viewHolder.setnewsreport(model.getFullnews());
                        viewHolder.setImagereport(model.getUrl());
                    }
                };
                recycler_news.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recycler_news.setAdapter(newsadapter);

            } else {
                Intent intent = new Intent(getContext(), login.class);
                startActivity(intent);
            }

        } */
}catch (Exception e){
    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

}
    }

    public static class lernholder extends RecyclerView.ViewHolder {
        TextView top_text;
        ImageView imageView;
        TextView fullrev;

        public lernholder(View itemView) {
            super(itemView);
            top_text = itemView.findViewById(R.id.cat_top_id);
            imageView = itemView.findViewById(R.id.temp_image_id);
            fullrev = itemView.findViewById(R.id.fulltext_id);
        }

        public void setname(String name) {
            top_text.setText(name);
        }

        public void setimage(String url) {
            Picasso.get().load(url).resize(90, 90).into(imageView);
        }

        public void setfulltext(String full) {
            fullrev.setText(full);

        }

    }

    public static class newsholder extends RecyclerView.ViewHolder {
        TextView headline;
        TextView report;
        ImageView image_report;


        public newsholder(View itemView) {
            super(itemView);
            headline = itemView.findViewById(R.id.news_tit_id);
            report = itemView.findViewById(R.id.full_news_id);
            image_report = itemView.findViewById(R.id.temp_image_news_id);

        }

        public void setnewsHead(String headline_str) {
            headline.setText(headline_str);
        }

        public void setnewsreport(String report_str) {
            report.setText(report_str);
        }

        public void setImagereport(String url) {
            Picasso.get().load(url).resize(120, 120).into(image_report);

        }


    }

    public void load_profile(final ImageView pro, final TextView pronm) {
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth != null) {
            if (firebaseAuth.getCurrentUser() != null) {
                String uid = firebaseAuth.getCurrentUser().getUid();
                reference = FirebaseDatabase.getInstance().getReference("user").child(uid);
                reference.keepSynced(true);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        model_profile model = dataSnapshot.getValue(model_profile.class);
                        pronm.setText(model.getUser_name());
                        String image_url = model.getImage_url();
                        Picasso.get().load(image_url).into(pro);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Intent intent = new Intent(getContext(), login.class);
                startActivity(intent);
            }
        } else {

            Intent intent = new Intent(getContext(), login.class);
            startActivity(intent);
        }
    }
}
