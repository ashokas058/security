package com.example.ashok_ray.security;

import java.util.ArrayList;

/**
 * Created by Ashok_Ray on 24-12-2018.
 */

public class categories {
    String main_cat;;
    ArrayList<String> sub_cat=new ArrayList<String>();
    categories(String cat) {
        this.main_cat=cat;
    }

    @Override
    public String toString() {
        return this.main_cat;
    }
}
