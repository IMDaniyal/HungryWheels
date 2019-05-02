package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;

import static com.example.hungrywheels.update_profile.handleSamplingAndRotationBitmap;

public class profileDisplayThread extends AsyncTask {
    Context c;
    TextView name;
    TextView email,phone,username;
    ImageView image;
    String user;
    UserTable u;
    boolean f=false;
    String path;
    boolean picFlag=false;

    public profileDisplayThread(Context c, TextView name, TextView email, TextView phone, TextView username, ImageView image, String user) {
        this.c = c;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.image = image;
        this.user = user;
        u=new UserTable();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        u=myDb.userDao().findByUsername(user);
        if(u!=null)
        {
         f=true;
        }
        if(myDb.userDao().pic(user).equals("1"))
        {
            picFlag=true;
            path=myDb.userDao().picPath(user);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        if (f == true) {
            name.setText(u.getName());
            if(u.getUsername().length()<15) {
                username.setText(u.getUsername());
            }
            email.setText(u.getEmail());
            if(!u.getPhone().equals("-1")) {
                phone.setText(u.getPhone());
            }
            if (picFlag == true) {
                File imgFile = new File(path);
                if (imgFile.exists()) {
                    Bitmap b = null;
                    try {
                        b = handleSamplingAndRotationBitmap(c, Uri.fromFile(imgFile));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    image.setImageBitmap(b);
                }
            }
        }
    }
}
