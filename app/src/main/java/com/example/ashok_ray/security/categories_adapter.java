package com.example.ashok_ray.security;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ashok_Ray on 24-12-2018.
 */

public class categories_adapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<categories> categories=new ArrayList<categories>();
    LayoutInflater layoutInflater;
    categories_adapter(Context context,ArrayList<categories>categories){
        this.context=context;
        this.categories=categories;

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return categories.get(i).sub_cat.size();
    }

    @Override
    public Object getGroup(int i) {
        return categories.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return categories.get(i).sub_cat.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view=layoutInflater.inflate(R.layout.model_main_list,null);
        }
        categories cat_ob=categories.get(i);
        String cat=cat_ob.main_cat;
        TextView textView=view.findViewById(R.id.main_cat_list);
        textView.setText(cat);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view==null){
            view=layoutInflater.inflate(R.layout.model_sub_list,viewGroup);
        }
        TextView textView_sub=view.findViewById(R.id.sub_list_name);
        String child=(String)getChild(i,i1);
        textView_sub.setText(child);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
