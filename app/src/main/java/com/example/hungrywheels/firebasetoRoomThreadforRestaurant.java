package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class firebasetoRoomThreadforRestaurant extends AsyncTask {
    Context c;
    RestaurantTable r;

    public firebasetoRoomThreadforRestaurant(Context c, RestaurantTable r) {
        this.c = c;
        this.r = r;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        if (myDb.restaurantDao().countordersofRestaurant(r.getRestaurantname())>0)
        {
            myDb.restaurantDao().delete(r);
            myDb.restaurantDao().insertAll(r);
        }
        else
        {
            myDb.restaurantDao().insertAll(r);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }
}

