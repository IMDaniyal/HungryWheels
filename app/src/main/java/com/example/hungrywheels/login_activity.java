package com.example.hungrywheels;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;

public class login_activity extends AppCompatActivity {

    private TextToSpeech My_Siri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView logo=findViewById(R.id.logoimg);
        logo.animate().rotation(180f).translationXBy(2000f).setDuration(2000);
        FrameLayout ph=findViewById(R.id.placeholder);
        ph.animate().alpha(1f).setDuration(5000);


        My_Siri= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {

                    int res= My_Siri.setLanguage(Locale.ENGLISH);
                    if(res==TextToSpeech.LANG_MISSING_DATA || res==TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(getApplicationContext(),"Language Not Supported", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        My_Siri.speak("Welcome to Hungry Wheels", TextToSpeech.QUEUE_FLUSH, null);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Initialization Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder,new login_fragment()).commit();

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot user : dataSnapshot.getChildren())
                {
                    UserTable u=user.getValue(UserTable.class);
                    new firebasetoRoomThread(getApplicationContext(),u).execute();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), " Not added",
                        Toast.LENGTH_SHORT).show();
            }
        });
        DatabaseReference myRestref = database.getReference("restaurants");
        myRestref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot restaurant : dataSnapshot.getChildren())
                {
                    RestaurantTable r=restaurant.getValue(RestaurantTable.class);
                    new firebasetoRoomThreadforRestaurant(getApplicationContext(),r).execute();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), " Not added",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
