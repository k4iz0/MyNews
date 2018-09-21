package ltd.kaizo.mynews.Views.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;

import java.util.List;

import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Model.ArticleFormatter;
import ltd.kaizo.mynews.Views.NytViewHolder;

/**
 * The type Nyt recycle view adapter.
 */
public class NytRecycleViewAdapter extends RecyclerView.Adapter<NytViewHolder>{
    /**
     * The interface Listener.
     */
    public interface Listener {
        /**
         * On click get url string.
         *
         * @param position the position
         * @return the string
         */
        String onClickGetUrl(int position);
    }

    /**
     * The Glide.
     */
    private  RequestManager glide;
    /**
     * The Article formatter list.
     */
    private List<ArticleFormatter> articleFormatterList;
    /**
     * The Callback.
     */
    private final Listener callback;

    /**
     * Instantiates a new Nyt recycle view adapter.
     *
     * @param articleFormatterList the article formatter list
     * @param glide                the glide
     * @param callback             the callback
     */
    public NytRecycleViewAdapter(List<ArticleFormatter> articleFormatterList, RequestManager glide, Listener callback) {
        this.articleFormatterList = articleFormatterList;
        this.glide = glide;
        this.callback = callback;
    }

    @NonNull
    @Override
    public NytViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_fragment_item,parent, false);
        return new NytViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NytViewHolder holder, int position) {
        holder.updateWithNytArticles(this.articleFormatterList,position, this.glide, this.callback);
    }

    @Override
    public int getItemCount() {
        if (this.articleFormatterList != null) {
            return this.articleFormatterList.size();

        } else {
        return 0;

        }
    }
}
