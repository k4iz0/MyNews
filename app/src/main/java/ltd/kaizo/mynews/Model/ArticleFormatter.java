package ltd.kaizo.mynews.Model;

public class ArticleFormatter {

    private String ArticleTitle;
    private String ArticlePictureUrl;
    private String ArticleSection;
    private String ArticlePublishingDate;
    private String ArticleSubSection;
    private String ArticleUrl;

    public ArticleFormatter(String articleTitle, String url, String articlePictureUrl, String articleSection, String articleSubSection, String articlePublishingDate) {
        ArticleTitle = articleTitle;
        ArticleUrl = url;
        ArticlePictureUrl = articlePictureUrl;
        ArticleSection = articleSection;
        ArticlePublishingDate = this.convertDate(articlePublishingDate);
        ArticleSubSection = articleSubSection;
    }

    public String getArticleUrl() {
        return ArticleUrl;
    }

    public String getArticleTitle() {
        return ArticleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        ArticleTitle = articleTitle;
    }

    public String getArticlePictureUrl() {
        return ArticlePictureUrl;
    }

    public void setArticlePictureUrl(String articlePictureUrl) {
        ArticlePictureUrl = articlePictureUrl;
    }

    public String getArticleSection() {
        return ArticleSection;
    }

    public void setArticleSection(String articleSection) {
        ArticleSection = articleSection;
    }

    public String getArticlePublishingDate() {
        return ArticlePublishingDate;
    }

    public void setArticlePublishingDate(String articlePublishingDate) {
        ArticlePublishingDate = articlePublishingDate;
    }

    public String getArticleSubSection() {
        return ArticleSubSection;
    }

    public void setArticleSubSection(String articleSubSection) {
        ArticleSubSection = articleSubSection;
    }

    private String convertDate(String str) {
        String year = str.substring(2, 4);
        String month = str.substring(5, 7);
        String day = str.substring(8, 10);

        return day + "/" + month + "/" + year;
    }
}
