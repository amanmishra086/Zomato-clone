package com.example.zomato;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RestaurantDetails extends AppCompatActivity {
    WebView web;
    AlertDialog.Builder alert;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Toast.makeText(this, "Loading.....", Toast.LENGTH_SHORT).show();
        //loading = ProgressDialog.show(this, "Please wait...", "Loading...", false, false);
        alert = new AlertDialog.Builder(this);

        Intent intent=getIntent();
        String url=intent.getExtras().getString("url");

        web = findViewById(R.id.web);
        web.loadUrl(url);
        //loading.dismiss();
        WebSettings webSettings = web.getSettings();
        webSettings.setAllowContentAccess(true);

        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());

//        // demo1TrySpecificURL();
//        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//
//            }
//        });
//        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//    }
    }
    }

