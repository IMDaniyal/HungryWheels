package com.example.hungrywheels;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class registration_fragment extends Fragment {

    TextView fullname,userid,password,password2,email,age,phone;
    ImageView userErr,passErr;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    public registration_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.registration_fragment, container, false);
        fullname=v.findViewById(R.id.fullnametxt);
        userid=v.findViewById(R.id.usernametxt);
        password=v.findViewById(R.id.password);
        password2=v.findViewById(R.id.password2);
        email=v.findViewById(R.id.emailtxt);
        age=v.findViewById(R.id.agetxt);
        phone=v.findViewById(R.id.phonetxt);
        userErr=v.findViewById(R.id.usernameerror);
        passErr=v.findViewById(R.id.passworderror);

        Button b=v.findViewById(R.id.Register);
        b.setOnClickListener(new Button.OnClickListener()
                             {
                                 @Override
                                 public void onClick(View view)
                                 {
                                     UserTable u=new UserTable();
                                     u.setAge(age.getText().toString());
                                     u.setName(fullname.getText().toString());
                                     u.setEmail(email.getText().toString());
                                     u.setPassword(password.getText().toString());
                                     u.setPhone(phone.getText().toString());
                                     u.setUsername(userid.getText().toString());

                                     RegisterThread rt=new RegisterThread(getActivity(),password2.getText().toString(),userErr,passErr,u,getFragmentManager());
                                     rt.execute();
                                 }
                             }

        );

        return v;
    }

}
