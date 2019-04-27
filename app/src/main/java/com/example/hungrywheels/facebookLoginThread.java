package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class facebookLoginThread extends AsyncTask {
    UserTable u;
    Context c;

    public facebookLoginThread( Context c,UserTable u) {
        this.u = u;
        this.c = c;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Intent i= new Intent(c, exampleActivity.class);
        i.putExtra("username",u.getUsername());
        i.putExtra("email",u.getEmail());
        c.startActivity(i);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String id = myRef.push().getKey();
        if(myDb.userDao().facebookSearch(u.getEmail(),u.getFacebook())>0)
        {

        }
        else if(myDb.userDao().emailSearch(u.getEmail())>0)
        {
            myRef.child(u.getUsername()).setValue(u);
            myDb.userDao().updateFB(u.getEmail(),"1");
        }
        else{
            myDb.userDao().insertAll(u);
            myRef.child(id).setValue(u);
        }


        return null;
    }
}
