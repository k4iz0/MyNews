package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article api data.
 */
public class NytSearchArticleApiData {


    /**
     * The Status.
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * The Copyright.
     */
    @SerializedName("copyright")
    @Expose
    private String copyright;
    /**
     * The Response.
     */
    @SerializedName("response")
    @Expose
    private NytSearchArticleResponse response;

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
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
     * Gets nyt search article response.
     *
     * @return the nyt search article response
     */
    public NytSearchArticleResponse getNytSearchArticleResponse() {
        return response;
    }

    /**
     * Sets nyt search article response.
     *
     * @param response the response
     */
    public void setNytSearchArticleResponse(NytSearchArticleResponse response) {
        this.response = response;
    }

}
