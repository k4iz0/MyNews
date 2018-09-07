package ltd.kaizo.mynews.Model.NytSearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Nyt search article headline.
 */
public class NytSearchArticleHeadline {

    /**
     * The Main.
     */
    @SerializedName("main")
    @Expose
    private String main;
    /**
     * The Kicker.
     */
    @SerializedName("kicker")
    @Expose
    private String kicker;
    /**
     * The Content kicker.
     */
    @SerializedName("content_kicker")
    @Expose
    private Object contentKicker;
    /**
     * The Print headline.
     */
    @SerializedName("print_headline")
    @Expose
    private Object printHeadline;
    /**
     * The Name.
     */
    @SerializedName("name")
    @Expose
    private Object name;
    /**
     * The Seo.
     */
    @SerializedName("seo")
    @Expose
    private Object seo;
    /**
     * The Sub.
     */
    @SerializedName("sub")
    @Expose
    private Object sub;

    /**
     * Gets main.
     *
     * @return the main
     */
    public String getMain() {
        return main;
    }

    /**
     * Sets main.
     *
     * @param main the main
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * Gets kicker.
     *
     * @return the kicker
     */
    public String getKicker() {
        return kicker;
    }

    /**
     * Sets kicker.
     *
     * @param kicker the kicker
     */
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    /**
     * Gets content kicker.
     *
     * @return the content kicker
     */
    public Object getContentKicker() {
        return contentKicker;
    }

    /**
     * Sets content kicker.
     *
     * @param contentKicker the content kicker
     */
    public void setContentKicker(Object contentKicker) {
        this.contentKicker = contentKicker;
    }

    /**
     * Gets print headline.
     *
     * @return the print headline
     */
    public Object getPrintHeadline() {
        return printHeadline;
    }

    /**
     * Sets print headline.
     *
     * @param printHeadline the print headline
     */
    public void setPrintHeadline(Object printHeadline) {
        this.printHeadline = printHeadline;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Object getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(Object name) {
        this.name = name;
    }

    /**
     * Gets seo.
     *
     * @return the seo
     */
    public Object getSeo() {
        return seo;
    }

    /**
     * Sets seo.
     *
     * @param seo the seo
     */
    public void setSeo(Object seo) {
        this.seo = seo;
    }

    /**
     * Gets sub.
     *
     * @return the sub
     */
    public Object getSub() {
        return sub;
    }

    /**
     * Sets sub.
     *
     * @param sub the sub
     */
    public void setSub(Object sub) {
        this.sub = sub;
    }

}
