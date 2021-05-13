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

import com.example.ashok_ray.security.DATA_MODEL.MDL_Learn;
import com.example.ashok_ray.security.DATA_MODEL.MDL_News;
import com.example.ashok_ray.security.Module_AUTH.ACT_Login;
import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.DATA_MODEL.MDL_User;
import com.google.firebase.auth.FirebaseAuth;
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
    RecyclerView recycler_instant_lern, recycler_news;
    FirebaseDatabase database;
    DatabaseReference reference, newsreference;
    ImageView profile_pic;
    TextView profile_name;
    MDL_User profile_object;
    ArrayList<MDL_Learn> model_ob_list = new ArrayList<MDL_Learn>();
    ArrayList<MDL_News> newshold = new ArrayList<MDL_News>();
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
        initViewComponents(view);
        initRecyclerComponents(view);
        initRecyclerViews();
        load_profile(profile_pic, profile_name);
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
                        MDL_User model = dataSnapshot.getValue(MDL_User.class);
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
                Intent intent = new Intent(getContext(), ACT_Login.class);
                startActivity(intent);
            }
        } else {

            Intent intent = new Intent(getContext(), ACT_Login.class);
            startActivity(intent);
        }
    }
    private void initViewComponents(View view){
        recycler_instant_lern = view.findViewById(R.id.recycler_horizon1);
        recycler_news = view.findViewById(R.id.recycler_horizon2);
        profile_pic = view.findViewById(R.id.new_profile1id);
        profile_name = view.findViewById(R.id.new_profile1_name_);
    }
    private void initRecyclerViews(){
        try {
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseDatabase f = FirebaseDatabase.getInstance();
            reference = FirebaseDatabase.getInstance().getReference("user").child("beginners");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        MDL_Learn ob = data.getValue(MDL_Learn.class);
                        model_ob_list.add(ob);
                    }
                    ADPTR_Learn madapter = new ADPTR_Learn(context, model_ob_list);
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
                        MDL_News news_mod=dat.getValue(MDL_News.class);
                        newshold.add(news_mod);

                    }
                    ADPTR_News adapter=new ADPTR_News(newshold,getContext());
                    recycler_news.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    private void initRecyclerComponents(View view){
        recycler_instant_lern.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycler_news.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));

    }
}
