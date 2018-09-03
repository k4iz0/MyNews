package ltd.kaizo.mynews.Views;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.Adapter.NytRecycleViewAdapter;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Model.ArticleFormatter;


public class NytViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.fragment_news_item_imageview)
    ImageView itemImageview;
    @BindView(R.id.fragment_news_item_content_textview)
    TextView itemContent;
    @BindView(R.id.fragment_news_item_date_textview)
    TextView itemDate;
    @BindView(R.id.fragment_news_item_location_textview)
    TextView itemLocation;
    @BindView(R.id.fragment_news_item_layout)
    ConstraintLayout itemConstraintLayout;
    private WeakReference<NytRecycleViewAdapter.Listener> callbackWeakRef;
    public NytViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNytArticles(List<ArticleFormatter> articleFormatterList, int position, RequestManager glide, NytRecycleViewAdapter.Listener callback) {

        this.itemContent.setText(articleFormatterList.get(position).getArticleTitle());
        this.itemDate.setText(articleFormatterList.get(position).getArticlePublishingDate());
        if (articleFormatterList.get(position).getArticleSubSection().equals("")) {
            this.itemLocation.setText(articleFormatterList.get(position).getArticleSection());
        } else {
            this.itemLocation.setText(String.format("%s > %s", articleFormatterList.get(position).getArticleSection(), articleFormatterList.get(position).getArticleSubSection()));
        }
        glide.load(articleFormatterList.get(position).getArticlePictureUrl()).apply(RequestOptions.noAnimation()).into(itemImageview);
        this.itemConstraintLayout.setOnClickListener(this);
        this.callbackWeakRef = new WeakReference<>(callback);

    }

    @Override
    public void onClick(View v) {
        NytRecycleViewAdapter.Listener callback = callbackWeakRef.get();
        if(callback != null) callback.OnClickGetUrl(getAdapterPosition());
    }
}
