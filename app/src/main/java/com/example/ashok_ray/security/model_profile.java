package com.example.ashok_ray.security;

/**
 * Created by Ashok_Ray on 14-01-2019.
 */

public class model_profile {
    String user_name,user_email,image_url,status,key;
    model_profile() {
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public model_profile(String user_name, String user_email, String image_url, String status, String key) {

        this.user_name = user_name;
        this.user_email = user_email;
        this.image_url = image_url;
        this.status = status;
        this.key = key;
    }
}



