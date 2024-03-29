package com.example.hungrywheels;

import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.Serializable;

public class OrderNowScreen extends AppCompatActivity {

    private AdView mAdView;
    int REQUEST_INVITE=10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now_screen);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        TextView item=findViewById(R.id.itemname);
        TextView res=findViewById(R.id.Rname);
        TextView price=findViewById(R.id.pricetag);
        Button b1=findViewById(R.id.order);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        b1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                }
                else {
                    vibrator.vibrate(200);
                }
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

        item.setText(getIntent().getExtras().getString("food"));
        price.setText(getIntent().getExtras().getString("price"));
        res.setText("Dominos");

       /* GetFoodDetails gt=new GetFoodDetails(getBaseContext(),res,getIntent().getExtras().getString("food"));
        gt.execute();*/





    }
}
