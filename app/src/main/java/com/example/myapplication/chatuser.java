package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.Edits;
import android.os.Bundle;
import android.text.Editable;
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

public class chatuser extends AppCompatActivity {
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
        firestore.collection("User").whereEqualTo("username",username)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(!task.getResult().getDocuments().isEmpty()) {
                    Map m = task.getResult().getDocuments().get(0).getData();
                    doc[0] = (String) m.get("name");
                    Toast.makeText(chatuser.this, doc[0], Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(chatuser.this, "Incorrect userneame or password", Toast.LENGTH_LONG).show();
                }
            }
        });
        final String[] p = new String[1];
        getchat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                chat();
            }

            private void chat() {
                //String d=dname.getText().toString();
                String pat=dname.getText().toString();
                System.out.println("dsjkfhdsajkfhsdjkfhsdkfjhsdjkfhdsfkjdsfjksdahfkshfsdakjhfhskdjfhadsjkfhskdfhsjkdfhdskfhsdkfhsdkjfh                                                   ");
                firestore.collection("Chat").document(pat).collection(doc[0]).whereEqualTo("to",doc[0])
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.getResult().getDocuments().isEmpty()) {
                            Map m = task.getResult().getDocuments().get(0).getData();
                            p[0] = (String) m.get("chat");
                            System.out.println("jklfsdjflkjsdfljsdlfjdslf");
                            chatdoc.setText(p[0]);
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
                m.put("to",doc[0]);
                m.put("chat",p[0]+"\n"+s);
                chatboxuser.setText("");
                firestore.collection("Chat").document(pat).collection(doc[0]).document(doc[0]).set(m);
                Toast.makeText(chatuser.this,"send",Toast.LENGTH_LONG).show();
            }
        });

    }

}