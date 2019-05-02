package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GetFoodListTask extends AsyncTask {


    Context c;
    List<RestaurantTable> alpha;
    RecyclerView beta;
    ListRestaurants ob;
    GetFoodListTask(Context con,RecyclerView alpha1,ListRestaurants ob) {
        c = con;
        this.beta=alpha1;
        this.ob=ob;

    }


    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        alpha=myDb.restaurantDao().getAll();

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(alpha!=null){



            beta.setLayoutManager(new LinearLayoutManager(c));

            beta.addOnItemTouchListener(ob);
            beta.setItemAnimator(new DefaultItemAnimator());
            FoodAdapter adapter=new FoodAdapter(c,alpha);
            beta.setAdapter(adapter);
        }

    }
}
