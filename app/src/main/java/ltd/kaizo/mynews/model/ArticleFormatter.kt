package ltd.kaizo.mynews.model

import ltd.kaizo.mynews.utils.Utils.convertDate

/**
 * The type Article formatter.
 */
data class ArticleFormatter(var articleTitle: String?,
                            val articleUrl: String,
                            var articlePictureUrl: String?,
                            var articleSection: String?,
                            var articleSubSection: String?,
                            var articlePublishingDate: String){
    init {
        articlePublishingDate = convertDate(articlePublishingDate)
    }
}