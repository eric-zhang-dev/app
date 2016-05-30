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
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.joker.R;
import com.joker.activity.DetailActivity;
import com.joker.http.HttpGet;

/**
 * Created by zhangyue on 2016/5/14.
 */
public class Three extends Fragment {
    private WebView webView;
    private String result;
    private String pno;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        webView = (WebView)view.findViewById(R.id.my_web);
        webView.loadUrl("file:///android_asset/www/wechat.html");
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
    }

    /**
     * 显示选择相片菜单窗口
     */
    @android.webkit.JavascriptInterface
    public void loadData(final String pno) {
        this.pno = pno;
        new Thread(load).start();
    }
    private Runnable load = new Runnable() {
        @Override
        public void run() {
            result = HttpGet.httpGet("http://v.juhe.cn/weixin/query?key=b20cf7f425f307cefa6bac31f9b88c54&pno=" + pno + "&dtype=json");
            handler.obtainMessage(1).sendToTarget();
        }
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
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
    @android.webkit.JavascriptInterface
    public void detial(final String urls){
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                showDetial(urls);
            }
        }.sendEmptyMessage(0);

    }

    private void showDetial(String urls) {
        Intent p = new Intent(getActivity(),DetailActivity.class);
        p.putExtra("url",urls);
        startActivity(p);
    }
}
