package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NytSearchArticleResponse {

    @SerializedName("docs")
    @Expose
    private List<NytSearchArticleDoc> nytSearchArticleDocs = null;
    @SerializedName("meta")
    @Expose
    private NytSearchArticleMeta meta;

    public List<NytSearchArticleDoc> getNytSearchArticleDocs() {
        return nytSearchArticleDocs;
    }

    public void setNytSearchArticleDocs(List<NytSearchArticleDoc> nytSearchArticleDocs) {
        this.nytSearchArticleDocs = nytSearchArticleDocs;
    }

    public NytSearchArticleMeta getMeta() {
        return meta;
    }

    public void setMeta(NytSearchArticleMeta meta) {
        this.meta = meta;
    }

}
