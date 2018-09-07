package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article doc.
 */
public class NytSearchArticleDoc {

    /**
     * The Web url.
     */
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    /**
     * The Snippet.
     */
    @SerializedName("snippet")
    @Expose
    private String snippet;
    /**
     * The Print page.
     */
    @SerializedName("print_page")
    @Expose
    private String printPage;

    /**
     * The Source.
     */
    @SerializedName("source")
    @Expose
    private String source;
    /**
     * The Multimedia.
     */
    @SerializedName("multimedia")
    @Expose
    private List<NytSearchArticleMultimedia> multimedia = null;
    /**
     * The Headline.
     */
    @SerializedName("headline")
    @Expose
    private NytSearchArticleHeadline headline;
    /**
     * The Nyt search article keywords.
     */
    @SerializedName("nytSearchArticleKeywords")
    @Expose
    private List<NytSearchArticleKeyword> nytSearchArticleKeywords = null;
    /**
     * The Pub date.
     */
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    /**
     * The Document type.
     */
    @SerializedName("document_type")
    @Expose
    private String documentType;
    /**
     * The News desk.
     */
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    /**
     * The Byline.
     */
    @SerializedName("byline")
    @Expose
    private NytSearchArticleByline byline;
    /**
     * The Type of material.
     */
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    /**
     * The Id.
     */
    @SerializedName("_id")
    @Expose
    private String id;
    /**
     * The Word count.
     */
    @SerializedName("word_count")
    @Expose
    private Integer wordCount;
    /**
     * The Score.
     */
    @SerializedName("score")
    @Expose
    private Float score;
    /**
     * The Abstract.
     */
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    /**
     * The Section name.
     */
    @SerializedName("section_name")
    @Expose
    private String sectionName;

    /**
     * Gets web url.
     *
     * @return the web url
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * Sets web url.
     *
     * @param webUrl the web url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * Gets snippet.
     *
     * @return the snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * Sets snippet.
     *
     * @param snippet the snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * Gets print page.
     *
     * @return the print page
     */
    public String getPrintPage() {
        return printPage;
    }

    /**
     * Sets print page.
     *
     * @param printPage the print page
     */
    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets source.
     *
     * @param source the source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Gets multimedia.
     *
     * @return the multimedia
     */
    public List<NytSearchArticleMultimedia> getMultimedia() {
        return multimedia;
    }

    /**
     * Sets multimedia.
     *
     * @param multimedia the multimedia
     */
    public void setMultimedia(List<NytSearchArticleMultimedia> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * Gets headline.
     *
     * @return the headline
     */
    public NytSearchArticleHeadline getHeadline() {
        return headline;
    }

    /**
     * Sets headline.
     *
     * @param headline the headline
     */
    public void setHeadline(NytSearchArticleHeadline headline) {
        this.headline = headline;
    }

    /**
     * Gets nyt search article keywords.
     *
     * @return the nyt search article keywords
     */
    public List<NytSearchArticleKeyword> getNytSearchArticleKeywords() {
        return nytSearchArticleKeywords;
    }

    /**
     * Sets nyt search article keywords.
     *
     * @param nytSearchArticleKeywords the nyt search article keywords
     */
    public void setNytSearchArticleKeywords(List<NytSearchArticleKeyword> nytSearchArticleKeywords) {
        this.nytSearchArticleKeywords = nytSearchArticleKeywords;
    }

    /**
     * Gets pub date.
     *
     * @return the pub date
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Sets pub date.
     *
     * @param pubDate the pub date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * Gets document type.
     *
     * @return the document type
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Sets document type.
     *
     * @param documentType the document type
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * Gets news desk.
     *
     * @return the news desk
     */
    public String getNewsDesk() {
        return newsDesk;
    }

    /**
     * Sets news desk.
     *
     * @param newsDesk the news desk
     */
    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    /**
     * Gets byline.
     *
     * @return the byline
     */
    public NytSearchArticleByline getByline() {
        return byline;
    }

    /**
     * Sets byline.
     *
     * @param byline the byline
     */
    public void setByline(NytSearchArticleByline byline) {
        this.byline = byline;
    }

    /**
     * Gets type of material.
     *
     * @return the type of material
     */
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     * Sets type of material.
     *
     * @param typeOfMaterial the type of material
     */
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets word count.
     *
     * @return the word count
     */
    public Integer getWordCount() {
        return wordCount;
    }

    /**
     * Sets word count.
     *
     * @param wordCount the word count
     */
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Float getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * Gets abstract.
     *
     * @return the abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Sets abstract.
     *
     * @param _abstract the abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    /**
     * Gets section name.
     *
     * @return the section name
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * Sets section name.
     *
     * @param sectionName the section name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

}
