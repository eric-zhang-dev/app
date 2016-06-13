package com.joker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.joker.R;
import com.joker.html5.Html5;
import com.joker.http.HttpGet;

/**
 * Created by zhangyue on 2016/5/27.
 */
public class UrlActivity extends AppCompatActivity {
    private WebView webView;
    private String con, result, httpUrl;
    private String type = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        initView();
    }

    @JavascriptInterface
    private void initView() {
        con = getIntent().getStringExtra("content");
        type = getIntent().getStringExtra("type");
        webView = (WebView) findViewById(R.id.my_web);
        if (type.equals("0")) {
            webView.loadUrl(Html5.SEARCH_NEWS + "?type=0&p=" + con);
        } else if (type.equals("1")) {
            webView.loadUrl(Html5.TIC_NUM_HTML + "?type=0&p=" + con);
        } else if (type.equals("2")) {
            webView.loadUrl(Html5.DREAM_NUM_HTML);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        //图片显示
        webView.getSettings().setLoadsImagesAutomatically(true);
        //自适应屏幕
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(this, "Android");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
    }

    @JavascriptInterface
    public void detial(String url) {
        startActivity(new Intent(UrlActivity.this, DActivity.class).putExtra("url", url));
    }

    @JavascriptInterface
    public void load() {
        result = HttpGet.httpGet("http://v.juhe.cn/dream/queryid?key=07550e95d2556c044825d0f3035fd433&id=" + con);
        handler.obtainMessage(1).sendToTarget();
    }

    @JavascriptInterface
    public void hot() {
        httpUrl = "http://op.juhe.cn/onebox/news/query?key=a847c7e85ab750d5487a7426087cac10&q=" + con;
        new Thread(load).start();
    }

    private Runnable load = new Runnable() {
        @Override
        public void run() {
            result = HttpGet.httpGet(httpUrl);
            handler.obtainMessage(1).sendToTarget();
        }
    };
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    webView.post(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadUrl("javascript:MyRefresh(" + result + ");");
                        }
                    });
                    break;
            }
        }
    };
}
