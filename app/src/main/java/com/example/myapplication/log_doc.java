package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class log_doc extends AppCompatActivity {
TextView name,pass;
FirebaseFirestore firestore;
FirebaseAuth mAuth;
Button login,forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_doc);
        mAuth=FirebaseAuth.getInstance();
        forget=(Button)findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(log_doc.this,forget_doc.class);
                startActivity(i);
            }
        });
        name=(TextView)findViewById(R.id.USERNAME);
        pass = (TextView) findViewById(R.id.pass);
        firestore=FirebaseFirestore.getInstance();
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    private void login() {

        final String n=name.getText().toString();
        String p=pass.getText().toString();
        mAuth.signInWithEmailAndPassword(n,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {     firestore.collection("Doctor").whereEqualTo("type","confirm")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(!task.getResult().getDocuments().isEmpty()) {
                                    Map m = task.getResult().getDocuments().get(0).getData();
                                    Intent i=new Intent(log_doc.this,doc_loggedin.class);
                                    i.putExtra("name",n);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(log_doc.this, "Not a valid", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
                else {
                    Toast.makeText(log_doc.this, "Incorrect userneame or password", Toast.LENGTH_LONG).show();

                }
            }
        });


    }



    @Override
    public void onStart() {
        super.onStart();

    }
}
