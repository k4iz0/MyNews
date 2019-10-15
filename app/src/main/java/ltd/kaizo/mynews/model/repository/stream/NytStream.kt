package ltd.kaizo.mynews.model.repository.stream

import java.util.concurrent.TimeUnit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ltd.kaizo.mynews.model.NytMostPopularAPI.NytMostPopularAPIData
import ltd.kaizo.mynews.model.NytSearchArticleAPI.NytSearchArticleApiData
import ltd.kaizo.mynews.model.NytTopStoriesAPI.NytTopStoriesAPIData

/**
 * The type Nyt stream.
 */
object NytStream {
    /**
     * Stream fetch top stories observable.
     *
     * @param section the section
     * @return the observable
     */
    fun streamFetchTopStories(section: String): Observable<NytTopStoriesAPIData> {
        val nytService = NytService.retrofit.create(NytService::class.java)
        return nytService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)

    }

    /**
     * Stream fetch most popular stories observable.
     *
     * @param section the section
     * @return the observable
     */
    fun streamFetchMostPopularStories(section: String, period: String): Observable<NytMostPopularAPIData> {
        val nytService = NytService.retrofit.create(NytService::class.java)
        return nytService.getMostPopular(section, period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)

    }

    /**
     * Stream fetch search article observable.
     *
     * @param query     the query
     * @param field     the field
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the observable
     */
    fun streamFetchSearchArticle(query: String, field: String, beginDate: String, endDate: String): Observable<NytSearchArticleApiData> {
        val nytService = NytService.retrofit.create(NytService::class.java)
        return nytService.getSearchArticle(query, field, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)

    }
}
