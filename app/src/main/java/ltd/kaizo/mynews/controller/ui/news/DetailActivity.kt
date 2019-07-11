package ltd.kaizo.mynews.controller.ui.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import ltd.kaizo.mynews.R

class DetailActivity : AppCompatActivity() {
    private var articleUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        this.configureToolbar()
        //get the url from the intent
        val intent = intent
        articleUrl = intent.getStringExtra("articleUrl")
        //load the webView with the url
        activity_detail_webview.loadUrl(articleUrl)
    }

    /**
     * Configure toolbar.
     */
    private fun configureToolbar() {
        setSupportActionBar(activity_detail_toolbar)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

    }
}
