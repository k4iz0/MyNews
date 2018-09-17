package ltd.kaizo.mynews;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.Utils.NytStream;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NytStreamAPITest {

    @Test
    public void nytTopStoriesApiResponseShouldBeOK() {
        Observable<NytTopStoriesAPIData> apiData = NytStream.streamFetchTopStories("home");
        TestObserver<NytTopStoriesAPIData> testObserver = new TestObserver<>();
        apiData.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();
        String apiResponseStatus = testObserver.values().get(0).getStatus();


        assertEquals("OK", apiResponseStatus);
    }
    @Test
    public void nytSearchArticleApiResponseShouldBeOK() {
        Observable<NytSearchArticleApiData> apiData = NytStream.streamFetchSearchArticle("test","arts",null,null);
        TestObserver<NytSearchArticleApiData> testObserver = new TestObserver<>();
        apiData.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();
        String apiResponseStatus = testObserver.values().get(0).getStatus();


        assertEquals("OK", apiResponseStatus);
    }
    @Test
    public void nytMostPopularApiResponseShouldBeOK() {
        Observable<NytMostPopularAPIData> apiData = NytStream.streamFetchMostPopularStories("test","1");
        TestObserver<NytMostPopularAPIData> testObserver = new TestObserver<>();
        apiData.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();
        String apiResponseStatus = testObserver.values().get(0).getStatus();


        assertEquals("OK", apiResponseStatus);
    }

}
