package com.example.user.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText siteName;
    Button search;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        search();
    }
    private void initialize() {
        webView = findViewById(R.id.webView);
        siteName = findViewById(R.id.siteName);
        search = findViewById(R.id.search);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siteName.setVisibility(View.GONE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true); // разрешаем скрипты
        webView.getSettings().setBuiltInZoomControls(true); // включаем нативный зум
        webView.getSettings().setDisplayZoomControls(false); // скрываем нативные кнопки зума, чтобы двумя пальцами зумить
// команда ниже нужна, чтобы WebView не спрашивала, не открыть ли сайт в другом браузере
// (по умолчанию спрашивает), а сразу сайт открывала в себе
        webView.setWebViewClient(new WebViewClient());
    }
    private void search() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://" + siteName.getText().toString() + ".com");
            }
        });
    }
}
