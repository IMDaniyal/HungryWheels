package com.example.hungrywheels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderDetailScreen extends AppCompatActivity {

    TextView item=findViewById(R.id.oname1);
    TextView res=findViewById(R.id.rname1);
    TextView price=findViewById(R.id.pname1);
    Button b1=findViewById(R.id.back);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_screen);



        b1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                }
            }
        );

    }




}
