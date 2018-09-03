package ltd.kaizo.mynews.Model;

import java.util.ArrayList;
import java.util.List;

import ltd.kaizo.mynews.Model.ArticleFormatter;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularResult;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleDoc;
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
     * The Nyt search article list.
     */
    private List<NytSearchArticleDoc> nytSearchArticleList;


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
     * Instantiates a new Nyt article converter.
     *
     * @param nytSearchArticleApiData the nyt search article api data
     */
    public NytArticleConverter(NytSearchArticleApiData nytSearchArticleApiData) {
        this.setNytSearchArticleList(nytSearchArticleApiData);
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
     * Sets nyt search article list.
     *
     * @param nytSearchArticleApiData the nyt search article api data
     */
    private void setNytSearchArticleList(NytSearchArticleApiData nytSearchArticleApiData) {
        this.nytSearchArticleList = new ArrayList<>();
        if (nytSearchArticleApiData != null) {
            this.nytSearchArticleList.addAll(nytSearchArticleApiData.getNytSearchArticleResponse().getNytSearchArticleDocs());
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
                    article.getUrl(),
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
                    article.getUrl(),
                    imageUrl,
                    article.getSection(),
                    "",
                    article.getPublishedDate());
            this.articleFormatterList.add(articleFormatter);
        }

        return this.articleFormatterList;
    }

    /**
     * Configure search article list for adapter with search api data.
     *
     * @return the list
     */
    public List<ArticleFormatter> configureSearchArticleListForAdapter() {
        this.articleFormatterList = new ArrayList<>();
        for (NytSearchArticleDoc article : this.nytSearchArticleList) {
            String imageUrl;
            if (article.getMultimedia().size() > 0) {
                imageUrl = "https://static01.nyt.com/"+article.getMultimedia().get(0).getUrl();
            } else {
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg";
            }

            ArticleFormatter articleFormatter = new ArticleFormatter(article.getSnippet(),
                    article.getWebUrl(),
                    imageUrl,
                    article.getSectionName(),
                    "",
                    article.getPubDate());
            this.articleFormatterList.add(articleFormatter);
        }

        return this.articleFormatterList;
    }


}
