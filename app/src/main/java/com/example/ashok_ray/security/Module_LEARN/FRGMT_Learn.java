package com.example.ashok_ray.security.Module_LEARN;

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

import com.example.ashok_ray.security.R;
import com.example.ashok_ray.security.DATA_MODEL.MDL_Learn;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by Ashok_Ray on 04-01-2019.
 */

public class FRGMT_Learn extends Fragment {
    RecyclerView learnrecycler;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.learn_fragment,container,false);

        learnrecycler=view.findViewById(R.id.learn_recycler_id);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        learnrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        initLearnDetails();


    }

   private  void initLearnDetails(){
       database=FirebaseDatabase.getInstance();
       reference=database.getReference("user").child("beginners");
       reference.keepSynced(true);
       FirebaseRecyclerAdapter<MDL_Learn,learnviewholder>firebase_adapt=new FirebaseRecyclerAdapter<MDL_Learn, learnviewholder>(MDL_Learn.class,R.layout.learn_recycler_layout_new,learnviewholder.class,reference) {
           @Override
           protected void populateViewHolder(learnviewholder viewHolder, MDL_Learn model, int position) {
               viewHolder.setname(model.getTitle_top());
               viewHolder.setimage(model.getImage_url());
               viewHolder.setfulltext(model.getText_data());
           }
       };
       learnrecycler.setAdapter(firebase_adapt);
   }

    public static class  learnviewholder extends RecyclerView.ViewHolder{
        TextView top_text;
        ImageView imageView;
        TextView fullrev;

        public learnviewholder(View itemView) {
            super(itemView);
            top_text = itemView.findViewById(R.id.cat_top_id);
            imageView = itemView.findViewById(R.id.temp_image_id);
            fullrev = itemView.findViewById(R.id.fulltext_id);
        }

        public void setname(String name) {
            top_text.setText(name);
        }

        public void setimage(String url) {
            //Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(imageView);
            Picasso.get().load(url).into(imageView);
        }

        public void setfulltext(String full) {
            fullrev.setText(full);

        }
    }

}
