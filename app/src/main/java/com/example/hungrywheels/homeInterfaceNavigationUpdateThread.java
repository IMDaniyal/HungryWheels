package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import static com.example.hungrywheels.update_profile.handleSamplingAndRotationBitmap;

public class homeInterfaceNavigationUpdateThread extends AsyncTask {

    Context c;
    TextView name;
    TextView email;
    ImageView image;
    String username;
    UserTable u;
    boolean f=false;
    boolean picFlag=false;
    String path;

    public homeInterfaceNavigationUpdateThread(Context c, TextView name, TextView email, ImageView image, String username) {
        this.c = c;
        this.name = name;
        this.email = email;
        this.image = image;
        this.username = username;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        u=myDb.userDao().findByUsername(username);
        if(u!=null)
        {
            f=true;
        }
        if(myDb.userDao().pic(username).equals("1"))
        {
            picFlag=true;
            path=myDb.userDao().picPath(username);
        }


        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (f == true) {
            name.setText(u.getName());
            email.setText(u.getEmail());
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
