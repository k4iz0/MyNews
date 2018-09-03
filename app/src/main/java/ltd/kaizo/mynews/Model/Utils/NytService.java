package ltd.kaizo.mynews.Model.Utils;

import io.reactivex.Observable;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NytService {
    String apiKey = "b35b8674678d4c8b87d09a7605d00a0f";
    String url = "http://api.nytimes.com/";
    //TODO comment interceptor
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC));


    Retrofit retrofit = new Retrofit.Builder()

            .baseUrl(url)

            .addConverterFactory(GsonConverterFactory.create())

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient.build())
            .build();

    @GET("svc/topstories/v2/{section}.json?api-key=" + apiKey)
    Observable<NytTopStoriesAPIData> getTopStories(@Path("section") String section);

    @GET("svc/mostpopular/v2/mostviewed/{section}/7.json?api-key=" + apiKey)
    Observable<NytMostPopularAPIData> getMostPopular(@Path("section") String section);

    @GET("svc/search/v2/articlesearch.json?api-key=" + apiKey)
    Observable<NytSearchArticleApiData> getSearchArticle(@Query("q") String query, @Query("fq") String field, @Query("begin_date") String beginDate, @Query("end_date") String endDate);

}