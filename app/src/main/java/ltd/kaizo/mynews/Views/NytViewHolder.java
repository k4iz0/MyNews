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
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesResult;
import ltd.kaizo.mynews.R;


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

    public void updateWithNytTopStories(List<NytTopStoriesResult> nytTopStoriesAPIData, int position, RequestManager glide) {
        this.itemContent.setText(nytTopStoriesAPIData.get(position).getTitle());
        this.itemDate.setText(nytTopStoriesAPIData.get(position).getPublished_date());
        this.itemLocation.setText(nytTopStoriesAPIData.get(position).getSection());
        glide.load(nytTopStoriesAPIData.get(position).getUrl()).apply(RequestOptions.noAnimation()).into(itemImageview);

    }
}
