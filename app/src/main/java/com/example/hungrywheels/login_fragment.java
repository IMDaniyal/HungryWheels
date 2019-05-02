package com.example.hungrywheels;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class login_fragment extends Fragment {

    TextView username;
    TextView password;
    boolean flag=false;
    private int req_code = 1111;
    private TextView forget_pass;
    private ImageButton btn_mic;
    Intent i;
    String Phone;

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

        forget_pass=v.findViewById(R.id.textView14);

       /* forget_pass.setOnClickListener(new TextView.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               FirebaseDatabase database = FirebaseDatabase.getInstance();
                                               DatabaseReference myRef = database.getReference("users");
                                                // Toast.makeText(getApplicationContext(),"lala", Toast.LENGTH_SHORT).show();
                                               myRef.orderByChild("username").equalTo(username.getText().toString()).addChildEventListener(new ChildEventListener() {
                                                   @Override
                                                   public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                       UserTable u=dataSnapshot.getValue(UserTable.class);
                                                       Toast.makeText(getApplicationContext(),u.getUsername(), Toast.LENGTH_SHORT).show();
                                                       if(u.getUsername().isEmpty())
                                                       {
                                                           Toast.makeText(getApplicationContext(),"Enter Username First", Toast.LENGTH_SHORT).show();
                                                       }

                                                       else if(u.getUsername().equals(username.getText().toString()))
                                                       {

                                                           Phone= u.getPhone();
                                                           if(Phone.length()<10)
                                                           {
                                                               Toast.makeText(getApplicationContext(),"Phone Number does not exist!", Toast.LENGTH_SHORT).show();
                                                           }
                                                           else
                                                           {

                                                               Intent i= new Intent(getActivity(), pass_forget.class);
                                                               i.putExtra("username",u.getUsername());
                                                               i.putExtra("phone",u.getPhone());
                                                               startActivity(i);
                                                               //Toast.makeText(getApplicationContext(),"HAHAHAHHA NOOB!", Toast.LENGTH_SHORT).show();
                                                           }
//

                                                       }
                                                       else
                                                       {
                                                           Toast.makeText(getApplicationContext(),"Wrong Username!", Toast.LENGTH_SHORT).show();
                                                       }

                                                   }

                                                   @Override
                                                   public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                   }

                                                   @Override
                                                   public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                                   }

                                                   @Override
                                                   public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                   }

                                                   @Override
                                                   public void onCancelled(@NonNull DatabaseError databaseError) {

                                                   }
                                               });
//                                               if(flag==false) {
//                                                   LoginThread alpha = new LoginThread(getActivity(), username.getText().toString(), password.getText().toString(),forget_pass);
//                                                   alpha.execute();
                                               //}

                                           }
                                       }
        );*/



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

                                       FirebaseDatabase database = FirebaseDatabase.getInstance();
                                       DatabaseReference myRef = database.getReference("users");
                                       myRef.orderByChild("username").equalTo(username.getText().toString()).addChildEventListener(new ChildEventListener() {
                                           @Override
                                           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                               UserTable u=dataSnapshot.getValue(UserTable.class);
                                               if(u.getPassword().equals(password.getText().toString()))
                                               {
                                                   Intent i= new Intent(getActivity(),HomeInterface.class);
                                                   i.putExtra("username",u.getUsername());
                                                   i.putExtra("email",u.getEmail());
                                                   startActivity(i);

                                               }
                                           }

                                           @Override
                                           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                           }

                                           @Override
                                           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                           }

                                           @Override
                                           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });
                                       if(flag==false) {
                                           LoginThread alpha = new LoginThread(getActivity(), username.getText().toString(), password.getText().toString(),forget_pass);
                                           alpha.execute();
                                       }

                                   }
                               }
        );
        Button btn3 = v.findViewById(R.id.restaddbutton);
        btn3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new restaurant_fragment()).commit();
            }
        });

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


    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode,resultCode,data);
//        if(resultCode==RESULT_OK && requestCode==1122)
//        {
////            name.setText(data.getStringExtra("key"));
//        }
//        i=data;
//    }
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