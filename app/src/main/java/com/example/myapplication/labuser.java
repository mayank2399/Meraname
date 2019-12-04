package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class labuser extends AppCompatActivity {
    EditText name,age,contact,disease,address;
    Button submit;
    FirebaseFirestore firestore;
    FirebaseAuth.AuthStateListener mAuthList;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labuser);

        //
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        name = (EditText) findViewById(R.id.USERNAME);
        age = (EditText) findViewById(R.id.conpass);
        contact = (EditText) findViewById(R.id.pass);
        disease = (EditText) findViewById(R.id.spel);
        address = (EditText) findViewById(R.id.user);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }

            private void submit() {
                String n = name.getText().toString();
                String a = age.getText().toString();
                String c = contact.getText().toString();
                String d = disease.getText().toString();
                String add = address.getText().toString();
                Map<String, Object> m = new HashMap<>();
                m.put("name", n);
                m.put("address", add);
                m.put("age", a);
                m.put("contact", c);
                m.put("disease", d);
                firestore.collection("lab").document(name.getText().toString()).set(m);
                Toast.makeText(labuser.this, "Register for home lab test", Toast.LENGTH_LONG).show();
            }
        });

    }

}

