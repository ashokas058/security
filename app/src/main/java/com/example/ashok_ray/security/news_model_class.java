package com.example.ashok_ray.security;

import android.content.Context;

/**
 * Created by Ashok_Ray on 12-01-2019.
 */

public class news_model_class  {
    String title;
    String fullnews;
    String url;
news_model_class(){

}

    public news_model_class(String title, String fullnews, String url) {
        this.title = title;
        this.fullnews = fullnews;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullnews() {
        return fullnews;
    }

    public void setFullnews(String fullnews) {
        this.fullnews = fullnews;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
