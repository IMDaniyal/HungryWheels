package com.example.hungrywheels;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;

public class login_activity extends AppCompatActivity {

    private TextToSpeech My_Siri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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

}
