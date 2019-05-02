package com.example.hungrywheels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    TextView name;
    TextView email,phone,username;
    ImageView image;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_name);
        username = findViewById(R.id.profile_username);
        email = findViewById(R.id.profile_email);
        phone = findViewById(R.id.profile_phone);
        image=findViewById(R.id.profile_image);

        Intent i= getIntent();
        user=i.getExtras().getString("username");
        new profileDisplayThread(getApplicationContext(),name,email,phone,username,image,user).execute();

    }
    public void backkbutton(View view)
    {
        Intent w= new Intent(getApplicationContext(),HomeInterface.class);
        w.putExtra("username",user);
        startActivity(w);
    }
}
