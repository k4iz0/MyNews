package ltd.kaizo.mynews.model.NytSearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article keyword.
 */
public class NytSearchArticleKeyword {

    /**
     * The Name.
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * The Value.
     */
    @SerializedName("value")
    @Expose
    private String value;
    /**
     * The Rank.
     */
    @SerializedName("rank")
    @Expose
    private Integer rank;
    /**
     * The Major.
     */
    @SerializedName("major")
    @Expose
    private Object major;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * Gets major.
     *
     * @return the major
     */
    public Object getMajor() {
        return major;
    }

    /**
     * Sets major.
     *
     * @param major the major
     */
    public void setMajor(Object major) {
        this.major = major;
    }

}
