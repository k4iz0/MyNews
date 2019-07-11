package ltd.kaizo.mynews.model.NytSearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article multimedia.
 */
public class NytSearchArticleMultimedia {

    /**
     * The Rank.
     */
    @SerializedName("rank")
    @Expose
    private Integer rank;
    /**
     * The Subtype.
     */
    @SerializedName("subtype")
    @Expose
    private String subtype;
    /**
     * The Caption.
     */
    @SerializedName("caption")
    @Expose
    private Object caption;
    /**
     * The Credit.
     */
    @SerializedName("credit")
    @Expose
    private Object credit;
    /**
     * The Type.
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * The Url.
     */
    @SerializedName("url")
    @Expose
    private String url;
    /**
     * The Height.
     */
    @SerializedName("height")
    @Expose
    private Integer height;
    /**
     * The Width.
     */
    @SerializedName("width")
    @Expose
    private Integer width;
    /**
     * The Legacy.
     */
    @SerializedName("legacy")
    @Expose
    private NytSearchArticleLegacy legacy;
    /**
     * The Sub type.
     */
    @SerializedName("subType")
    @Expose
    private String subType;
    /**
     * The Crop name.
     */
    @SerializedName("crop_name")
    @Expose
    private Object cropName;

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * Gets subtype.
     *
     * @return the subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * Sets subtype.
     *
     * @param subtype the subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * Gets caption.
     *
     * @return the caption
     */
    public Object getCaption() {
        return caption;
    }

    /**
     * Sets caption.
     *
     * @param caption the caption
     */
    public void setCaption(Object caption) {
        this.caption = caption;
    }

    /**
     * Gets credit.
     *
     * @return the credit
     */
    public Object getCredit() {
        return credit;
    }

    /**
     * Sets credit.
     *
     * @param credit the credit
     */
    public void setCredit(Object credit) {
        this.credit = credit;
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
     * Gets height.
     *
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Gets nyt search article legacy.
     *
     * @return the nyt search article legacy
     */
    public NytSearchArticleLegacy getNytSearchArticleLegacy() {
        return legacy;
    }

    /**
     * Sets nyt search article legacy.
     *
     * @param legacy the legacy
     */
    public void setNytSearchArticleLegacy(NytSearchArticleLegacy legacy) {
        this.legacy = legacy;
    }

    /**
     * Gets sub type.
     *
     * @return the sub type
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Sets sub type.
     *
     * @param subType the sub type
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }

    /**
     * Gets crop name.
     *
     * @return the crop name
     */
    public Object getCropName() {
        return cropName;
    }

    /**
     * Sets crop name.
     *
     * @param cropName the crop name
     */
    public void setCropName(Object cropName) {
        this.cropName = cropName;
    }

}
