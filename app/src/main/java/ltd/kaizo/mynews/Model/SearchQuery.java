package ltd.kaizo.mynews.Model;

/**
 * The type Search query.
 */
public class SearchQuery {
    /**
     * The Query terms.
     */
    private String queryTerms;
    /**
     * The Begin date.
     */
    private String BeginDate;
    /**
     * The End date.
     */
    private String EndDate;
    /**
     * The Query fields.
     */
    private String queryFields;

    /**
     * Instantiates a new Search query.
     */
    public SearchQuery() {
        this.queryTerms = "";
        this.BeginDate = null;
        this.EndDate = null;
        this.queryFields = "";
    }

    /**
     * Gets query terms.
     *
     * @return the query terms
     */
    public String getQueryTerms() {
        return queryTerms;
    }

    /**
     * Sets query terms.
     *
     * @param queryTerms the query terms
     */
    public void setQueryTerms(String queryTerms) {
        this.queryTerms += queryTerms;
    }

    /**
     * Gets begin date.
     *
     * @return the begin date
     */
    public String getBeginDate() {
        return BeginDate;
    }

    /**
     * Sets begin date.
     *
     * @param beginDate the begin date
     */
    public void setBeginDate(String beginDate) {
        if (!beginDate.equals("")) {
        BeginDate = formatDate(beginDate);
        }
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEndDate() {
        return EndDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(String endDate) {
        if (!endDate.equals("")) {
        EndDate = formatDate(endDate);
        }
    }

    /**
     * Gets query fields.
     *
     * @return the query fields
     */
    public String getQueryFields() {
        return queryFields;
    }

    /**
     * Sets query fields.
     *
     * @param queryFields the query fields
     */
    public void setQueryFields(String queryFields) {
        if (this.queryFields.equals("")) {
            this.queryFields = queryFields;
        } else {
            this.queryFields += "," + queryFields;
        }
    }

    /**
     * Format date string.
     *
     * @param date the date
     * @return the string
     */
    private String formatDate(String date) {
        String[] dateSplit = date.split("/");
        return dateSplit[2] + dateSplit[1] + dateSplit[0];
    }
}
