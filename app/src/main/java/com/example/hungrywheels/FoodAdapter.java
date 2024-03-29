package com.example.hungrywheels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {


    ArrayList<RestaurantTable> data=new ArrayList<>();

    int size;
    Context c;
    List<RestaurantTable> alpha;

    public FoodAdapter(Context c,List<RestaurantTable> data){

    this.c=c;
    this.alpha=data;

    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.foodview,parent,false);

        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        if(alpha!=null) {

            holder.Itemname.setText("     "+alpha.get(position).getOrdername());
            holder.rating.setText(alpha.get(position).getRating());
            holder.price.setText(alpha.get(position).getPrice());
        }

    }

    @Override
    public int getItemCount() {
        return  alpha.size();
    }


}
