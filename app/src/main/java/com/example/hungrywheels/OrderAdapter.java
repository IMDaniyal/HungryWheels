package com.example.hungrywheels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {


    List<OrderListTable> data;

    int size;
    Context c;

    public OrderAdapter(Context c,List<OrderListTable> og){

        this.c=c;
        this.data=og;

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


        if(data!=null) {

            holder.Itemname.setText(data.get(position).getOrdername());
            holder.quanitiy.setText(data.get(position).getUsername());

        }

    }

    @Override
    public int getItemCount() {


       return data.size();
    }


}
