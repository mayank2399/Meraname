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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class Admin extends AppCompatActivity {
Button Login;
FirebaseAuth mAuth;
FirebaseFirestore firestore;
EditText name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        firestore=FirebaseFirestore.getInstance();
        name=(EditText)findViewById(R.id.name);
        pass=(EditText)findViewById(R.id.pass);
        Login=(Button)findViewById(R.id.LOGIN);
        mAuth=FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n=name.getText().toString();
                String p=pass.getText().toString();

                mAuth.signInWithEmailAndPassword(n,p).addOnCompleteListener(Admin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {     firestore.collection("Admin").whereEqualTo("username",n)
                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(!task.getResult().getDocuments().isEmpty()) {
                                            Map m = task.getResult().getDocuments().get(0).getData();
                                            Intent i=new Intent(Admin.this,adminloged.class);
                                            i.putExtra("username",n);
                                            startActivity(i);
                                        }
                                        else {
                                            Toast.makeText(Admin.this, "Not an admin", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        }
                        else {
                            Toast.makeText(Admin.this, "Incorrect username or password", Toast.LENGTH_LONG).show();

                        }
                    }
                });



            }
        });

    }
}
