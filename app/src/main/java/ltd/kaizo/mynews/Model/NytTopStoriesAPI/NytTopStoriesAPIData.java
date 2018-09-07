package ltd.kaizo.mynews.Model.NytTopStoriesAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Nyt top stories api data.
 */
public class NytTopStoriesAPIData {
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
     * The Section.
     */
    @SerializedName("section")
    @Expose
    private String section;
    /**
     * The Last updated.
     */
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
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
    private List<NytTopStoriesResult> results = null;

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
     * Gets section.
     *
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Sets section.
     *
     * @param section the section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * Gets last updated.
     *
     * @return the last updated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets last updated.
     *
     * @param lastUpdated the last updated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
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
    public List<NytTopStoriesResult> getResults() {
        return results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(List<NytTopStoriesResult> results) {
        this.results = results;
    }
}
