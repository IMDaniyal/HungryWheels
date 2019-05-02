package com.example.hungrywheels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class feedbackActivity extends AppCompatActivity {

    TextView t;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent i =getIntent();
        username=i.getExtras().getString("username");
        Button b= findViewById(R.id.feedbackSend);
        t=findViewById(R.id.feedbackText);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Feedback");
                String id = myRef.push().getKey();
                myRef.child(id).setValue(t.getText().toString());
                myRef.child(id).setValue(username);
                Intent w= new Intent(getApplicationContext(),HomeInterface.class);
                w.putExtra("username",username);
                startActivity(w);

            }
        });
    }
}
