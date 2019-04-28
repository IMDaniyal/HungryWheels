package com.example.hungrywheels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {


    ArrayList<RestaurantTable> data=new ArrayList<>();

    int size;
    Context c;

    public OrderAdapter(Context c){

        this.c=c;


    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.orderview,parent,false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        /*GetFoodListTask foods=new GetFoodListTask(c,data);
        foods.execute();*/




        if(data!=null) {

            holder.Itemname.setText("");

        }

    }

    @Override
    public int getItemCount() {
        /*GetFoodCountThread p=new GetFoodCountThread(c,size);
        p.execute();*/
        return 10;
    }


}
