package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class UserOrders extends Fragment implements RecyclerView.OnItemTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GestureDetector gestureDetector;
    RecyclerView orders;
    Context c=getActivity();
    int pos;
    OrderAdapter adapter;

    public UserOrders() {
        // Required empty public constructor
    }


    public static UserOrders newInstance(String param1, String param2) {
        UserOrders fragment = new UserOrders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_orders, container, false);

        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //      Toast.makeText(c,"onSingleTap",Toast.LENGTH_SHORT).show();

                View child = orders.findChildViewUnder(e.getX(),e.getY());

                if(child != null)
                {
                    pos=orders.getChildAdapterPosition(child);

                    Intent z=new Intent(getActivity().getBaseContext(),OrderDetailScreen.class);
                    startActivity(z);


                }
                return true;
            }
        }

        );

        orders=v.findViewById(R.id.OrderRecycler);
        orders.setLayoutManager(new LinearLayoutManager(c));

        orders.addOnItemTouchListener(this);
        orders.setItemAnimator(new DefaultItemAnimator());
        adapter=new OrderAdapter(getActivity());
        orders.setAdapter(adapter);



        return v;
    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
