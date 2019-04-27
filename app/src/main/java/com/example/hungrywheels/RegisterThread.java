package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterThread extends AsyncTask {
    String password2;
    Context c;
    ImageView usernameError;
    ImageView passwordError, emailerr;
    UserTable user;
    int flag1=0,flag2=0,flag3=0;
    FragmentManager manager;

    public RegisterThread( Context c,String password2, ImageView usernameError, ImageView passwordError,ImageView emailerr, UserTable u,FragmentManager manager) {
        this.password2 = password2;
        this.c = c;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.user = u;
        this.manager=manager;
        this.emailerr=emailerr;
    }


    @Override
    protected Object doInBackground(Object[] objects)
    {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String id = myRef.push().getKey();

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        if(myDb.userDao().countUsers(user.getUsername())>0)
        {
            flag1=1;
        }
        if(myDb.userDao().emailSearch(user.getEmail())>0)
        {
            flag3=1;
        }
        if(!user.getPassword().equals(password2))
        {
            flag2=1;
        }
        if(flag1==0 && flag2==0 && flag3==0)
        {
            myDb.userDao().insertAll(user);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if(flag1==1)
        {
            usernameError.setVisibility(View.VISIBLE);
        }
        else
        {
            usernameError.setVisibility(View.INVISIBLE);
        }
        if(flag2==1)
        {
            passwordError.setVisibility(View.VISIBLE);
        }
        else
        {
            passwordError.setVisibility(View.INVISIBLE);
        }
        if(flag3==1)
        {
            emailerr.setVisibility(View.VISIBLE);
        }
        else
        {
            emailerr.setVisibility(View.INVISIBLE);
        }
        if(flag1==0 && flag2==0 && flag3==0){
            login_fragment bdf = new login_fragment();
            manager.beginTransaction().replace(R.id.placeholder,bdf).commit();

        }

    }
}
