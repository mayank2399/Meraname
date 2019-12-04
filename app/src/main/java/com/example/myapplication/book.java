package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class book extends AppCompatActivity {
EditText name,age,disease;
Button confirm;
FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent i=getIntent();
        final String username=i.getStringExtra("name");
        firestore=FirebaseFirestore.getInstance();
        name=(EditText)findViewById(R.id.USERNAME);
        age=(EditText)findViewById(R.id.age);
        disease=(EditText)findViewById(R.id.disease);
        confirm=(Button)findViewById(R.id.book);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book();

            }

            private void Book() {
                String n=name.getText().toString();
                String a=age.getText().toString();
                String d=disease.getText().toString();
                Map<String,Object> m=new HashMap<>();
                m.put("name",n);
                m.put("age",a);
                m.put("disease",d);
                firestore.collection("Appoint").document(d).set(m);
                Toast.makeText(book.this,"Your appointment has been confirmed doctor will send you direct message for date and time",Toast.LENGTH_LONG).show();;
                Intent i=new Intent(book.this,user_loggedin.class);
                i.putExtra("name",n);
                startActivity(i);
            }
        });

    }
}
