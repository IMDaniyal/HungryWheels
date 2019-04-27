package com.example.hungrywheels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderNowScreen extends AppCompatActivity {

    TextView item=findViewById(R.id.itemname);
    TextView res=findViewById(R.id.Rname);
    TextView price=findViewById(R.id.pricetag);
    Button b1=findViewById(R.id.order);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now_screen);

        b1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }
        );



    }
}
