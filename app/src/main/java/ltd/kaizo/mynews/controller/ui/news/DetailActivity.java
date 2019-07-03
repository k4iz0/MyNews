package ltd.kaizo.mynews.controller.ui.news;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.R;

/**
 * The type Detail activity.
 */
public class DetailActivity extends AppCompatActivity {
    /**
     * The Toolbar.
     */
    @BindView(R.id.activity_detail_toolbar)
    Toolbar toolbar;
    /**
     * The Web view.
     */
    @BindView(R.id.activity_detail_webview)
    WebView webView;
    /**
     * The Article url.
     */
    private String articleUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //bind view
        ButterKnife.bind(this);
        this.configureToolbar();
        //get the url from the intent
        Intent intent = getIntent();
        articleUrl = intent.getStringExtra("articleUrl");
        //load the webView with the url
        webView.loadUrl(articleUrl);
    }

    /**
     * Configure toolbar.
     */
    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }
}
