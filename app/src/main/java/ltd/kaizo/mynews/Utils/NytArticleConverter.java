package ltd.kaizo.mynews.Utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularResult;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesResult;

/**
 * The type Nyt article converter.
 */
public class NytArticleConverter {
    /**
     * The Nyt top stories article list.
     */
    private List<NytTopStoriesResult> nytTopStoriesArticleList;
    /**
     * The Article formatter list.
     */
    private List<ArticleFormatter> articleFormatterList;
    /**
     * The Nyt most popular article list.
     */
    private List<NytMostPopularResult> nytMostPopularArticleList;


    /**
     * Instantiates a new Nyt article converter.
     *
     * @param nytTopStoriesResult the nyt top stories result
     */
    public NytArticleConverter(NytTopStoriesAPIData nytTopStoriesResult) {
        this.setNytTopStoriesArticleList(nytTopStoriesResult);
    }

    /**
     * Instantiates a new Nyt article converter.
     *
     * @param nytMostPopularAPIdata the nyt most popular api data
     */
    public NytArticleConverter(NytMostPopularAPIData nytMostPopularAPIdata) {
        this.setNytMostPopularArticleList(nytMostPopularAPIdata);
    }

    /**
     * Sets nyt top stories article list.
     *
     * @param nytTopStoriesAPIData the nyt top stories api data
     */
    private void setNytTopStoriesArticleList(NytTopStoriesAPIData nytTopStoriesAPIData) {
        this.nytTopStoriesArticleList = new ArrayList<>();
        if (nytTopStoriesAPIData != null) {
            this.nytTopStoriesArticleList.addAll(nytTopStoriesAPIData.getResults());
        }
    }

    /**
     * Sets nyt most popular article list.
     *
     * @param nytMostPopularAPIdata the nyt most popular api data
     */
    private void setNytMostPopularArticleList(NytMostPopularAPIData nytMostPopularAPIdata) {
        this.nytMostPopularArticleList = new ArrayList<>();
        if (nytMostPopularAPIdata != null) {
            this.nytMostPopularArticleList.addAll(nytMostPopularAPIdata.getResults());
        }
    }

    /**
     * Configure top stories article list for adapter with top stories api data.
     *
     * @return the list
     */
    public List<ArticleFormatter> configureTopStoriesArticleListForAdapter() {
        this.articleFormatterList = new ArrayList<>();
        for (NytTopStoriesResult article : this.nytTopStoriesArticleList) {
            String imageUrl;
            if (article.getMultimedia().size() > 0) {
                imageUrl = article.getMultimedia().get(0).getUrl();
            } else {
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg";

            }
            ArticleFormatter articleFormatter = new ArticleFormatter(article.getTitle(),
                    imageUrl,
                    article.getSection(),
                    article.getSubsection(),
                    article.getPublishedDate());
            this.articleFormatterList.add(articleFormatter);
        }

        return this.articleFormatterList;
    }

    /**
     * Configure most popular article list for adapter with most popular api data.
     *
     * @return the list
     */
    public List<ArticleFormatter> configureMostPopularArticleListForAdapter() {
        this.articleFormatterList = new ArrayList<>();
        for (NytMostPopularResult article : this.nytMostPopularArticleList) {
            String imageUrl;
            if (article.getMedia().size() > 0) {
                imageUrl = article.getMedia().get(0).getMediaMetadata().get(0).getUrl();
            } else {
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg";

            }

            ArticleFormatter articleFormatter = new ArticleFormatter(article.getTitle(),
                    imageUrl,
                    article.getSection(),
                    "",
                    article.getPublishedDate());
            this.articleFormatterList.add(articleFormatter);
        }

        return this.articleFormatterList;
    }


}
