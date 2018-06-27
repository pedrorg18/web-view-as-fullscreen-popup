package com.example.proiggimenez.fullscreenwebviewtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button buttonShowWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonShowWebView = findViewById(R.id.buttonShowWebView);
        buttonShowWebView.setOnClickListener(v -> openFullScreenWebView(MainActivity.this, R.string.web_view_title,
                "https://www.google.es"));

    }

    @SuppressWarnings("SameParameterValue")
    @SuppressLint("SetJavaScriptEnabled")
    private void openFullScreenWebView(Activity act, int titleTextResId, String urlWebView) {
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = Objects.requireNonNull(inflater).inflate(R.layout.help_webview_popup_layout,
                act.findViewById(R.id.tutorial_popup_parent));

        final PopupWindow popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSplitTouchEnabled(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final ImageView closePopupBtn = popupWindow.getContentView().findViewById(R.id.btn_close_popup);
        WebView tutoContent = popupWindow.getContentView().findViewById(R.id.tutorial_webview);
        TextView textViewTitle = popupWindow.getContentView().findViewById(R.id.title_web_view);
        textViewTitle.setText(titleTextResId);
        tutoContent.getSettings().setJavaScriptEnabled(true);
        tutoContent.getSettings().setDomStorageEnabled(true);

        tutoContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        tutoContent.loadUrl(urlWebView);

        closePopupBtn.setOnClickListener(v -> popupWindow.dismiss());
    }

}
