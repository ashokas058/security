package com.example.ashok_ray.security.DATA_MODEL;

/**
 * Created by Ashok on 5/7/2019.
 */

public class MDL_Ssh {
    String hostname,port,username,password;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MDL_Ssh(String hostname, String port, String username, String password) {

        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }
}
