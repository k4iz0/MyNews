package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article response.
 */
public class NytSearchArticleResponse {

    /**
     * The Nyt search article docs.
     */
    @SerializedName("docs")
    @Expose
    private List<NytSearchArticleDoc> nytSearchArticleDocs = null;
    /**
     * The Meta.
     */
    @SerializedName("meta")
    @Expose
    private NytSearchArticleMeta meta;

    /**
     * Gets nyt search article docs.
     *
     * @return the nyt search article docs
     */
    public List<NytSearchArticleDoc> getNytSearchArticleDocs() {
        return nytSearchArticleDocs;
    }

    /**
     * Sets nyt search article docs.
     *
     * @param nytSearchArticleDocs the nyt search article docs
     */
    public void setNytSearchArticleDocs(List<NytSearchArticleDoc> nytSearchArticleDocs) {
        this.nytSearchArticleDocs = nytSearchArticleDocs;
    }

    /**
     * Gets meta.
     *
     * @return the meta
     */
    public NytSearchArticleMeta getMeta() {
        return meta;
    }

    /**
     * Sets meta.
     *
     * @param meta the meta
     */
    public void setMeta(NytSearchArticleMeta meta) {
        this.meta = meta;
    }

}
