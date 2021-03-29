package com.example.ct60a2411_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    WebView myWeb;
    EditText textViewAddressURL;
    TextView inputTextView; // For testing purposes, to see what's going on

    String addressString = "";
    WebPage wp = new WebPage();
    WebPage prevWp = new WebPage();
    WebPage nextWp = new WebPage();
    Boolean javaScriptStart = true;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        System.out.println("######## Starting... ########");
        inputTextView = (TextView) findViewById(R.id.textViewAddress); // For testing purposes, to see what's going on

        textViewAddressURL = (EditText) findViewById(R.id.editTextTextMultiLine);
        myWeb = findViewById(R.id.webView);
        myWeb.setWebViewClient(new WebViewClient());

        myWeb.getSettings().setJavaScriptEnabled(true); // JavaScript usage E10.3

        // Address field management
        textViewAddressURL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addressString = textViewAddressURL.getText().toString();
                if (addressString.contains("\n")) {
                    loadPage(s.toString());
               }
            }
        });

    }

    // Main page load

    public void loadPage(String url) {
        String preURL = "http://";
        String preSURL = "https://";

        if (!wp.getPageUrl().equals(""))
            prevWp.setPageUrl(wp.getPageUrl()); //Store previous page

        if (url.contains(preURL) || url.contains(preSURL)) {
            wp.setPageUrl(url);
            myWeb.loadUrl(url);
        }
        else {
            String fullUrl = preURL + url;
            myWeb.loadUrl(fullUrl);
            wp.setPageUrl(fullUrl);
        }
        textViewAddressURL.setText("");

        /* Just for checking what's inside previous object
        if (!prevWp.getPageUrl().equals(""))
            inputTextView.setText(prevWp.getPageUrl());
         */
    }

    // Refresh functionality E10.2

    public void refreshURL(View v){
        System.out.println("Refresh webpage pushed");
        myWeb.loadUrl(wp.getPageUrl()); // Reload current
    }


    // JavaScript usage E10.3 - START

    public void initializeJS(View v) {
        if (javaScriptStart) {
            myWeb.loadUrl("file:///android_asset/index.html");
            javaScriptStart = false;
        }
        else
            myWeb.evaluateJavascript("javascript:initialize()",null);
        System.out.println("Initialize JS pushed");
    }

    public void executeJS(View v) {
        myWeb.evaluateJavascript("javascript:shoutOut()",null);
        System.out.println("Run JS pushed");
    }

    // JavaScript usage E10.3 - END

    // Previous and next page management for one step forward or backwards - E10.4

    public void loadPreviousPage(View v) {
        nextWp.setPageUrl(wp.getPageUrl()); // Current saved to next
        //inputTextView.setText(prevWp.getPageUrl());
        if (!prevWp.getPageUrl().equals("")) {
            wp.setPageUrl(prevWp.getPageUrl()); // Current updated
            myWeb.loadUrl(prevWp.getPageUrl());
        }
    }

    public void loadNextPage(View v) {
        prevWp.setPageUrl(wp.getPageUrl()); // Current saved to previous
        //inputTextView.setText(nextWp.getPageUrl());
        if (!nextWp.getPageUrl().equals("")) {
            wp.setPageUrl(nextWp.getPageUrl()); // Current updated
            myWeb.loadUrl(nextWp.getPageUrl());
        }
    }

    // Previous and next page management for one step forward or backwards E10.4 - END

}