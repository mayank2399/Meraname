package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class mediret extends AppCompatActivity {
Button sub;
FirebaseFirestore firestore;
EditText name,quan,mfg,exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediret);
        name= (EditText) findViewById(R.id.mname);
        firestore=FirebaseFirestore.getInstance();
        quan=(EditText)findViewById(R.id.mquan);
        mfg= (EditText) findViewById(R.id.mmfg);
        exp=(EditText)findViewById(R.id.mexp);
        sub=(Button)findViewById(R.id.msub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String q=quan.getText().toString();
                String e=exp.getText().toString();
                String m=mfg.getText().toString();
                /*Map<String,Object> t=new HashMap<>();
                t.put("name", n);
                t.put("quan", q);
                t.put("exp", e);
                t.put("mfg", m);
                firestore.collection("ret").document().set(m);
                Toast.makeText(mediret.this, "We will contact you soon...", Toast.LENGTH_LONG).show();
                String n=name.getText().toString();

               String a=age.getText().toString();
                String d=disease.getText().toString();
                Map<String,Object> m=new HashMap<>();
                m.put("name",n);
                m.put("age",a);
                m.put("disease",d);*/
                Map<String,Object> t=new HashMap<>();
                t.put("name", n);
                t.put("quan", q);
                t.put("exp", e);
                t.put("mfg", m);
                firestore.collection("Return").document(n).set(t);
                Toast.makeText(mediret.this,"Thanks for return Medicine we will contact you soon",Toast.LENGTH_LONG).show();;
                Intent i=new Intent(mediret.this,user_loggedin.class);
                i.putExtra("name",n);
                startActivity(i);
            }
        });




    }
}
