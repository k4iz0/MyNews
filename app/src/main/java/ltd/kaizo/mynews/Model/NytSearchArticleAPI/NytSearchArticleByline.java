package ltd.kaizo.mynews.Model.NytSearchArticleAPI;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NytSearchArticleByline {

    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("person")
    @Expose
    private List<NytSearchArticlePerson> person = null;
    @SerializedName("organization")
    @Expose
    private Object organization;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public List<NytSearchArticlePerson> getPerson() {
        return person;
    }

    public void setPerson(List<NytSearchArticlePerson> person) {
        this.person = person;
    }

    public Object getOrganization() {
        return organization;
    }

    public void setOrganization(Object organization) {
        this.organization = organization;
    }

}
