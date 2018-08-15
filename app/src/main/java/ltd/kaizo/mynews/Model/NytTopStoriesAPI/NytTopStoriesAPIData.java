package ltd.kaizo.mynews.Model.NytTopStoriesAPI;

public class NytTopStoriesAPIData {
    private NytTopStoriesResult[] results;

    private String status;

    private String num_results;

    private String last_updated;

    private String copyright;

    private String section;

    public NytTopStoriesResult[] getResults ()
    {
        return results;
    }

    public void setResults (NytTopStoriesResult[] results)
    {
        this.results = results;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getNum_results ()
    {
        return num_results;
    }

    public void setNum_results (String num_results)
    {
        this.num_results = num_results;
    }

    public String getLast_updated ()
    {
        return last_updated;
    }

    public void setLast_updated (String last_updated)
    {
        this.last_updated = last_updated;
    }

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getSection ()
    {
        return section;
    }

    public void setSection (String section)
    {
        this.section = section;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", status = "+status+", num_results = "+num_results+", last_updated = "+last_updated+", copyright = "+copyright+", section = "+section+"]";
    }

}
