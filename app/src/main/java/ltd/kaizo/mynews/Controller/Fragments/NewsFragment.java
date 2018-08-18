package ltd.kaizo.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Utils.ArticleFormatter;
import ltd.kaizo.mynews.Utils.NytArticleConverter;
import ltd.kaizo.mynews.Utils.NytStream;
import ltd.kaizo.mynews.Views.NytAdapter;

public class NewsFragment extends BaseFragment {
    @BindView(R.id.fragment_news_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_page_rootview)
    LinearLayout fragmentNewsLayout;
    private NytAdapter adapter;
    private NytArticleConverter nytArticleConverter;
    private Disposable disposable;
    private List<ArticleFormatter> articleFormatterList;
    private String section;
    public static final String KEY_SECTION = "home";

    public NewsFragment() {

    }


    public static BaseFragment newInstance(String section) {
        NewsFragment frag = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_SECTION, section);
        frag.setArguments(bundle);
        return frag;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.section = getArguments().getString(KEY_SECTION);
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
        this.executeRetrofitHttpRequest();
    }

    private void executeRetrofitHttpRequest() {

        this.disposable = NytStream.streamFetchTopStories(section).subscribeWith(new DisposableObserver<NytTopStoriesAPIData>() {
            @Override
            public void onNext(NytTopStoriesAPIData nytTopStoriesAPIData) {
                nytArticleConverter = new NytArticleConverter(nytTopStoriesAPIData);
                updateUI(nytArticleConverter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void updateDesign() {

    }

    public void configureRecycleView() {
        this.articleFormatterList = new ArrayList<>();
        this.adapter = new NytAdapter(this.articleFormatterList, Glide.with(this));
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void updateUI(NytArticleConverter nytArticleConverter) {
        this.articleFormatterList.addAll(nytArticleConverter.configureArticleListForAdapter());
        adapter.notifyDataSetChanged();
    }

    private void disposeWhenDestroy() {

        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();

    }
}
