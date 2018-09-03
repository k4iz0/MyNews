package ltd.kaizo.mynews.Model;

public class SearchQuery {
    private String queryTerms;
    private String BeginDate;
    private String EndDate;
    private String queryFields;

    public SearchQuery() {
        this.queryTerms = "";
        this.BeginDate = null;
        this.EndDate = null;
        this.queryFields = "";
    }

    public String getQueryTerms() {
        return queryTerms;
    }

    public void setQueryTerms(String queryTerms) {
        this.queryTerms += queryTerms;
    }

    public String getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(String beginDate) {
        if (!beginDate.equals("")) {
        BeginDate = formatDate(beginDate);
        }
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        if (!endDate.equals("")) {
        EndDate = formatDate(endDate);
        }
    }

    public String getQueryFields() {
        return queryFields;
    }

    public void setQueryFields(String queryFields) {
        if (this.queryFields.equals("")) {
            this.queryFields = queryFields;
        } else {
            this.queryFields += "," + queryFields;
        }
    }
    private String formatDate(String date) {
        String[] dateSplit = date.split("/");
        return dateSplit[2] + dateSplit[1] + dateSplit[0];
    }
}
