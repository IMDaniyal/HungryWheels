package com.example.hungrywheels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class OrderNowScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now_screen);

        TextView item=findViewById(R.id.itemname);
        TextView res=findViewById(R.id.Rname);
        TextView price=findViewById(R.id.pricetag);
        Button b1=findViewById(R.id.order);

        b1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(), MapsActivity.class);
                i.putExtras(getIntent().getExtras());

                /*UserTable user1=(Serializable)getIntent().getExtras().get("user");
                RestaurantTable food=(Serializable)getIntent().getExtras().get("food");*/
                String user1=(String)getIntent().getExtras().get("username");
                String food=(String)getIntent().getExtras().get("food");

                FillOrderThread y=new FillOrderThread(getBaseContext(),user1,food,i);
                y.execute();

                /*startActivity(i);*/
            }
        }
        );



    }
}
