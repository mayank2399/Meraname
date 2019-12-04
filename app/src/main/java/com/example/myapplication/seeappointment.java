package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class seeappointment extends AppCompatActivity {
    FirebaseFirestore firestore;
    EditText pname;
    Button chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //
        Intent i = getIntent();
        final String n = i.getStringExtra("name");
        setContentView(R.layout.activity_seeappointment);
        pname = (EditText) findViewById(R.id.USERNAME);
        chat = (Button) findViewById(R.id.chat);
        firestore = FirebaseFirestore.getInstance();
        System.out.println("hdskjfhdskjfhdskjfhsdkjfhdskjfhdskjfhdskfhsdkfhdskfhdskjfhdskjfhdskjf                        "+n);
        final String[] s = {""};
        final String name=cha(n);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="Hello know you may chat with me";
                String d=pname.getText().toString();
                Map<String,Object> senduser=new HashMap<>();
                senduser.put("user",s+"\n"+n+":"+s);
                senduser.put("to",d);
                firestore.collection("chat").document(d).collection(n).document(n).set(senduser);
                //chatdoc.setText(d+"\n"+n+":"+s);
            }
        });

    }

    private String cha(String n) {
        final String[] s = {""};
        firestore.collection("Doctor").whereEqualTo("username", n).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (!task.getResult().getDocuments().isEmpty()) {
                    Map m = task.getResult().getDocuments().get(0).getData();
                    s[0] = (String) m.get("speciality");
                    System.out.println("fkjdkshfkjsdhfjksdhkfhdsfhsdjfhsdjkfkjdfhkj          "+s[0]);
                    firestore.collection("Appoint").whereEqualTo("disease", s[0]).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            Map m = task.getResult().getDocuments().get(0).getData();
                            s[0] = (String) m.get("name");
                            System.out.println("hdskjfhdskjfhdskjfhsdkjfhdskjfhdskjfhdskfhsdkfhdskfhdskjfhdskjfhdskjf                        "+s);
                            pname.setText(s[0]);
                        }
                    });
                }
                else
                { System.out.println("not      ");

                }
            }



        });
        System.out.println("kfdjkldsfjksdljfsdlkfjldsjfldsjfldsfjdslkfjdslfjsdlkfjsdklfjsdklafjsdlkf");
        return  s[0];

    }
}





