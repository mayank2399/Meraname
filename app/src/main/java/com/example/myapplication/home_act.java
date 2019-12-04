package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_act extends AppCompatActivity {
Button log_doc,sign_doc,log_pat,sign_pat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);
        log_doc=(Button)findViewById(R.id.log_doc);
        log_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home_act.this,log_doc.class);
                startActivity(i);
            }
        });
        sign_doc=(Button)findViewById(R.id.sign_user);
        sign_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home_act.this,Sign_Doc.class);
                startActivity(i);
            }

        });
        log_pat=(Button)findViewById(R.id.loginpat);
        log_pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home_act.this,log_user.class);
                startActivity(i);
            }
        });
        sign_pat=(Button)findViewById(R.id.signuser);
        sign_pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home_act.this,Sign_User.class);
                startActivity(i);
            }
        });

    }
}
