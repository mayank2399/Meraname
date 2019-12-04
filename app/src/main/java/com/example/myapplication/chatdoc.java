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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class chatdoc extends AppCompatActivity {
    EditText dname,chatboxuser,chatdoc;
    Button send,getchat;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatdoc);
        //
        Intent i = getIntent();
        final String username=i.getStringExtra("name");
        dname = (EditText) findViewById(R.id.dname);
        chatboxuser = (EditText) findViewById(R.id.cahtboxuser);
        firestore = FirebaseFirestore.getInstance();
        getchat = (Button) findViewById(R.id.getchat);
        send = (Button) findViewById(R.id.send);
        chatdoc=(EditText)findViewById(R.id.chatdoc);
        final String[] doc = new String[1];
        firestore.collection("Doctor").whereEqualTo("username",username)
                .whereEqualTo("type","confirm")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(!task.getResult().getDocuments().isEmpty()) {
                    Map m = task.getResult().getDocuments().get(0).getData();
                    doc[0] = (String) m.get("name");
                    Toast.makeText(chatdoc.this, doc[0], Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(chatdoc.this, "Incorrect userneame or password", Toast.LENGTH_LONG).show();
                }
            }
        });
        getchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat();
            }

            private void chat() {
                //String d=dname.getText().toString();
                String pat=dname.getText().toString();
                System.out.println("dsjkfhdsajkfhsdjkfhsdkfjhsdjkfhdsfkjdsfjksdahfkshfsdakjhfhskdjfhadsjkfhskdfhsjkdfhdskfhsdkfhsdkjfh                                                   ");
                firestore.collection("Chat").document(doc[0]).collection(pat).whereEqualTo("to",pat)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.getResult().getDocuments().isEmpty()) {
                            Map m = task.getResult().getDocuments().get(0).getData();
                            String p = (String) m.get("chat");
                            System.out.println("jklfsdjflkjsdfljsdlfjdslf");
                            chatdoc.setText(p);
                        }

                    }
                });
            }

        });







        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=chatboxuser.getText().toString();
                String pat=dname.getText().toString();
                Map<String,Object> m=new HashMap<>();
                m.put("to",pat);
                m.put("chat",s);
                firestore.collection("Chat").document(doc[0]).collection(pat).document(pat).set(m);
                Toast.makeText(chatdoc.this,"send",Toast.LENGTH_LONG).show();
            }
        });

    }

}