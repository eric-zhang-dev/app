package com.joker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.joker.R;
import com.joker.activity.DActivity;
import com.joker.html5.Html5;
import com.joker.http.HttpGet;

/**
 * Created by zhangyue on 2016/5/27.
 */
public class SearchFragment extends Fragment {
    private View view;
    private WebView webView;
    private String startNum, endNum, httpUrl, result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        initView();
        return view;
    }

    @JavascriptInterface
    private void initView() {
        webView = (WebView) view.findViewById(R.id.my_web);
        webView.loadUrl(Html5.SEARCH_NEWS);
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

    @android.webkit.JavascriptInterface
    public void news(String p) {
        httpUrl = "http://op.juhe.cn/onebox/news/query?key=a847c7e85ab750d5487a7426087cac10&q=" + p;
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

    //    startActivity(new Intent(DetailActivity.this, DActivity.class).putExtra("url",url));
    @JavascriptInterface
    public void detial(String url) {
        startActivity(new Intent(getActivity(), DActivity.class).putExtra("url", url));
    }
}
