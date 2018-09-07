package ltd.kaizo.mynews.Model.NytMostPopularAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Nyt most popular media metadata.
 */
public class NytMostPopularMediaMetadata {
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

}
