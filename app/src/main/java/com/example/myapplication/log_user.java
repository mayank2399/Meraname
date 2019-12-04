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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class log_user extends AppCompatActivity {
Button login;
EditText user,pass;
FirebaseFirestore firestore;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_user);
        login=(Button)findViewById(R.id.login);
        user=(EditText)findViewById(R.id.USERNAME);
       pass=(EditText) findViewById(R.id.pass);
        firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_user();

            }
        });
    }
    private  void login_user()
    {
        final String n=user.getText().toString();
        String p=pass.getText().toString();
        /*firestore.collection("User").whereEqualTo("Username",n)
                .whereEqualTo("Password",p)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(!task.getResult().getDocuments().isEmpty()) {
                    Map m = task.getResult().getDocuments().get(0).getData();
                    Intent i=new Intent(log_user.this,user_loggedin.class);
                    i.putExtra("name",n);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(log_user.this, "INVALID USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
        mAuth.signInWithEmailAndPassword(n,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {     firestore.collection("User").whereEqualTo("username",n)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(!task.getResult().getDocuments().isEmpty()) {
                                    Map m = task.getResult().getDocuments().get(0).getData();
                                    Intent i=new Intent(log_user.this,user_loggedin.class);
                                    i.putExtra("name",n);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(log_user.this, "Not a valid", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
                else {
                    Toast.makeText(log_user.this, "Incorrect userneame or password", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    }
