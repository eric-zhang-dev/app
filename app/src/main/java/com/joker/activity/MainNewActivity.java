package com.joker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.tt.MaActivity;
import com.joker.R;
import com.joker.html5.Html5;

/**
 * Created by zhangyue on 2016/5/17.
 */
public class MainNewActivity extends AppCompatActivity{
    private WebView webView;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        button  = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainNewActivity.this,MaActivity.class));
            }
        });
        initView();
    }
    private void initView() {
        webView = (WebView)findViewById(R.id.my_web);
        webView.loadUrl(Html5.MAIN_HTML);
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
    public void onItem(final String type) {
        if (!type.equals("暂未开通")){
            Intent p = new Intent(this,DetailActivity.class);
            p.putExtra("type",type);
            startActivity(p);
        }
    }
}
