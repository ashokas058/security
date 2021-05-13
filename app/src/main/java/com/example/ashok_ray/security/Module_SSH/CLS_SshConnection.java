package com.example.ashok_ray.security.Module_SSH;

/**
 * Created by Ashok on 5/7/2019.
 */
import android.app.Activity;

import android.content.Context;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class CLS_SshConnection {
    private static final String TAG = "Chilkat";
    public String hostname;
    public int channelNum, port;
    public Context context;

    public CLS_SshConnection(String hostname, int channelNum, int port, Context context) {
        this.hostname = hostname;
        this.channelNum = channelNum;
        this.port = port;
        this.context = context;
    }
}

