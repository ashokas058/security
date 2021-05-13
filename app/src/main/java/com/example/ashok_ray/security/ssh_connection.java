package com.example.ashok_ray.security;

/**
 * Created by Ashok on 5/7/2019.
 */
import android.app.Activity;

import android.content.Context;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class ssh_connection {
    private static final String TAG = "Chilkat";
    public String hostname;
    public int channelNum, port;
    public Context context;

    public ssh_connection(String hostname, int channelNum, int port, Context context) {
        this.hostname = hostname;
        this.channelNum = channelNum;
        this.port = port;
        this.context = context;
    }
}

