package ltd.kaizo.mynews.Utils;

import java.util.List;

import io.reactivex.Observable;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NytService {
    String apiKey = "b35b8674678d4c8b87d09a7605d00a0f";
    String url = "http://api.nytimes.com/";

    @GET("svc/topstories/v2/{section}.json?api-key="+apiKey)
    Observable<NytTopStoriesAPIData> getTopStories(@Path("section") String section);

    public static final Retrofit retrofit = new Retrofit.Builder()

            .baseUrl(url)

            .addConverterFactory(GsonConverterFactory.create())

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .build();
}
