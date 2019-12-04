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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class forget_doc extends AppCompatActivity {
EditText email;
Button sendmail;
FirebaseFirestore firestore;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_doc);
        email=(EditText)findViewById(R.id.email);
        sendmail=(Button)findViewById(R.id.sendmail);
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();


        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget();
            }

            private void forget() {
                final String mail=email.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            firestore.collection("Doctor").whereEqualTo("username",mail)
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(!task.getResult().getDocuments().isEmpty()) {
                                        Map m = task.getResult().getDocuments().get(0).getData();
                                        Toast.makeText(forget_doc.this, "Password reset link is send", Toast.LENGTH_LONG).show();
                                        Intent i=new Intent(forget_doc.this,log_doc.class);
                                        startActivity(i);
                                    }
                                    else {
                                        Toast.makeText(forget_doc.this,"Please enter valid email id",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }
                        else
                            Toast.makeText(forget_doc.this,"Please enter valid email id",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
