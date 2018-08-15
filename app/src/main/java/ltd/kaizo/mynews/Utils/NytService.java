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

    @GET("svc/topstories/v2/home.json?api-key=b35b8674678d4c8b87d09a7605d00a0f")
    Observable<NytTopStoriesAPIData> getTopStories();

    public static final Retrofit retrofit = new Retrofit.Builder()

            .baseUrl("http://api.nytimes.com/")

            .addConverterFactory(GsonConverterFactory.create())

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .build();
}
