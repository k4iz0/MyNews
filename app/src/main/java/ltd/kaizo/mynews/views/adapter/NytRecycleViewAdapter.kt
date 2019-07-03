package ltd.kaizo.mynews.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ltd.kaizo.mynews.Model.ArticleFormatter
import ltd.kaizo.mynews.R

/**
 * The type Nyt recycle view adapter.
 */
class NytRecycleViewAdapter(private val articleFormatterList: List<ArticleFormatter>?,
                            private val glide: RequestManager,
                            private val clickListener: (ArticleFormatter) -> Unit) : RecyclerView.Adapter<NytRecycleViewAdapter.NytViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NytViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.news_fragment_item, parent, false)
        return NytViewHolder(view)
    }

    override fun onBindViewHolder(holder: NytViewHolder, position: Int) {
        holder.updateWithNytArticles(this.articleFormatterList!!, position, this.glide)
    }

    override fun getItemCount(): Int {
        return if (this.articleFormatterList != null) this.articleFormatterList.size else 0
    }

    inner class NytViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemContent = itemView.findViewById<TextView>(R.id.fragment_news_item_content_textview)!!
        private val itemDate = itemView.findViewById<TextView>(R.id.fragment_news_item_date_textview)!!
        private val itemLocation = itemView.findViewById<TextView>(R.id.fragment_news_item_location_textview)!!
        private val itemImageView = itemView.findViewById<ImageView>(R.id.fragment_news_item_imageview)!!
        private val itemConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.fragment_news_item_layout)!!

        fun updateWithNytArticles(articleFormatterList: List<ArticleFormatter>, position: Int, glide: RequestManager) {
            val article = articleFormatterList[position]
            itemContent.text = article.articleTitle
            itemDate.text = article.articlePublishingDate
            if (article.articleSubSection == "") {
                itemLocation.text = article.articleSection
            } else {
                itemLocation.text = String.format("%s > %s", article.articleSection, article.articleSubSection)
            }
            glide.load(article.articlePictureUrl).apply(RequestOptions.errorOf(R.drawable.nyt).fitCenter()).apply(RequestOptions.bitmapTransform(RoundedCorners(15))).into(itemImageView!!)
            this.itemConstraintLayout.setOnClickListener { clickListener(article) }
        }

    }
}
