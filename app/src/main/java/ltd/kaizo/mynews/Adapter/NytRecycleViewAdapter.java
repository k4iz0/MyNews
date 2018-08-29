package ltd.kaizo.mynews.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;

import java.util.List;

import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Utils.ArticleFormatter;
import ltd.kaizo.mynews.Views.NytViewHolder;

public class NytRecycleViewAdapter extends RecyclerView.Adapter<NytViewHolder>{
    public interface Listener {
        String OnClickGetUrl(int position);
    }
    private  RequestManager glide;
    private List<ArticleFormatter> articleFormatterList;
    private final Listener callback;

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
