package ltd.kaizo.mynews.controller.ui.news


import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.fragment_news.*
import ltd.kaizo.mynews.R
import ltd.kaizo.mynews.controller.ui.base.BaseFragment
import ltd.kaizo.mynews.model.ArticleFormatter
import ltd.kaizo.mynews.model.NytArticleConverter
import ltd.kaizo.mynews.model.NytMostPopularAPI.NytMostPopularAPIData
import ltd.kaizo.mynews.model.NytSearchArticleAPI.NytSearchArticleApiData
import ltd.kaizo.mynews.model.NytTopStoriesAPI.NytTopStoriesAPIData
import ltd.kaizo.mynews.model.SearchQuery
import ltd.kaizo.mynews.model.TabsNames
import ltd.kaizo.mynews.model.repository.stream.NytStream
import ltd.kaizo.mynews.utils.*
import ltd.kaizo.mynews.utils.DataRecordManager.read
import ltd.kaizo.mynews.utils.DataRecordManager.saveData
import ltd.kaizo.mynews.views.adapter.NytRecycleViewAdapter
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import timber.log.Timber
import java.util.*

/**
 * The type News fragment.
 */
/**
 * Instantiates a new News fragment.
 */
class NewsFragment : BaseFragment() {
    private lateinit var newsViewModel: NewsViewModel

    private var section: String = ""
    private var position: Int = 0
    private lateinit var adapter: NytRecycleViewAdapter
    private var disposable: Disposable? = null

    /**
     * The Article formatter list.
     */
    private lateinit var articleFormatterList: MutableList<ArticleFormatter>

    /**
     * The Gson str.
     */
    private var gsonStr: String? = ""

    override val fragmentLayout = R.layout.fragment_news
    override fun configureViewModel() {
        newsViewModel = getSharedViewModel()
    }

    override fun configureObserver() {
        newsViewModel.section.observe(this, Observer { section ->
            updateSection(section)
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
            TabsNames.TOP_STORIES -> executeStreamFetchTopStories(newsViewModel.section.value!!)
            TabsNames.MOST_POPULAR -> executeStreamFetchMostPopularStories(this.apiPeriod)
            TabsNames.CUSTOM_TAB -> executeStreamFetchTopStories(this.section)
            TabsNames.SEARCH -> executeStreamFetchSearchArticle(this.gsonStr)
        }
    }

    /**
     * Execute stream fetch top stories
     * and convert the result to a list for the recycleView
     */
    private fun executeStreamFetchTopStories(section: String) {
        this.disposable = NytStream.streamFetchTopStories(section).subscribeWith(object : DisposableObserver<NytTopStoriesAPIData>() {
            override fun onNext(nytTopStoriesAPIData: NytTopStoriesAPIData) {
                val nytArticleConverter = NytArticleConverter(nytTopStoriesAPIData)
                updateUI(nytArticleConverter.configureTopStoriesArticleListForAdapter())
                Timber.i("top httpRequest in progress : - status = ${nytTopStoriesAPIData.status}\n taille des résultats = ${nytTopStoriesAPIData.numResults}\n section = $section")
            }

            override fun onError(e: Throwable) {
                Timber.i("top error : $e")
            }

            override fun onComplete() {
                Timber.i("top complete ")
            }
        })

    }

    /**
     * Execute stream fetch most popular stories
     * and convert the result to a list for the recycleView
     */
    private fun executeStreamFetchMostPopularStories(apiPeriod: String) {
        this.disposable = NytStream.streamFetchMostPopularStories(newsViewModel.section.value!!, apiPeriod).subscribeWith(object : DisposableObserver<NytMostPopularAPIData>() {
            override fun onNext(nytMostPopularAPIdata: NytMostPopularAPIData) {
                Timber.i("MP httpRequest in progress : - status = ${nytMostPopularAPIdata.status}\n taille des résultats = ${nytMostPopularAPIdata.numResults}\n section = $section")
                val nytArticleConverter = NytArticleConverter(nytMostPopularAPIdata)
                updateUI(nytArticleConverter.configureMostPopularArticleListForAdapter())
            }

            override fun onError(e: Throwable) {
                Timber.i("MP error : $e")

            }

            override fun onComplete() {
                Timber.i("MP complete ")
            }
        })

    }

    /**
     * Execute stream fetch search article,
     * and convert the result to a list for the recycleView
     */
    private fun executeStreamFetchSearchArticle(gsonStr: String?) {
        val gson = Gson()
        val searchQuery = gson.fromJson(gsonStr, SearchQuery::class.java)
        this.disposable = NytStream.streamFetchSearchArticle(searchQuery.queryTerms, searchQuery.queryFields, searchQuery.beginDate!!, searchQuery.endDate!!)
                .subscribeWith(object : DisposableObserver<NytSearchArticleApiData>() {
                    override fun onNext(nytSearchArticleApiData: NytSearchArticleApiData) {
                        if (nytSearchArticleApiData.nytSearchArticleResponse.nytSearchArticleDocs.size > 0) {
                            val nytArticleConverter = NytArticleConverter(nytSearchArticleApiData)
                            updateUI(nytArticleConverter.configureSearchArticleListForAdapter())
                        }
                    }

                    override fun onError(e: Throwable) {
                        Timber.i("search error : $e")
                    }

                    override fun onComplete() {
                        Timber.i("search complete ")
                    }
                })

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
        fragment_news_swipe_container.setColorSchemeColors(getColor(context!!, R.color.bluePrimary))
        fragment_news_swipe_container.setOnRefreshListener { executeHttpRequest() }
    }

    /**
     * Update ui with a new list of articles for the recycleView adapter
     *
     * @param articleList the article list
     */
    private fun updateUI(articleList: List<ArticleFormatter>) {
        fragment_news_swipe_container.isRefreshing = false
        this.articleFormatterList.clear()
        this.articleFormatterList.addAll(articleList)
        adapter.notifyDataSetChanged()
    }

    /**
     * Dispose when destroy.
     */
    private fun disposeWhenDestroy() {
        if (disposable != null && !disposable!!.isDisposed) disposable!!.dispose()
    }

    /**
     * Update section and save data to sharedPreferences
     *
     * @param section the section
     */
    private fun updateSection(section: String) {
        saveData(this.position, section)
        this.executeHttpRequest()
    }
}
