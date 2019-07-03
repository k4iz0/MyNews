package ltd.kaizo.mynews.controller.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import ltd.kaizo.mynews.Model.ArticleFormatter
import ltd.kaizo.mynews.Model.NytArticleConverter
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData
import ltd.kaizo.mynews.Model.SearchQuery
import ltd.kaizo.mynews.Model.repository.stream.NytStream
import timber.log.Timber

class NewsViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    var disposable: Disposable? = null
    private lateinit var nytArticleConverter: NytArticleConverter
    var nytArticleList = MutableLiveData<MutableList<ArticleFormatter>>()

    /**
     * Execute stream fetch top stories
     * and convert the result to a list for the recycleView
     */
    fun executeStreamFetchTopStories(section: String) {
        nytArticleList.value = mutableListOf()
        this.disposable = NytStream.streamFetchTopStories(section).subscribeWith(object : DisposableObserver<NytTopStoriesAPIData>() {
            override fun onNext(nytTopStoriesAPIData: NytTopStoriesAPIData) {
                nytArticleConverter = NytArticleConverter(nytTopStoriesAPIData)
                nytArticleList.postValue(nytArticleConverter.configureTopStoriesArticleListForAdapter())
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
    fun executeStreamFetchMostPopularStories(section: String, apiPeriod: String) {
        nytArticleList.value = mutableListOf()
        this.disposable = NytStream.streamFetchMostPopularStories(section, apiPeriod).subscribeWith(object : DisposableObserver<NytMostPopularAPIData>() {
            override fun onNext(nytMostPopularAPIdata: NytMostPopularAPIData) {
                Timber.i("MP httpRequest in progress : - status = ${nytMostPopularAPIdata.status}\n taille des résultats = ${nytMostPopularAPIdata.numResults}\n section = $section")
                nytArticleConverter = NytArticleConverter(nytMostPopularAPIdata)
                nytArticleList.postValue(nytArticleConverter.configureMostPopularArticleListForAdapter())
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
    fun executeStreamFetchSearchArticle(gsonStr: String?) {
        nytArticleList.value = mutableListOf()
        val gson = Gson()
        val searchQuery = gson.fromJson(gsonStr, SearchQuery::class.java)
        this.disposable = NytStream.streamFetchSearchArticle(searchQuery.queryTerms, searchQuery.queryFields, searchQuery.beginDate, searchQuery.endDate)
                .subscribeWith(object : DisposableObserver<NytSearchArticleApiData>() {
                    override fun onNext(nytSearchArticleApiData: NytSearchArticleApiData) {
                        if (nytSearchArticleApiData.nytSearchArticleResponse.nytSearchArticleDocs.size > 0) {
                            nytArticleConverter = NytArticleConverter(nytSearchArticleApiData)
                            nytArticleList.value?.addAll(nytArticleConverter.configureSearchArticleListForAdapter())
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
}