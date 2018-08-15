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

public class NytAdapter extends RecyclerView.Adapter<NytViewHolder>{
    private  RequestManager glide;
    private List<NytTopStoriesResult> result;

    public NytAdapter(List<NytTopStoriesResult> nytTopStoriesAPIDataList, RequestManager glide) {
        this.result = nytTopStoriesAPIDataList;
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
        holder.updateWithNytTopStories(this.result,position, this.glide);
    }

    @Override
    public int getItemCount() {
        return this.result.size();
    }
}
