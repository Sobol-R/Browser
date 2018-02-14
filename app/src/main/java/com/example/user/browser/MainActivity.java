package com.example.user.browser;

import android.inputmethodservice.Keyboard;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText siteName;
    Button search;
    Button show;
    RelativeLayout relativeLayout;
    RelativeLayout mainRL;
    ArrayList<String> linkList = new ArrayList<>();
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        search();
    }
    private void initialize() {
        back = findViewById(R.id.back);
        mainRL = findViewById(R.id.mainRL);
        show = findViewById(R.id.show);
        relativeLayout = findViewById(R.id.first);
        webView = findViewById(R.id.webView);
        siteName = findViewById(R.id.siteName);
        search = findViewById(R.id.search);
        webView.getSettings().setJavaScriptEnabled(true); // разрешаем скрипты
        webView.getSettings().setBuiltInZoomControls(true); // включаем нативный зум
        webView.getSettings().setDisplayZoomControls(false); // скрываем нативные кнопки зума, чтобы двумя пальцами зумить
        webView.setWebViewClient(new WebViewClient());// команда ниже нужна, чтобы WebView не спрашивала, не открыть ли сайт в другом браузере
    }
    private void search() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://" + siteName.getText().toString() + ".com");
                linkList.add(webView.getUrl());
                //k = webView.getUrl();
            }
        });
        System.out.print(webView.getScrollY());
        siteName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)) {
                    webView.loadUrl("http://" + siteName.getText().toString() + ".com");
                    linkList.add(webView.getUrl());
                    //k = webView.getUrl();
                    return true;
                }
                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] url = linkList.toArray(new String[linkList.size()]);
                for (int i = 0; i < url.length; i++ ) {
                    if (url[i].equals(webView.getUrl())) {
                        webView.loadUrl(url[i - 1]);
                    }
                }
            }
        });
    }
}
