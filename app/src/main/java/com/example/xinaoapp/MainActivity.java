package com.example.xinaoapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 WebView
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // 启用 JavaScript
        webView.setWebViewClient(new WebViewClient()); // 防止跳转到外部浏览器

        // 加载 index.html
        webView.loadUrl("file:///android_asset/index.html");

        // 设置 WebView 的 JavaScript 接口
        webView.addJavascriptInterface(new WebAppInterface(), "Android");
    }

    // JavaScript 接口，用于处理按钮点击
    public class WebAppInterface {
        @android.webkit.JavascriptInterface
        public void loadAppMain() {
            // 加载 app-main.html
            runOnUiThread(() -> webView.loadUrl("file:///android_asset/app-main.html"));
        }
    }

    @Override
    public void onBackPressed() {
        // 如果 WebView 可以返回上一页，则返回上一页
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}