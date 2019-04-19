package com.example.hungrywheels;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class login_fragment extends Fragment {

    TextView username;
    TextView password;


    public login_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.login_fragment, container, false);
        TextView register = v.findViewById(R.id.registertxt);

        username= v.findViewById(R.id.nametxt);
        password=v.findViewById(R.id.passwordtxt);

        Button btn = v.findViewById(R.id.loginbutton);
        btn.setOnClickListener(new Button.OnClickListener() {
                                   @Override
                                   public void onClick(View view)
                                   {


                                       LoginThread alpha=new LoginThread(getActivity(),username.getText().toString(),password.getText().toString());
                                       alpha.execute();


                                   }
                               }
        );


        register.setOnClickListener(new TextView.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View view)
                                        {


                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.placeholder,new registration_fragment()).commit();

                                        }
                                    }
        );

        return v;

    }

}
