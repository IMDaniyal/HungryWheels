package com.example.hungrywheels;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class restaurant_fragment extends Fragment {
    TextView Restaurant,price,rating,review,foodname;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    public restaurant_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_restaurant, container, false);
        Restaurant=v.findViewById(R.id.restaurantnametxt);
        price=v.findViewById(R.id.pricetxt);
        review=v.findViewById(R.id.reviewtxt);
        rating=v.findViewById(R.id.ratingtxt);
        foodname=v.findViewById(R.id.foodnametxt);

        Button b=v.findViewById(R.id.restaurantAdd);
        b.setOnClickListener(new Button.OnClickListener()
                             {
                                 @Override
                                 public void onClick(View view)
                                 {
                                     RestaurantTable u=new RestaurantTable();
                                     u.setPrice(price.getText().toString());
                                     u.setRestaurantname(Restaurant.getText().toString());
                                     u.setRating(rating.getText().toString());
                                     u.setOrdername(foodname.getText().toString());
                                     u.setReview(review.getText().toString());

                                     restaurantThread rt=new restaurantThread(u,getActivity(),getFragmentManager());
                                     rt.execute();
                                 }
                             }

        );

        return v;
    }

}
