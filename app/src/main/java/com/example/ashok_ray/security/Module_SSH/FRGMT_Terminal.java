package com.example.ashok_ray.security.Module_SSH;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ashok_ray.security.R;

/**
 * Created by Ashok_Ray on 08-03-2019.
 */

public class FRGMT_Terminal extends Fragment {
EditText term_input;
TextView termshow;
Button cmd;
public CLS_SshConnection ssh_ob;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.terminal_layout,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        term_input=view.findViewById(R.id.term_input);
        termshow=view.findViewById(R.id.term_show_id);
        cmd=view.findViewById(R.id.cmd_send_ssh_id);
    }

    @Override
    public void onStart() {
        super.onStart();
       /* DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("ssh");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MDL_Ssh mod_ssh=dataSnapshot.getValue(MDL_Ssh.class);
                String hostname=mod_ssh.getHostname();
                String port=mod_ssh.getPort();
                String username=mod_ssh.getUsername();
                String password=mod_ssh.getPassword();
                int pot=Integer.parseInt(port);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */




    }
}
