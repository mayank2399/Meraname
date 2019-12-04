package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class doc_loggedin extends AppCompatActivity {
    TextView user;
    Button logout,chatpat,seeapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_loggedin);
        //
        Intent i=getIntent();
        final String name=i.getStringExtra("name");
        user=(TextView)findViewById(R.id.USERNAME);
        logout=(Button)findViewById(R.id.logout);
        seeapp=(Button)findViewById(R.id.see_app);
        seeapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                see();
            }

            private void see() {
                Intent i=new Intent(doc_loggedin.this,seeappointment.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doc_loggedin.this,MainActivity.class);
                startActivity(i);
            }
        });
        chatpat=(Button)findViewById(R.id.chatpat2);
        chatpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doc_loggedin.this,chatdoc.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });


    }
}
