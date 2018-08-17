package ltd.kaizo.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Utils.ArticleFormatter;


public class NytViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_news_item_imageview)
    ImageView itemImageview;
    @BindView(R.id.fragment_news_item_content_textview)
    TextView itemContent;
    @BindView(R.id.fragment_news_item_date_textview)
    TextView itemDate;
    @BindView(R.id.fragment_news_item_location_textview)
    TextView itemLocation;

    public NytViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNytTopStories(List<ArticleFormatter> articleFormatterList, int position, RequestManager glide) {

        this.itemContent.setText(articleFormatterList.get(position).getArticleTitle());
        this.itemDate.setText(articleFormatterList.get(position).getArticlePublishingDate());
        if (articleFormatterList.get(position).getArticleSubSection().equals("")) {
            this.itemLocation.setText(articleFormatterList.get(position).getArticleSection());
        } else {
            this.itemLocation.setText(String.format("%s > %s", articleFormatterList.get(position).getArticleSection(), articleFormatterList.get(position).getArticleSubSection()));
        }
        glide.load(articleFormatterList.get(position).getArticlePictureUrl()).apply(RequestOptions.noAnimation()).into(itemImageview);

    }
}
