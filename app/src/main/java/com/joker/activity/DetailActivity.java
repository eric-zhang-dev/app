package com.joker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.joker.R;
import com.joker.html5.Html5;
import com.joker.http.HttpGet;

/**
 * Created by zhangyue on 2016/5/14.
 */
public class DetailActivity extends AppCompatActivity {
    private WebView webView;
    private String type, url,httpUrl;
    private String result;
    private String startNum;
    private String endNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        initUrl();
        initView();

    }

    private void initUrl() {
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "新闻":
//                url = Html5.NEWS;
                startActivity(new Intent(DetailActivity.this,NewsActivity.class));
                break;
            case "精选":
                url = Html5.WECHAT_HTML;
                break;
            case "段子":
//                url = Html5.JOKER_HTML;
                startActivity(new Intent(DetailActivity.this,JokerActivity.class));
                break;
            case "火车时刻表":
//                url = Html5.TIC_HTML;
                startActivity(new Intent(DetailActivity.this,TrainActivity.class));
                break;
            case "公交线路":
                url = Html5.P_BUS_HTML;
                break;
            case "汽车查询":
                url = Html5.BUS_HTML;
                break;
            case "天气预报":
                url = Html5.WEATHER_HTML;
                break;
            case "股票查询":
                url = Html5.STOCK_HTML;
                break;
            case "万年历":
                url = Html5.CALENDAR;
                break;
            case "足球联赛":
                url = Html5.FOOTBALL;
                break;
            case "NBA":
                url = Html5.NBA;
                break;
            case "星座运势":
                url = Html5.FORTUNE;
                break;
            case "周公解梦":
                url = Html5.DREAM_HTML;
                break;
            case "问答机器人":
                startActivity(new Intent(DetailActivity.this,ChatActivity.class));
                break;
        }
    }

    @JavascriptInterface
    private void initView() {
        webView = (WebView) findViewById(R.id.my_web);
        webView.loadUrl(url);
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
    //解梦
    @android.webkit.JavascriptInterface
    public void searchDream(final String pno) {
        this.startNum = pno;
        httpUrl = "http://v.juhe.cn/dream/query?key=07550e95d2556c044825d0f3035fd433&q="+pno;
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void loadData(final String pno) {
        this.startNum = pno;
        httpUrl = "http://v.juhe.cn/weixin/query?key=b20cf7f425f307cefa6bac31f9b88c54&pno=" + startNum + "&dtype=json";
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void loadData(final String startNum, final String endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
        httpUrl = "http://japi.juhe.cn/joke/content/text.from?key=0387ce49ebf86da430611dc3e7c1a668&page="+startNum+"&pagesize="+endNum;
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void searchTic(final String startNum, final String endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
        httpUrl = "http://op.juhe.cn/onebox/train/query_ab.php?key=793389e3ad51445010a7b5608309735f&from="+startNum+"&to="+endNum;
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void searchBus(final String startNum, final String endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
        httpUrl = "http://op.juhe.cn/onebox/bus/query_ab?key=8d8bbdcbd7344c6a2fc5b82bfaa8fce1&from="+startNum+"&to="+endNum;
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void searchStation(final String pno) {
        this.startNum = pno;
        httpUrl = "http://op.juhe.cn/189/bus/station?key=1bd04e425700e9639782c98f94826b16&city=%E4%B8%8A%E6%B5%B7&station="+pno;
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void weather() {
        httpUrl = "http://op.juhe.cn/onebox/weather/query?cityname=%E4%B8%8A%E6%B5%B7&key=0d61588a2680c7c7ef5efff5bf0857ea";
        new Thread(load).start();
    }
    @JavascriptInterface
    public void searchStock(String page) {
        httpUrl = "http://web.juhe.cn:8080/finance/stock/shall?key=3a77e340a8e99308333cbf2d9f6d1105&page="+page+"type=4";
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void calendar() {
        httpUrl = "http://japi.juhe.cn/calendar/day?date=2016-5-23&key=547e8dccc9bc51d737342bf8eff1759b";
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void football() {
        httpUrl = "http://op.juhe.cn/onebox/football/team?key=e7cb1fe5de17b9339f4640819fc8a7be&team=%E7%9A%87%E9%A9%AC";
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void nba() {
        httpUrl = "http://op.juhe.cn/onebox/basketball/team?key=5b1b3751bee03266a1c52f457a9b2894&team=%E7%81%AB%E7%AE%AD";
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void fortune(String item) {
        httpUrl = "http://web.juhe.cn:8080/constellation/getAll?consName="+item+"&type=today&key=b3926f342953ede20dd6756f5e660cb1";
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void news() {
        httpUrl = "http://op.juhe.cn/onebox/news/query?key=a847c7e85ab750d5487a7426087cac10&q=%E6%AF%9B%E6%B3%BD%E4%B8%9C";
        new Thread(load).start();
    }
    @android.webkit.JavascriptInterface
    public void hot() {
        httpUrl = "http://op.juhe.cn/onebox/news/words?key=a847c7e85ab750d5487a7426087cac10";
        new Thread(load).start();
    }
    @JavascriptInterface
    public void detial(String url){
        startActivity(new Intent(DetailActivity.this,DActivity.class).putExtra("url",url));
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
    @JavascriptInterface
    public void DreamDetail(String id){
        Intent intent = new Intent(this,UrlActivity.class);
        intent.putExtra("content",id);
        intent.putExtra("type","2");
        startActivity(intent);
    }
}
