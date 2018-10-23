package test.message.com.messagetestdemo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import test.message.com.messagetestdemo.R;
import test.message.com.messagetestdemo.base.BaseActivity;


public class WebActivity extends BaseActivity {

    private ImageView back;
    private TextView title;
    private WebView web;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);

        try{
            url = getIntent().getExtras().getString("url");
        }catch (Exception e){
            e.printStackTrace();
        }

        initView();

    }

    private void initView() {

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = (TextView) findViewById(R.id.title);
        web = (WebView) findViewById(R.id.web);

        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(true);
        settings.setUseWideViewPort(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        web.setWebViewClient(new MyWebViewClient());
        web.loadUrl(url);

    }

    private class MyWebViewClient extends WebViewClient {

        public MyWebViewClient() {

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d("WebView", "onPageFinished ");
            super.onPageFinished(view, url);
            web.getSettings().setBlockNetworkImage(false);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            // 处理界面加载失败的问题

        }
    }
}
