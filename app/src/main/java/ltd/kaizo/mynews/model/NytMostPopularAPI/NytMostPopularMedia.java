package ltd.kaizo.mynews.model.NytMostPopularAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Nyt most popular media.
 */
public class NytMostPopularMedia {

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
     * The Approved for syndication.
     */
    @SerializedName("approved_for_syndication")
    @Expose
    private Integer approvedForSyndication;
    /**
     * The Media metadata.
     */
    @SerializedName("media-metadata")
    @Expose
    private List<NytMostPopularMediaMetadata> mediaMetadata = null;

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

    /**
     * Gets approved for syndication.
     *
     * @return the approved for syndication
     */
    public Integer getApprovedForSyndication() {
        return approvedForSyndication;
    }

    /**
     * Sets approved for syndication.
     *
     * @param approvedForSyndication the approved for syndication
     */
    public void setApprovedForSyndication(Integer approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    /**
     * Gets media metadata.
     *
     * @return the media metadata
     */
    public List<NytMostPopularMediaMetadata> getMediaMetadata() {
        return mediaMetadata;
    }

    /**
     * Sets media metadata.
     *
     * @param mediaMetadata the media metadata
     */
    public void setMediaMetadata(List<NytMostPopularMediaMetadata> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }
}
