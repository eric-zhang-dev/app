package com.joker.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.joker.R;
import com.joker.http.HttpGet;

/**
 * Created by zhangyue on 2016/5/14.
 */
public class One extends Fragment {
    private WebView webView;
    private String result;
    private String startNum;
    private String endNum;
    private View view;
    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        webView = (WebView) view.findViewById(R.id.my_web);
        webView.loadUrl("file:///android_asset/www/joker.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(this, "Android");
    }
    @android.webkit.JavascriptInterface
    public void loadData(final String startNum, final String endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
        new Thread(load).start();
    }

    private Runnable load = new Runnable() {
        @Override
        public void run() {
            result = HttpGet.httpGet("http://japi.juhe.cn/joke/content/text.from?key=0387ce49ebf86da430611dc3e7c1a668&page="+startNum+"&pagesize="+endNum);
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
