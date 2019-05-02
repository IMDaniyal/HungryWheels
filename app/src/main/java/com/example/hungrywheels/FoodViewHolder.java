package com.example.hungrywheels;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    TextView Itemname;
    TextView rating;
    TextView price;


    public FoodViewHolder(View itemView) {
        super(itemView);
        this.Itemname=itemView.findViewById(R.id.Foodname);
        this.rating=itemView.findViewById(R.id.qunatity);
        this.price=itemView.findViewById(R.id.foodprice);

    }


}
