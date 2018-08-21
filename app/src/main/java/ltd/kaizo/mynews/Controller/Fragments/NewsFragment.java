package ltd.kaizo.mynews.Controller.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ltd.kaizo.mynews.Controller.Activities.DetailActivity;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Utils.ArticleFormatter;
import ltd.kaizo.mynews.Utils.ItemClickSupport;
import ltd.kaizo.mynews.Utils.NytArticleConverter;
import ltd.kaizo.mynews.Utils.NytStream;
import ltd.kaizo.mynews.Views.NytAdapter;

public class NewsFragment extends BaseFragment implements NytAdapter.Listener{
    public static final String KEY_SECTION = "home";
    public static final String Key_POSITION = "0";
    @BindView(R.id.fragment_news_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_page_rootview)
    LinearLayout fragmentNewsLayout;
    private NytAdapter adapter;
    private NytArticleConverter nytArticleConverter;
    private Disposable disposable;
    private List<ArticleFormatter> articleFormatterList;
    private String section;
    private int position;

    public NewsFragment() {

    }


    public static BaseFragment newInstance(int position, String section) {
        NewsFragment frag = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_SECTION, section);
        bundle.putInt(Key_POSITION, position);
        frag.setArguments(bundle);
        return frag;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.section = getArguments().getString(KEY_SECTION);
            this.position = getArguments().getInt(Key_POSITION);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void configureDesign() {
        this.configureRecycleView();
        this.configureOnClickRecyclerView();
        switch (this.position) {
            case 0:
                this.executeStreamFetchTopStories();
                break;
            case 1:
                this.executeStreamFetchMostPopularStories();

                break;
            case 2:
                this.executeStreamFetchTopStories();
                break;
            default:
                this.executeStreamFetchTopStories();
        }

    }

    private void executeStreamFetchTopStories() {

        this.disposable = NytStream.streamFetchTopStories(section).subscribeWith(new DisposableObserver<NytTopStoriesAPIData>() {
            @Override
            public void onNext(NytTopStoriesAPIData nytTopStoriesAPIData) {
                nytArticleConverter = new NytArticleConverter(nytTopStoriesAPIData);
                updateUI(nytArticleConverter.configureTopStoriesArticleListForAdapter());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("StreamInfo", "top error");
            }

            @Override
            public void onComplete() {
                Log.i("StreamInfo", "top complete");
            }
        });

    }

    private void executeStreamFetchMostPopularStories() {

        this.disposable = NytStream.streamFetchMostPopularStories(section).subscribeWith(new DisposableObserver<NytMostPopularAPIData>() {
            @Override
            public void onNext(NytMostPopularAPIData nytMostPopularAPIdata) {
                Log.i("StreamInfo", "MP httpRequest in progress : " + nytMostPopularAPIdata.getStatus());
                nytArticleConverter = new NytArticleConverter(nytMostPopularAPIdata);
                updateUI(nytArticleConverter.configureMostPopularArticleListForAdapter());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("StreamInfo", "MP error : " + e);

            }

            @Override
            public void onComplete() {
                Log.i("StreamInfo", "MP complete");
            }
        });

    }

    @Override
    protected void updateDesign() {

    }

    public void configureRecycleView() {
        this.articleFormatterList = new ArrayList<>();
        this.adapter = new NytAdapter(this.articleFormatterList, Glide.with(this),this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.news_fragment_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent detailActivity = new Intent(getActivity(), DetailActivity.class);
                        detailActivity.putExtra("articleUrl",OnClickGetUrl(position));
                        startActivity(detailActivity);
                    }
                });
    }

    private void updateUI(List<ArticleFormatter> articleList) {
        this.articleFormatterList.addAll(articleList);
        adapter.notifyDataSetChanged();
    }

    private void disposeWhenDestroy() {

        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();

    }

    @Override
    public String OnClickGetUrl(int position) {
        return this.articleFormatterList.get(position).getArticleUrl();
    }
}
