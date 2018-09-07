package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article person.
 */
public class NytSearchArticlePerson {

    /**
     * The Firstname.
     */
    @SerializedName("firstname")
    @Expose
    private String firstname;
    /**
     * The Middlename.
     */
    @SerializedName("middlename")
    @Expose
    private Object middlename;
    /**
     * The Lastname.
     */
    @SerializedName("lastname")
    @Expose
    private String lastname;
    /**
     * The Qualifier.
     */
    @SerializedName("qualifier")
    @Expose
    private Object qualifier;
    /**
     * The Title.
     */
    @SerializedName("title")
    @Expose
    private Object title;
    /**
     * The Role.
     */
    @SerializedName("role")
    @Expose
    private String role;
    /**
     * The Organization.
     */
    @SerializedName("organization")
    @Expose
    private String organization;
    /**
     * The Rank.
     */
    @SerializedName("rank")
    @Expose
    private Integer rank;

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets middlename.
     *
     * @return the middlename
     */
    public Object getMiddlename() {
        return middlename;
    }

    /**
     * Sets middlename.
     *
     * @param middlename the middlename
     */
    public void setMiddlename(Object middlename) {
        this.middlename = middlename;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets qualifier.
     *
     * @return the qualifier
     */
    public Object getQualifier() {
        return qualifier;
    }

    /**
     * Sets qualifier.
     *
     * @param qualifier the qualifier
     */
    public void setQualifier(Object qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public Object getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(Object title) {
        this.title = title;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets organization.
     *
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets organization.
     *
     * @param organization the organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
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

}
