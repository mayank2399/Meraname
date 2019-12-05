package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createadmin extends AppCompatActivity {
EditText e,n,p,cp,ph;
FirebaseFirestore firestore;
FirebaseAuth mAuth;
Button subm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createadmin);
        mAuth=FirebaseAuth.getInstance();
       e=(EditText)findViewById(R.id.aemail);
        p=(EditText)findViewById(R.id.apass);
        firestore=FirebaseFirestore.getInstance();
        cp=(EditText)findViewById(R.id.acpass);
        n=(EditText)findViewById(R.id.aname);
        ph=(EditText)findViewById(R.id.ph);
        subm=(Button)findViewById(R.id.sub);
        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
                private void signup(){
                    final String na=n.getText().toString();
                    final String email=e.getText().toString();
                    String pass=p.getText().toString();
                    String conpass=cp.getText().toString();
                    final String phone=ph.getText().toString();
                    final Map<String, Object> map = new HashMap<>();
                    map.put("name", na);
                    map.put("username",email);
                    map.put("Phone",phone);

                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(createadmin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        firestore.collection("Doctor").document(email).set(map);
                                        Toast.makeText(createadmin.this,"Please wait!! we have to validate your soeciality",Toast.LENGTH_LONG).show();
                                        Intent i=new Intent(createadmin.this,MainActivity.class);
                                        startActivity(i);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(createadmin.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                                };
                    });
    }
}
