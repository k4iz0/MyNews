package ltd.kaizo.mynews.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesMultimedia;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesResult;

import static org.junit.Assert.assertEquals;

public class NytArticleConverterTest {
    private NytTopStoriesAPIData nytTopStoriesAPIData;

    @Before
    public void setup() {
        this.nytTopStoriesAPIData = new NytTopStoriesAPIData();
        ArrayList<NytTopStoriesResult> results = new ArrayList<>();

        NytTopStoriesResult apiData1 = new NytTopStoriesResult();
        apiData1.setTitle("News Title 1");
        apiData1.setSection("world");
        apiData1.setPublishedDate("2018-09-06");
        apiData1.setUrl("http://www.foo.com");
        apiData1.setMultimedia(new ArrayList<NytTopStoriesMultimedia>());
        NytTopStoriesResult apiData2 = new NytTopStoriesResult();
        apiData2.setTitle("News Title 2");
        apiData2.setSection("europe");
        apiData2.setPublishedDate("2017-05-06");
        apiData2.setUrl("http://www.foo2.com");
        apiData2.setMultimedia(new ArrayList<NytTopStoriesMultimedia>());
        NytTopStoriesResult apiData3 = new NytTopStoriesResult();
        apiData3.setTitle("News Title 3");
        apiData3.setSection("Technology");
        apiData3.setPublishedDate("2016-10-06");
        apiData3.setUrl("http://www.foo3.com");
        apiData3.setMultimedia(new ArrayList<NytTopStoriesMultimedia>());

        results.add(apiData1);
        results.add(apiData2);
        results.add(apiData3);
        this.nytTopStoriesAPIData.setResults(results);
    }

    @Test
    public void GiveApiDataToNytArticleConverterShouldReturnAList() {
        NytArticleConverter articleConverter = new NytArticleConverter(this.nytTopStoriesAPIData);

        List<ArticleFormatter> articleFormatterList = new ArrayList<>(articleConverter.configureTopStoriesArticleListForAdapter());
        //check the size
        assertEquals(3, articleFormatterList.size());

    }

    @Test
    public void GiveApiDataToNytArticleConverterShouldReturnUrl() {
        NytArticleConverter articleConverter = new NytArticleConverter(this.nytTopStoriesAPIData);
        //check the url
        List<ArticleFormatter> articleFormatterList = new ArrayList<>(articleConverter.configureTopStoriesArticleListForAdapter());
        assertEquals("http://www.foo2.com", articleFormatterList.get(1).getArticleUrl());

    }

    @Test
    public void GiveApiDataToNytArticleConverterShouldReturnPublishingDate() {
        NytArticleConverter articleConverter = new NytArticleConverter(this.nytTopStoriesAPIData);
        //check the publishing date
        List<ArticleFormatter> articleFormatterList = new ArrayList<>(articleConverter.configureTopStoriesArticleListForAdapter());
        assertEquals("06/10/16", articleFormatterList.get(2).getArticlePublishingDate());

    }

    @Test
    public void GiveApiDataToNytArticleConverterShouldReturnArticleTitle() {
        NytArticleConverter articleConverter = new NytArticleConverter(this.nytTopStoriesAPIData);
        //check the title
        List<ArticleFormatter> articleFormatterList = new ArrayList<>(articleConverter.configureTopStoriesArticleListForAdapter());
        assertEquals("News Title 1", articleFormatterList.get(0).getArticleTitle());

    }

}