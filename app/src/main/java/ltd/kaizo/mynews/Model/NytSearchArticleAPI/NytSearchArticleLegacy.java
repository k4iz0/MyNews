package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article legacy.
 */
public class NytSearchArticleLegacy {

    /**
     * The Thumbnailheight.
     */
    @SerializedName("thumbnailheight")
    @Expose
    private String thumbnailheight;
    /**
     * The Thumbnail.
     */
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    /**
     * The Thumbnailwidth.
     */
    @SerializedName("thumbnailwidth")
    @Expose
    private String thumbnailwidth;

    /**
     * Gets thumbnailheight.
     *
     * @return the thumbnailheight
     */
    public String getThumbnailheight() {
        return thumbnailheight;
    }

    /**
     * Sets thumbnailheight.
     *
     * @param thumbnailheight the thumbnailheight
     */
    public void setThumbnailheight(String thumbnailheight) {
        this.thumbnailheight = thumbnailheight;
    }

    /**
     * Gets thumbnail.
     *
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Sets thumbnail.
     *
     * @param thumbnail the thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Gets thumbnailwidth.
     *
     * @return the thumbnailwidth
     */
    public String getThumbnailwidth() {
        return thumbnailwidth;
    }

    /**
     * Sets thumbnailwidth.
     *
     * @param thumbnailwidth the thumbnailwidth
     */
    public void setThumbnailwidth(String thumbnailwidth) {
        this.thumbnailwidth = thumbnailwidth;
    }

}
