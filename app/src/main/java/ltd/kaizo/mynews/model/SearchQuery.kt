package ltd.kaizo.mynews.model

import ltd.kaizo.mynews.utils.Utils.formatDate

/**
 * The type Search query.
 */
data class SearchQuery(
        var queryTerms: String = "",
        var beginDate: String? = null,
        var endDate: String? = null,
        var queryFields: String = ""
) {
    init {
        if (this.queryFields != "") queryFields += ",$queryFields"
    }
}
