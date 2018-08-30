package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NytSearchArticleDoc {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("print_page")
    @Expose
    private String printPage;

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("multimedia")
    @Expose
    private List<NytSearchArticleMultimedia> multimedia = null;
    @SerializedName("headline")
    @Expose
    private NytSearchArticleHeadline headline;
    @SerializedName("nytSearchArticleKeywords")
    @Expose
    private List<NytSearchArticleKeyword> nytSearchArticleKeywords = null;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("byline")
    @Expose
    private NytSearchArticleByline byline;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private Integer wordCount;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("section_name")
    @Expose
    private String sectionName;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPrintPage() {
        return printPage;
    }

    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<NytSearchArticleMultimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<NytSearchArticleMultimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public NytSearchArticleHeadline getHeadline() {
        return headline;
    }

    public void setHeadline(NytSearchArticleHeadline headline) {
        this.headline = headline;
    }

    public List<NytSearchArticleKeyword> getNytSearchArticleKeywords() {
        return nytSearchArticleKeywords;
    }

    public void setNytSearchArticleKeywords(List<NytSearchArticleKeyword> nytSearchArticleKeywords) {
        this.nytSearchArticleKeywords = nytSearchArticleKeywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public NytSearchArticleByline getByline() {
        return byline;
    }

    public void setByline(NytSearchArticleByline byline) {
        this.byline = byline;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

}
