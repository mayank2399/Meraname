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

public class sendrec extends AppCompatActivity {
EditText date,hr,dname;
Button sub;
FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendrec);
        Intent i = getIntent();
        final String username=i.getStringExtra("name");
        final String[] doc = new String[1];

        date=(EditText)findViewById(R.id.da);
        hr=(EditText)findViewById(R.id.heart);
        firestore=FirebaseFirestore.getInstance();
        sub=(Button)findViewById(R.id.submit);
        dname=(EditText)findViewById(R.id.dname);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dahr();
            }

            private void dahr() {
                firestore.collection("User").whereEqualTo("username",username)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.getResult().getDocuments().isEmpty()) {
                            Map m = task.getResult().getDocuments().get(0).getData();
                            doc[0] = (String) m.get("name");
                            Toast.makeText(sendrec.this, doc[0], Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(sendrec.this, "Incorrect userneame or password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                String s=date.getText().toString();
                String t=hr.getText().toString();
                String d=dname.getText().toString();
                Map<String, Object> may = new HashMap<>();
                may.put("day", s);
                may.put("hrate", t);
                may.put("doctor",d);
                firestore.collection("heart").document(doc[0]).collection(d).document(s).set(may);
            }
        });
    }
}
