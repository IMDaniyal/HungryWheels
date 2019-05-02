package com.example.hungrywheels;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

public class postOnFacebook extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    Button shareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_on_facebook);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
        shareButton = findViewById(R.id.fb_share_button);
        callbackManager=CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("It is helpful !")
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();
                if (ShareDialog.canShow(ShareLinkContent.class)) {

                    shareDialog.show(linkContent);
                }
            }
        });

    }

}
