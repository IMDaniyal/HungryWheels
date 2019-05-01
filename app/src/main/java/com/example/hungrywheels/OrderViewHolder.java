package com.example.hungrywheels;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class OrderViewHolder extends RecyclerView.ViewHolder {


    TextView Itemname;
    TextView quanitiy;


    public OrderViewHolder(View itemView) {
        super(itemView);
        this.Itemname=itemView.findViewById(R.id.itemordered);
        this.quanitiy=itemView.findViewById(R.id.textView15);

    }

}
