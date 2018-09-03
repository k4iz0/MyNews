package ltd.kaizo.mynews.Model.Utils;

import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
public class NytStream {
    public static Observable<NytTopStoriesAPIData> streamFetchTopStories(String section) {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }
    public static Observable<NytMostPopularAPIData> streamFetchMostPopularStories(String section) {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getMostPopular(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }
    public static Observable<NytSearchArticleApiData> streamFetchSearchArticle(String query, String field, String beginDate, String endDate) {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getSearchArticle(query, field, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }
}
