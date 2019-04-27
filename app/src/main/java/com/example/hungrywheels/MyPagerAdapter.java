package com.example.hungrywheels;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

            case 0: return ListRestaurants.newInstance("Restaurants", "Restaurants");
            case 1: return UserOrders.newInstance("My Orders", "My Orders");
            default: return ListRestaurants.newInstance("Restaurants", "Restaurants");
        }
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        if(position==0){

            return "Restaurants";

        }
        else{

            return "My Orders";

        }

    }
}
