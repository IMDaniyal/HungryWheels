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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListRestaurants extends Fragment implements RecyclerView.OnItemTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GestureDetector gestureDetector;
    RecyclerView restaurants;
    Context c;
    int pos;
    FoodAdapter adapter;
    List<RestaurantTable> data;

    public ListRestaurants() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListRestaurants.
     */
    // TODO: Rename and change types and number of parameters
    public static ListRestaurants newInstance(String param1, String param2) {
        ListRestaurants fragment = new ListRestaurants();
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
       View v=inflater.inflate(R.layout.fragment_list_restaurants, container, false);

        c=getActivity();
        restaurants=v.findViewById(R.id.FoodRecycler);

        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //      Toast.makeText(c,"onSingleTap",Toast.LENGTH_SHORT).show();

                View child = restaurants.findChildViewUnder(e.getX(),e.getY());

                if(child != null)
                {
                    pos=restaurants.getChildAdapterPosition(child);

                    RecyclerView.ViewHolder viewHolder = restaurants.findViewHolderForAdapterPosition(pos);
                    View view = viewHolder.itemView;
                    TextView food1 =view.findViewById(R.id.Foodname);
                    TextView price1=view.findViewById(R.id.foodprice);
                    String food=food1.getText().toString();
                    String price=price1.getText().toString();


                    Intent i = new Intent(getActivity(), OrderNowScreen.class);
                    i.putExtras(getActivity().getIntent().getExtras());
                    i.putExtra("food",food );
                    i.putExtra("price",price);
                    getActivity().startActivity(i);


                }
                return true;
            }
        }

        );


        GetFoodListTask ab=new GetFoodListTask(getActivity(),restaurants,this);
        ab.execute();

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
