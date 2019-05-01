package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;

public class GetFoodCountThread extends AsyncTask {


    Context c;
    int food;
    int a;

    GetFoodCountThread(Context con,int food) {
        c = con;
        this.food=food;

    }


    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
       a= myDb.restaurantDao().countfood();

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        food=a;

    }
}
