package ltd.kaizo.mynews.model.NytSearchArticleAPI;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article byline.
 */
public class NytSearchArticleByline {

    /**
     * The Original.
     */
    @SerializedName("original")
    @Expose
    private String original;
    /**
     * The Person.
     */
    @SerializedName("person")
    @Expose
    private List<NytSearchArticlePerson> person = null;
    /**
     * The Organization.
     */
    @SerializedName("organization")
    @Expose
    private Object organization;

    /**
     * Gets original.
     *
     * @return the original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * Sets original.
     *
     * @param original the original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public List<NytSearchArticlePerson> getPerson() {
        return person;
    }

    /**
     * Sets person.
     *
     * @param person the person
     */
    public void setPerson(List<NytSearchArticlePerson> person) {
        this.person = person;
    }

    /**
     * Gets organization.
     *
     * @return the organization
     */
    public Object getOrganization() {
        return organization;
    }

    /**
     * Sets organization.
     *
     * @param organization the organization
     */
    public void setOrganization(Object organization) {
        this.organization = organization;
    }

}
