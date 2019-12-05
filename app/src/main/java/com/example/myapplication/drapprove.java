package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class drapprove extends AppCompatActivity {
Button  veri;
FirebaseFirestore firestore;
TextView tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drapprove);
        tf = (TextView) findViewById(R.id.tf);
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Doctor").whereEqualTo("type","new").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (!task.getResult().getDocuments().isEmpty()) {
                    Map m = task.getResult().getDocuments().get(0).getData();
                    final String s, t, u, v, w;
                    s = (String) m.get("name");
                    //t = (String) m.get("password");
                    u = (String) m.get("speciality");
                    v = (String) m.get("username");
                    w = (String) m.get("disease");
                    tf.setText("Name:-"+s+"\nSpeciality-"+u+"\n"+"Username"+v+"\n"+"Disease"+w);
                    veri=(Button)findViewById(R.id.veri);
                    veri.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String, Object> may = new HashMap<>();
                            may.put("name", s);
                            may.put("speciality", u);
                            //may.put("address", u);
                            may.put("username", v);
                            may.put("disease", w);
                            may.put("type", "confirm");
                            firestore.collection("lab").document(s).set(may);

                        }
                    });

                }
                else
                { tf.setText("No data");

                }
            }


        });




    }
}
