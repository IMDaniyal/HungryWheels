package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class GetFoodListTask extends AsyncTask {


    Context c;
    List<RestaurantTable> alpha;

    GetFoodListTask(Context con,List<RestaurantTable> alpha) {
        c = con;
        this.alpha=alpha;

    }


    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        alpha=myDb.restaurantDao().getAll();

        return null;
    }

}
