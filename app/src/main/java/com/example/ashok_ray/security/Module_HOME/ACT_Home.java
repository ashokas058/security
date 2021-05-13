package com.example.ashok_ray.security.Module_HOME;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.Module_CHAT.FRGMT_GroupChat;
import com.example.ashok_ray.security.Module_LEARN.FRGMT_Learn;
import com.example.ashok_ray.security.Module_AUTH.ACT_Login;
import com.example.ashok_ray.security.Module_PROFILE.FRGMT_Profile;
import com.example.ashok_ray.security.Module_SETTINGS.ACT_Settings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ACT_Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth newauth;
    FirebaseUser newuser;
    DatabaseReference databaseReference;
    TabLayout tabLayout;
    ViewPager viewPager;
    FirebaseAuthUserCollisionException colo;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth.IdTokenListener tokenListener;
    ArrayList<Fragment> listviewpager = new ArrayList<Fragment>();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView pic;
    DrawerLayout drawer;
    AppBarLayout toolb;
    TextView pro_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViewComponent();
        initFragments();
        initFireBaseAuth();
        navigationView.setNavigationItemSelectedListener(this);
        initNavViewComponents();
        initFirebaseUserCheck();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_id:
                newauth.signOut();
                break;
            case R.id.home_id:
                viewPager.setCurrentItem(0);
               drawer.closeDrawers();
                break;
            case R.id.learn_id:
                viewPager.setCurrentItem(1);
                break;
            case R.id.chat_id:
                viewPager.setCurrentItem(2);
                break;
            case R.id.setting_id:
                Intent intent=new Intent(getApplicationContext(), ACT_Settings.class);
                startActivity(intent);




        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(FirebaseAuth.getInstance().getCurrentUser().getUid()!=null){
            String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
            databaseReference=FirebaseDatabase.getInstance().getReference("user").child(uid).child("status");
            databaseReference.setValue("ofline");
        }
        else{
            Intent log=new Intent(getApplicationContext(), ACT_Login.class);
            startActivity(log); }
    }

    private void initViewComponent(){
        drawerLayout=findViewById(R.id.drawer_id);
        viewPager = findViewById(R.id.viewpager_id);
        drawer=findViewById(R.id.drawer_id);
        toolb=findViewById(R.id.toolbar1);
        tabLayout = findViewById(R.id.tab_id);
        navigationView=findViewById(R.id.navigationView_id);
    }
    private void initFragments() {
        final CLS_HomeViewpager adapter = new CLS_HomeViewpager(getSupportFragmentManager());
        adapter.addfragment(new FRGMT_Profile(),"Profile");
        adapter.addfragment(new FRGMT_Learn(), "Learn");
        adapter.addfragment(new FRGMT_GroupChat(), "Chat");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }
    private void initFireBaseAuth(){
        newauth=FirebaseAuth.getInstance();
        newauth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Intent log=new Intent(getApplicationContext(), ACT_Login.class);
                    startActivity(log);
                }

            }
        });
    }
    private void initFirebaseUserCheck(){
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            if (uid != null) {

                databaseReference = FirebaseDatabase.getInstance().getReference("user").child(uid);
                databaseReference.keepSynced(true);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("image_url")) {
                            String url = dataSnapshot.child("image_url").getValue().toString();
                            Picasso.get().load(url).into(pic);
                        }
                        String nam = dataSnapshot.child("user_name").getValue().toString();
                        pro_name.setText(nam);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                databaseReference.child("status").setValue("ofline");
            }
        }
        else{
            Intent intent =new Intent(getApplicationContext(), ACT_Login.class);
            startActivity(intent);
        }
    }
    private void initNavViewComponents(){
       View header=navigationView.getHeaderView(0);
       pic=header.findViewById(R.id.profilePic_id);
       pro_name=header.findViewById(R.id.profileName_id);
    }
}
