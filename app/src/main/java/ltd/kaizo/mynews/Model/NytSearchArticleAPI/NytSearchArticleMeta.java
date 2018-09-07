package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article meta.
 */
public class NytSearchArticleMeta {

    /**
     * The Hits.
     */
    @SerializedName("hits")
    @Expose
    private Integer hits;
    /**
     * The Offset.
     */
    @SerializedName("offset")
    @Expose
    private Integer offset;
    /**
     * The Time.
     */
    @SerializedName("time")
    @Expose
    private Integer time;

    /**
     * Gets hits.
     *
     * @return the hits
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * Sets hits.
     *
     * @param hits the hits
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Sets offset.
     *
     * @param offset the offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

}
