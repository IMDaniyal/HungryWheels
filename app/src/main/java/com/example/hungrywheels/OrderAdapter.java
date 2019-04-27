package com.example.hungrywheels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<FoodViewHolder> {


    ArrayList<RestaurantTable> data=new ArrayList<>();

    int size;
    Context c;

    public OrderAdapter(Context c){

        this.c=c;


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

        GetFoodListTask foods=new GetFoodListTask(c,data);
        foods.execute();




        if(data!=null) {

            holder.Itemname.setText(data.get(position).getOrdername());

        }

    }

    @Override
    public int getItemCount() {
        GetFoodCountThread p=new GetFoodCountThread(c,size);
        p.execute();
        return size;
    }


}
