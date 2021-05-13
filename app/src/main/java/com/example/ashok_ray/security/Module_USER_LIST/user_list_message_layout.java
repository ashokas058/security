package com.example.ashok_ray.security.Module_USER_LIST;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.model_profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Ashok on 5/4/2019.
 */

public class user_list_message_layout extends Fragment {
ArrayList<model_profile> m_pro;
//Context context;
RecyclerView m_list_user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_list_message_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m_list_user=view.findViewById(R.id.user_list_Recycler_id);
        m_list_user.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        m_pro=new ArrayList<model_profile>();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("user");
        //databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot shot:dataSnapshot.getChildren()){
                    model_profile md=shot.getValue(model_profile.class);
                    m_pro.add(md);
                }
               user_list_model_recycler_adapter m_adapter=new user_list_model_recycler_adapter(m_pro);
                m_list_user.setAdapter(m_adapter);
                m_list_user.smoothScrollToPosition(m_list_user.getAdapter().getItemCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
