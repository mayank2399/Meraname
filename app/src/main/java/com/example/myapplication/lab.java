package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class lab extends AppCompatActivity {
EditText et;
FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);
         et = (EditText) findViewById(R.id.et);
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("lab").whereEqualTo("lab", "T").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (!task.getResult().getDocuments().isEmpty()) {
                    Map m = task.getResult().getDocuments().get(0).getData();
                    String s, t, u, v, w;
                    s = (String) m.get("name");
                    t = (String) m.get("age");
                    u = (String) m.get("address");
                    v = (String) m.get("contact");
                    w = (String) m.get("disease");
                    et.setText(s);
                    Map<String, Object> may = new HashMap<>();
                    may.put("name", s);
                    may.put("age", t);
                    may.put("address", u);
                    may.put("contact", v);
                    may.put("disease", w);
                    may.put("lab", "F");
                    firestore.collection("lab").document(s).set(may);

                }
            }


        });




    }
}
