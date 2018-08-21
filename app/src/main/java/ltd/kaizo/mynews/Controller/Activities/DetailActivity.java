package ltd.kaizo.mynews.Controller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.R;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_detail_webview)
    WebView webView;
    private String articleUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        this.configureToolbar();
        Intent intent = getIntent();
        articleUrl = intent.getStringExtra("articleUrl");
        webView.loadUrl(articleUrl);
    }
    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }
}
