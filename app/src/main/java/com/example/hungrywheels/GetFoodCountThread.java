package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;

public class GetFoodCountThread extends AsyncTask {


    Context c;
    int food;

    GetFoodCountThread(Context con,int food) {
        c = con;
        this.food=food;

    }


    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        food=myDb.restaurantDao().countfood();

        return null;
    }



}
