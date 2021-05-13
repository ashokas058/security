package com.example.ashok_ray.security.DATA_MODEL;

/**
 * Created by Ashok_Ray on 18-01-2019.
 */

public class MDL_Message {
    String email,messagetext,time,username,from;
    MDL_Message(){}


    public MDL_Message(String email, String messagetext, String time, String username, String from) {
        this.email = email;
        this.messagetext = messagetext;
        this.username = username;
        this.from = from;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
