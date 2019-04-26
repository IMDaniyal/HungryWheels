package com.example.hungrywheels;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class login_fragment extends Fragment {

    TextView username;
    TextView password;
    private int req_code = 1111;
    private ImageButton btn_mic;

    public login_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.login_fragment, container, false);
        TextView register = v.findViewById(R.id.registertxt);

        username = v.findViewById(R.id.nametxt);
        password = v.findViewById(R.id.passwordtxt);
        btn_mic = v.findViewById(R.id.imageButton);

        btn_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letstalk();
            }
        });

        Button btn = v.findViewById(R.id.loginbutton);
        btn.setOnClickListener(new Button.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {


                                       LoginThread alpha = new LoginThread(getActivity(), username.getText().toString(), password.getText().toString());
                                       alpha.execute();


                                   }
                               }
        );

        Button btn2 = v.findViewById(R.id.otherlogin);
        btn2.setOnClickListener(new Button.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {


                                        Intent i = new Intent(getActivity(), MainActivity.class);
                                        startActivity(i);

                                    }
                                }
        );


        register.setOnClickListener(new TextView.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {


                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new registration_fragment()).commit();

                                        }
                                    }
        );

        return v;

    }

    private void letstalk() {


        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tell us your Username:");

        try {
            startActivityForResult(intent, req_code);
        } catch (ActivityNotFoundException e) {
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == req_code) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> voice_in_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                username.setText(voice_in_text.get(0));
            }
        }
    }
}