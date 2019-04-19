package com.example.hungrywheels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder,new login_fragment()).commit();
    }
}
