package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Sign_User extends AppCompatActivity {
EditText name,user,pass,conpass;
Button signup,back;
FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__user);
        firestore=FirebaseFirestore.getInstance();
        name=(EditText)findViewById(R.id.USERNAME);
        user=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.pass);
        conpass=(EditText)findViewById(R.id.conpass);
        signup=(Button)findViewById(R.id.sign_user);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }

            private void signup() {
                String n=name.getText().toString();
                String p=pass.getText().toString();
                String c=conpass.getText().toString();
                String u=user.getText().toString();
                Map<String, Object> map = new HashMap<>();
                if(p.equals(c)) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Name", n);
                    user.put("Password", p);
                    user.put("Username", u);
                    firestore.collection("User").document(n).set(user);
                    Toast.makeText(Sign_User.this,"User created",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Sign_User.this,log_user.class);
                    startActivity(i);
                }
                else
                {Toast.makeText(Sign_User.this,"please enter same password in conforim password box",Toast.LENGTH_LONG).show();

                }


            }

        });



    }
}
