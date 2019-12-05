package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adminloged extends AppCompatActivity {
EditText admin;
Button bd,klr,nadmin,vd,lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminloged);
        Intent i=getIntent();
        final String n=i.getStringExtra("name");
        admin=(EditText)findViewById(R.id.admin);
        admin.setText(n);
        bd=(Button)findViewById(R.id.bdonor);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(adminloged.this,blooddonor.class);
                startActivity(i);
            }
        });
        klr=(Button)findViewById(R.id.knowaboutmedi);
        klr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(adminloged.this,leftmedi.class);
                startActivity(i);
            }
        });
        nadmin=(Button)findViewById(R.id.newadmin);
        nadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(adminloged.this,createadmin.class);
                startActivity(i);
            }
        });
        lm=(Button)findViewById(R.id.labtest);
        lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(adminloged.this,lab.class);
                startActivity(i);

            }
        });

    }
}
