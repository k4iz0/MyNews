package ltd.kaizo.mynews.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesResult;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Utils.ArticleFormatter;
import ltd.kaizo.mynews.Utils.NytArticleConverter;

public class NytAdapter extends RecyclerView.Adapter<NytViewHolder>{
    private  RequestManager glide;
    private List<ArticleFormatter> articleFormatterList;

    public NytAdapter(List<ArticleFormatter> articleFormatterList, RequestManager glide) {
        this.articleFormatterList = articleFormatterList;
        this.glide = glide;
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
        holder.updateWithNytTopStories(this.articleFormatterList,position, this.glide);
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
