package com.example.ashok_ray.security.Module_HOME;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ashok_Ray on 15-01-2019.
 */

public class CLS_HomeViewpager extends FragmentPagerAdapter {
    ArrayList<Fragment> fraglist=new ArrayList<>();
    ArrayList<String> fragtitle=new ArrayList<String>();
    public CLS_HomeViewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fraglist.get(position);
    }

    @Override
    public int getCount() {
        return fraglist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragtitle.get(position);
    }
    public void addfragment(Fragment fragment,String title){
    fraglist.add(fragment);
    fragtitle.add(title);
    }
}
