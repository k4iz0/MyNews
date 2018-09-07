package ltd.kaizo.mynews.Model;

/**
 * The type Article formatter.
 */
public class ArticleFormatter {

    /**
     * The Article title.
     */
    private String ArticleTitle;
    /**
     * The Article picture url.
     */
    private String ArticlePictureUrl;
    /**
     * The Article section.
     */
    private String ArticleSection;
    /**
     * The Article publishing date.
     */
    private String ArticlePublishingDate;
    /**
     * The Article sub section.
     */
    private String ArticleSubSection;
    /**
     * The Article url.
     */
    private String ArticleUrl;

    /**
     * Instantiates a new Article formatter.
     *
     * @param articleTitle          the article title
     * @param url                   the url
     * @param articlePictureUrl     the article picture url
     * @param articleSection        the article section
     * @param articleSubSection     the article sub section
     * @param articlePublishingDate the article publishing date
     */
    public ArticleFormatter(String articleTitle, String url, String articlePictureUrl, String articleSection, String articleSubSection, String articlePublishingDate) {
        ArticleTitle = articleTitle;
        ArticleUrl = url;
        ArticlePictureUrl = articlePictureUrl;
        ArticleSection = articleSection;
        ArticlePublishingDate = this.convertDate(articlePublishingDate);
        ArticleSubSection = articleSubSection;
    }

    /**
     * Gets article url.
     *
     * @return the article url
     */
    public String getArticleUrl() {
        return ArticleUrl;
    }

    /**
     * Gets article title.
     *
     * @return the article title
     */
    public String getArticleTitle() {
        return ArticleTitle;
    }

    /**
     * Sets article title.
     *
     * @param articleTitle the article title
     */
    public void setArticleTitle(String articleTitle) {
        ArticleTitle = articleTitle;
    }

    /**
     * Gets article picture url.
     *
     * @return the article picture url
     */
    public String getArticlePictureUrl() {
        return ArticlePictureUrl;
    }

    /**
     * Sets article picture url.
     *
     * @param articlePictureUrl the article picture url
     */
    public void setArticlePictureUrl(String articlePictureUrl) {
        ArticlePictureUrl = articlePictureUrl;
    }

    /**
     * Gets article section.
     *
     * @return the article section
     */
    public String getArticleSection() {
        return ArticleSection;
    }

    /**
     * Sets article section.
     *
     * @param articleSection the article section
     */
    public void setArticleSection(String articleSection) {
        ArticleSection = articleSection;
    }

    /**
     * Gets article publishing date.
     *
     * @return the article publishing date
     */
    public String getArticlePublishingDate() {
        return ArticlePublishingDate;
    }

    /**
     * Sets article publishing date.
     *
     * @param articlePublishingDate the article publishing date
     */
    public void setArticlePublishingDate(String articlePublishingDate) {
        ArticlePublishingDate = articlePublishingDate;
    }

    /**
     * Gets article sub section.
     *
     * @return the article sub section
     */
    public String getArticleSubSection() {
        return ArticleSubSection;
    }

    /**
     * Sets article sub section.
     *
     * @param articleSubSection the article sub section
     */
    public void setArticleSubSection(String articleSubSection) {
        ArticleSubSection = articleSubSection;
    }

    /**
     * Convert date string.
     *
     * @param str the string yyyy-mm-dd
     * @return the string dd/mm/yy
     */
    private String convertDate(String str) {
        String year = str.substring(2, 4);
        String month = str.substring(5, 7);
        String day = str.substring(8, 10);

        return day + "/" + month + "/" + year;
    }
}
