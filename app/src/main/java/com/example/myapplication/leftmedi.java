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

import java.util.Map;

public class leftmedi extends AppCompatActivity {
EditText medi;
Button search;
FirebaseFirestore firestore;
EditText tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leftmedi);
        firestore=FirebaseFirestore.getInstance();
        medi=(EditText)findViewById(R.id.mediname);
        search=(Button)findViewById(R.id.ser);
        tf=(EditText)findViewById(R.id.result);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medi();
            }

            private void medi() {
                String n=medi.getText().toString();
                firestore.collection("Return").whereEqualTo("name", n).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.getResult().getDocuments().isEmpty())
                        { Map m = task.getResult().getDocuments().get(0).getData();
                            String s = (String) m.get("name");
                            s=s+(String) m.get("quan");
                            s=s+(String)m.get("exp");
                            s=s+(String)m.get("mfg");
                            tf.setText(s);



                        }
                        else
                        { String s="NO medicine fine";
                          tf.setText(s);
                        }
                    }
                });
            }
        });
    }
}
