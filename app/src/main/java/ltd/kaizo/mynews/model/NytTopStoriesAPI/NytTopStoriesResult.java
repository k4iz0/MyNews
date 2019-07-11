package ltd.kaizo.mynews.model.NytTopStoriesAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Nyt top stories result.
 */
public class NytTopStoriesResult {


    /**
     * The Section.
     */
    @SerializedName("section")
    @Expose
    private String section;
    /**
     * The Subsection.
     */
    @SerializedName("subsection")
    @Expose
    private String subsection;
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
     * The Url.
     */
    @SerializedName("url")
    @Expose
    private String url;
    /**
     * The Byline.
     */
    @SerializedName("byline")
    @Expose
    private String byline;
    /**
     * The Item type.
     */
    @SerializedName("item_type")
    @Expose
    private String itemType;
    /**
     * The Updated date.
     */
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    /**
     * The Created date.
     */
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    /**
     * The Published date.
     */
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    /**
     * The Material type facet.
     */
    @SerializedName("material_type_facet")
    @Expose
    private String materialTypeFacet;
    /**
     * The Kicker.
     */
    @SerializedName("kicker")
    @Expose
    private String kicker;
    /**
     * The Des facet.
     */
    @SerializedName("des_facet")
    @Expose
    private List<String> desFacet = null;
    /**
     * The Org facet.
     */
    @SerializedName("org_facet")
    @Expose
    private List<Object> orgFacet = null;
    /**
     * The Per facet.
     */
    @SerializedName("per_facet")
    @Expose
    private List<String> perFacet = null;
    /**
     * The Geo facet.
     */
    @SerializedName("geo_facet")
    @Expose
    private List<Object> geoFacet = null;
    /**
     * The Multimedia.
     */
    @SerializedName("multimedia")
    @Expose
    private List<NytTopStoriesMultimedia> multimedia = null;
    /**
     * The Short url.
     */
    @SerializedName("short_url")
    @Expose
    private String shortUrl;

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
     * Gets subsection.
     *
     * @return the subsection
     */
    public String getSubsection() {
        return subsection;
    }

    /**
     * Sets subsection.
     *
     * @param subsection the subsection
     */
    public void setSubsection(String subsection) {
        this.subsection = subsection;
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
     * Gets item type.
     *
     * @return the item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets item type.
     *
     * @param itemType the item type
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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
     * Gets material type facet.
     *
     * @return the material type facet
     */
    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    /**
     * Sets material type facet.
     *
     * @param materialTypeFacet the material type facet
     */
    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    /**
     * Gets kicker.
     *
     * @return the kicker
     */
    public String getKicker() {
        return kicker;
    }

    /**
     * Sets kicker.
     *
     * @param kicker the kicker
     */
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    /**
     * Gets des facet.
     *
     * @return the des facet
     */
    public List<String> getDesFacet() {
        return desFacet;
    }

    /**
     * Sets des facet.
     *
     * @param desFacet the des facet
     */
    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    /**
     * Gets org facet.
     *
     * @return the org facet
     */
    public List<Object> getOrgFacet() {
        return orgFacet;
    }

    /**
     * Sets org facet.
     *
     * @param orgFacet the org facet
     */
    public void setOrgFacet(List<Object> orgFacet) {
        this.orgFacet = orgFacet;
    }

    /**
     * Gets per facet.
     *
     * @return the per facet
     */
    public List<String> getPerFacet() {
        return perFacet;
    }

    /**
     * Sets per facet.
     *
     * @param perFacet the per facet
     */
    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    /**
     * Gets geo facet.
     *
     * @return the geo facet
     */
    public List<Object> getGeoFacet() {
        return geoFacet;
    }

    /**
     * Sets geo facet.
     *
     * @param geoFacet the geo facet
     */
    public void setGeoFacet(List<Object> geoFacet) {
        this.geoFacet = geoFacet;
    }

    /**
     * Gets multimedia.
     *
     * @return the multimedia
     */
    public List<NytTopStoriesMultimedia> getMultimedia() {
        return multimedia;
    }

    /**
     * Sets multimedia.
     *
     * @param multimedia the multimedia
     */
    public void setMultimedia(List<NytTopStoriesMultimedia> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * Gets short url.
     *
     * @return the short url
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Sets short url.
     *
     * @param shortUrl the short url
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    }

