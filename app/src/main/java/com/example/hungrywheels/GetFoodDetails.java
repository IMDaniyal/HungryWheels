package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class GetFoodDetails extends AsyncTask {

    Context c;
    RestaurantTable alpha;
    TextView res;
    String ordername;

    GetFoodDetails(Context con, TextView res,String ordername) {
        c = con;
        this.res=res;
        this.ordername=ordername;

    }


    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        alpha=myDb.restaurantDao().findByOrdername(ordername);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(alpha!=null){



            res.setText(alpha.getRestaurantname());
        }

    }
}
