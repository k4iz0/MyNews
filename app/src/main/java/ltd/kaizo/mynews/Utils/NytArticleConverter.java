package ltd.kaizo.mynews.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesResult;

public class NytArticleConverter {
    private List<NytTopStoriesResult> nytArticleList;
    private List<NytTopStoriesResult> nytMultimediaList;
    private List<ArticleFormatter> articleFormatterList;


    public NytArticleConverter(NytTopStoriesAPIData nytTopStoriesAPIData) {
        this.setNytArticleList(nytTopStoriesAPIData);
        this.setNytMultimediaList(nytTopStoriesAPIData.getResults());

    }

    private void setNytMultimediaList(NytTopStoriesResult[] nytArticleResult) {
        this.nytMultimediaList = new ArrayList<>();
        this.nytMultimediaList.addAll(Arrays.asList(nytArticleResult));
    }


    public List<NytTopStoriesResult> getNytArticleList() {
        return nytArticleList;
    }

    private void setNytArticleList(NytTopStoriesAPIData nytTopStoriesAPIData) {
        this.nytArticleList = new ArrayList<>();
        if (nytTopStoriesAPIData != null) {
            this.nytArticleList.addAll(Arrays.asList(nytTopStoriesAPIData.getResults()));

        }
    }

    public List<ArticleFormatter> configureArticleListForAdapter() {
        this.articleFormatterList = new ArrayList<>();
        for (NytTopStoriesResult article : this.nytArticleList) {
            String imageUrl;
            if (article.getMultimedia().length > 0) {
                imageUrl = article.getMultimedia()[0].getUrl();
            } else {
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg";

            }
            ArticleFormatter articleFormatter = new ArticleFormatter(article.getTitle(),
                    imageUrl,
                    article.getSection(),
                    article.getSubsection(),
                    article.getPublished_date());
            this.articleFormatterList.add(articleFormatter);
        }

        return this.articleFormatterList;
    }


}
