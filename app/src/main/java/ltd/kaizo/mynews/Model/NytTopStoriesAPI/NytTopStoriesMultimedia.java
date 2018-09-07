package ltd.kaizo.mynews.Model.NytTopStoriesAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt top stories multimedia.
 */
public class NytTopStoriesMultimedia {
    /**
     * The Url.
     */
    @SerializedName("url")
    @Expose
    private String url;
    /**
     * The Format.
     */
    @SerializedName("format")
    @Expose
    private String format;
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
     * The Type.
     */
    @SerializedName("type")
    @Expose
    private String type;
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
    private String caption;
    /**
     * The Copyright.
     */
    @SerializedName("copyright")
    @Expose
    private String copyright;

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
     * Gets format.
     *
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets format.
     *
     * @param format the format
     */
    public void setFormat(String format) {
        this.format = format;
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
    public String getCaption() {
        return caption;
    }

    /**
     * Sets caption.
     *
     * @param caption the caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Gets copyright.
     *
     * @return the copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets copyright.
     *
     * @param copyright the copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
