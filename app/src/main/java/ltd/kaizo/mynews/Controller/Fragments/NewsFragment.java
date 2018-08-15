package ltd.kaizo.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesResult;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Utils.NytArticleConverter;
import ltd.kaizo.mynews.Utils.NytStream;
import ltd.kaizo.mynews.Views.NytAdapter;

public class NewsFragment extends BaseFragment {
    @BindView(R.id.fragment_news_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_page_rootview)
    LinearLayout fragmentNewsLayout;
    private NytAdapter adapter;
    private List<NytTopStoriesResult> NytArticleList;
    private Disposable disposable;

    public NewsFragment() {

    }


    public static BaseFragment newInstance(int position) {
        NewsFragment frag = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        frag.setArguments(bundle);
        return frag;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
        this.disposable = NytStream.streamFetchTopStories().subscribeWith(new DisposableObserver<NytTopStoriesAPIData>() {
            @Override
            public void onNext(NytTopStoriesAPIData nytTopStoriesAPIData) {
                updateUI(Arrays.asList(nytTopStoriesAPIData.getResults()));
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
        this.NytArticleList = new ArrayList<>();
        this.adapter = new NytAdapter(this.NytArticleList, Glide.with(this));
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void updateUI(List<NytTopStoriesResult> nytTopStoriesAPIresult) {
        this.NytArticleList.addAll(nytTopStoriesAPIresult);
        adapter.notifyDataSetChanged();
    }

    private void disposeWhenDestroy() {

        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();

    }
}
