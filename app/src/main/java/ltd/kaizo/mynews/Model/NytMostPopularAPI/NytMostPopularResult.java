package ltd.kaizo.mynews.Model.NytMostPopularAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Nyt most popular result.
 */
public class NytMostPopularResult {


    /**
     * The Url.
     */
    @SerializedName("url")
    @Expose
    private String url;
    /**
     * The Adx keywords.
     */
    @SerializedName("adx_keywords")
    @Expose
    private String adxKeywords;
    /**
     * The Column.
     */
    @SerializedName("column")
    @Expose
    private Object column;
    /**
     * The Section.
     */
    @SerializedName("section")
    @Expose
    private String section;
    /**
     * The Byline.
     */
    @SerializedName("byline")
    @Expose
    private String byline;
    /**
     * The Type.
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * The Title.
     */
    @SerializedName("title")
    @Expose
    private String title;
    /**
     * The Abstract.
     */
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    /**
     * The Published date.
     */
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    /**
     * The Source.
     */
    @SerializedName("source")
    @Expose
    private String source;
    /**
     * The Id.
     */
    @SerializedName("id")
    @Expose
    private Long id;
    /**
     * The Asset id.
     */
    @SerializedName("asset_id")
    @Expose
    private Long assetId;
    /**
     * The Views.
     */
    @SerializedName("views")
    @Expose
    private Integer views;
    /**
     * The Media.
     */
    @SerializedName("media")
    @Expose
    private List<NytMostPopularMedia> media = null;

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets adx keywords.
     *
     * @return the adx keywords
     */
    public String getAdxKeywords() {
        return adxKeywords;
    }

    /**
     * Sets adx keywords.
     *
     * @param adxKeywords the adx keywords
     */
    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public Object getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(Object column) {
        this.column = column;
    }

    /**
     * Gets section.
     *
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Sets section.
     *
     * @param section the section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * Gets byline.
     *
     * @return the byline
     */
    public String getByline() {
        return byline;
    }

    /**
     * Sets byline.
     *
     * @param byline the byline
     */
    public void setByline(String byline) {
        this.byline = byline;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Gets published date.
     *
     * @return the published date
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * Sets published date.
     *
     * @param publishedDate the published date
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
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
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets asset id.
     *
     * @return the asset id
     */
    public Long getAssetId() {
        return assetId;
    }

    /**
     * Sets asset id.
     *
     * @param assetId the asset id
     */
    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    /**
     * Gets views.
     *
     * @return the views
     */
    public Integer getViews() {
        return views;
    }

    /**
     * Sets views.
     *
     * @param views the views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public List<NytMostPopularMedia> getMedia() {
        return media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(List<NytMostPopularMedia> media) {
        this.media = media;
    }
}
