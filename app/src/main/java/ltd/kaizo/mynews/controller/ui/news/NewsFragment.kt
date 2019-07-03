package ltd.kaizo.mynews.controller.ui.news


import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_news.*
import ltd.kaizo.mynews.Model.ArticleFormatter
import ltd.kaizo.mynews.Model.TabsNames
import ltd.kaizo.mynews.R
import ltd.kaizo.mynews.Utils.DataRecordManager.*
import ltd.kaizo.mynews.controller.ui.base.BaseFragment
import ltd.kaizo.mynews.views.adapter.NytRecycleViewAdapter
import java.util.*

/**
 * The type News fragment.
 */
/**
 * Instantiates a new News fragment.
 */
class NewsFragment : BaseFragment() {
    private lateinit var parentActivity: NewsActivity
    private lateinit var newsViewModel: NewsViewModel
    /**
     * The Section.
     */
    private var section: String = ""
    /**
     * The Position.
     */
    private var position: Int = 0
    /**
     * The Adapter.
     */
    private lateinit var adapter: NytRecycleViewAdapter

    /**
     * The Article formatter list.
     */
    private var articleFormatterList: MutableList<ArticleFormatter>? = null

    /**
     * The Gson str.
     */
    private var gsonStr: String? = ""

    override val fragmentLayout = R.layout.fragment_news
    override fun configureViewModel() {
        parentActivity = activity as NewsActivity
        newsViewModel = parentActivity.newsViewModel
    }

    override fun configureObserver() {
        newsViewModel.nytArticleList.observe(this, Observer { articleList ->
            if (articleList != null && articleList.size > 0) {
                updateUI(articleList)
            }
        })
    }

    /**
     * read the api period from sharedPreferences
     * @return the api period
     */
    private val apiPeriod = read(KEY_API_PERIOD, "7")

    companion object {
        /**
         * New instance base fragment.
         *
         * @param position the position of the tab
         * @param section  the section
         * @return the base fragment
         */
        fun newInstance(position: Int, section: String): BaseFragment {
            val frag = NewsFragment()
            val bundle = Bundle()
            bundle.putString(KEY_SECTION, section)
            bundle.putInt(KEY_POSITION, position)
            frag.arguments = bundle
            return frag

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            this.section = arguments!!.getString(KEY_SECTION) ?: ""
            this.position = arguments!!.getInt(KEY_POSITION)
            this.gsonStr = arguments!!.getString(KEY_SEARCHQUERY)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.disposeWhenDestroy()
    }

    override fun configureDesign() {}

    override fun updateDesign() {
        this.configureRecycleView()
        this.executeHttpRequest()
        this.configureSwipeRefreshLayout()

    }

    /**
     * Execute http request according to tabs
     */
    private fun executeHttpRequest() {
        when (TabsNames.values()[this.position]) {
            TabsNames.TOP_STORIES -> newsViewModel.executeStreamFetchTopStories(this.section)
            TabsNames.MOST_POPULAR -> newsViewModel.executeStreamFetchMostPopularStories(this.section, this.apiPeriod)
            TabsNames.CUSTOM_TAB -> newsViewModel.executeStreamFetchTopStories(this.section)
            TabsNames.SEARCH -> newsViewModel.executeStreamFetchSearchArticle(this.gsonStr)
        }
    }

    /**
     * Configure the recycleView by giving to the adapter
     * a formatted list of articles, a glide Object
     * and a callback to manage the item's click
     */
    private fun configureRecycleView() {
        this.articleFormatterList = ArrayList()
        this.adapter = NytRecycleViewAdapter(this.articleFormatterList, Glide.with(this)) { articleItem -> onArticleItemClicked(articleItem) }
        fragment_news_recycleview.adapter = adapter
        fragment_news_recycleview.layoutManager = LinearLayoutManager(activity)

    }

    private fun onArticleItemClicked(articleItem: ArticleFormatter) {
        val detailActivity = Intent(activity, DetailActivity::class.java)
        detailActivity.putExtra(getString(R.string.articleUrl), articleItem.articleUrl)
        startActivity(detailActivity)
    }

    /**
     * Configure swipe refresh layout.
     * change default color and execute a new HTTP request
     */
    private fun configureSwipeRefreshLayout() {
        fragment_news_swipe_container.setColorSchemeColors(getColor(parentActivity, R.color.bluePrimary))
        fragment_news_swipe_container.setOnRefreshListener { executeHttpRequest() }
    }

    /**
     * Update ui with a new list of articles for the recycleView adapter
     *
     * @param articleList the article list
     */
    private fun updateUI(articleList: List<ArticleFormatter>) {
        fragment_news_swipe_container.isRefreshing = false
        this.articleFormatterList!!.clear()
        this.articleFormatterList!!.addAll(articleList)
        adapter.notifyDataSetChanged()
    }

    /**
     * Dispose when destroy.
     */
    private fun disposeWhenDestroy() {
        if (newsViewModel.disposable != null && !newsViewModel.disposable!!.isDisposed) newsViewModel.disposable!!.dispose()
    }

    /**
     * Update section and save data to sharedPreferences
     *
     * @param section the section
     */
    fun updateSection(section: String) {
        this.section = section
        saveData(this.position, this.section)
        this.executeHttpRequest()
    }
}
