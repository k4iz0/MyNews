package ltd.kaizo.mynews.model.NytMostPopularAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Nyt most popular api data.
 */
public class NytMostPopularAPIData {

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
     * The Num results.
     */
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    /**
     * The Results.
     */
    @SerializedName("results")
    @Expose
    private List<NytMostPopularResult> results = null;

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
     * Gets num results.
     *
     * @return the num results
     */
    public Integer getNumResults() {
        return numResults;
    }

    /**
     * Sets num results.
     *
     * @param numResults the num results
     */
    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<NytMostPopularResult> getResults() {
        return results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(List<NytMostPopularResult> results) {
        this.results = results;
    }

}
