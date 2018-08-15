package ltd.kaizo.mynews.Controller.Fragments;

import org.junit.BeforeClass;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Utils.NytStream;

import static org.junit.Assert.*;

public class NewsFragmentTest {

    @Test
    public void nytApiResponseShouldBeOK() throws Exception {
        Observable<NytTopStoriesAPIData> apiData = NytStream.streamFetchTopStories();
        TestObserver<NytTopStoriesAPIData> testObserver = new TestObserver<>();
        apiData.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue
        String apiResponseStatus = testObserver.values().get(0).getStatus();


        assertEquals(apiResponseStatus, "OK");
    }

}