package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Sign_Doc extends AppCompatActivity {
    EditText name,pass,con_pass,special,user;
    Button signdoc;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__doc);
        firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        name=(EditText)findViewById(R.id.USERNAME);
        user=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.pass);
        con_pass=(EditText)findViewById(R.id.conpass);
        special=(EditText)findViewById(R.id.spel);
        signdoc=(Button)findViewById(R.id.sign_user);
        signdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign();
            }
            private void sign() {
                final String n=name.getText().toString();
                String u=user.getText().toString();
                String p=pass.getText().toString();
                String c=con_pass.getText().toString();
                final String s=special.getText().toString();
                final Map<String, Object> map = new HashMap<>();
                map.put("name", n);
                map.put("username", u);
                map.put("speciality", s);
                map.put("type","new");

                mAuth.createUserWithEmailAndPassword(u,p)
                        .addOnCompleteListener(Sign_Doc.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    firestore.collection("Doctor").document(n).set(map);
                                    Toast.makeText(Sign_Doc.this,"Please wait!! we have to validate your soeciality",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(Sign_Doc.this,MainActivity.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Sign_Doc.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

            }


        });
    }
}
