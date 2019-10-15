package ltd.kaizo.mynews.model.repository.stream

import io.reactivex.Observable
import ltd.kaizo.mynews.model.NytMostPopularAPI.NytMostPopularAPIData
import ltd.kaizo.mynews.model.NytSearchArticleAPI.NytSearchArticleApiData
import ltd.kaizo.mynews.model.NytTopStoriesAPI.NytTopStoriesAPIData
import ltd.kaizo.mynews.utils.API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The interface Nyt service.
 */
interface NytService {

    /**
     * Gets top stories.
     *
     * @param section the section
     * @return the top stories
     */
    @GET("svc/topstories/v2/{section}.json?api-key=$API_KEY")
    fun getTopStories(@Path("section") section: String): Observable<NytTopStoriesAPIData>

    /**
     * Gets most popular.
     *
     * @param section the section
     * @return the most popular
     */
    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json?api-key=$API_KEY")
    fun getMostPopular(@Path("section") section: String, @Path("period") period: String): Observable<NytMostPopularAPIData>

    /**
     * Gets search article.
     *
     * @param query     the query
     * @param field     the field
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the search article
     */
    @GET("svc/search/v2/articlesearch.json?api-key=$API_KEY")
    fun getSearchArticle(@Query("q") query: String, @Query("fq") field: String, @Query("begin_date") beginDate: String, @Query("end_date") endDate: String): Observable<NytSearchArticleApiData>

    companion object {
              /**
         * The constant url.
         */
        val url = "http://api.nytimes.com/"
        /**
         * The constant interceptor.
         */
        //Uncomment line 36 & 49 to add interceptor for debug purpose
        val interceptor = HttpLoggingInterceptor()
        /**
         * The constant okHttpClient.
         */
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC))


        /**
         * The constant retrofit builder
         */
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build()
    }

}
