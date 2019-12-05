package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.Intent.ACTION_CALL;

public class
user_loggedin extends AppCompatActivity {
    Button lab, ambu, hos, book, chatuser, donate, rmedi, hb;
    EditText name, age, disease;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_loggedin);
        Intent i = getIntent();
        final String n = i.getStringExtra("name");
        ambu = (Button) findViewById(R.id.ambul);
        ambu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+00));//change the number
                startActivity(callIntent);
            }
        });
        hos=(Button)findViewById(R.id.hos);
        hos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number="9210237578";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });
        book=(Button)findViewById(R.id.bookappoint);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_loggedin.this,book.class);
                i.putExtra("name",n);
                startActivity(i);
            }
        });
        chatuser=(Button)findViewById(R.id.chatuser);
        chatuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_loggedin.this,chatuser.class);
                i.putExtra("name",n);
                startActivity(i);
            }
        });
        lab=(Button)findViewById(R.id.lab);
        lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_loggedin.this,labuser.class);

                startActivity(i);
            }
        });
        donate=(Button)findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_loggedin.this,donate.class);
                startActivity(i);
            }
        });
        rmedi=(Button)findViewById(R.id.retmedi);
        rmedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_loggedin.this,mediret.class);
                startActivity(i);
            }
        });
        //
        hb=(Button)findViewById(R.id.hbeat);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_loggedin.this,sendrec.class);
                i.putExtra("name",n);
                startActivity(i);

            }
        });
    }
}
