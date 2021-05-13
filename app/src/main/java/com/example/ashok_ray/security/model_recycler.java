package com.example.ashok_ray.security;

/**
 * Created by Ashok_Ray on 25-12-2018.
 */

public class model_recycler  {
    String title_top,image_url,text_data;
    model_recycler(){}

    public model_recycler(String title_top, String image_url, String text_data) {
        this.title_top = title_top;
        this.image_url = image_url;
        this.text_data = text_data;
    }

    public String getTitle_top() {
        return title_top;
    }

    public void setTitle_top(String title_top) {
        this.title_top = title_top;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getText_data() {
        return text_data;
    }

    public void setText_data(String text_data) {
        this.text_data = text_data;
    }
}
