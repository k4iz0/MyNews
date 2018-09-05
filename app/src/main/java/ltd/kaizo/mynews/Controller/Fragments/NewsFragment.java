package ltd.kaizo.mynews.Controller.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import icepick.State;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ltd.kaizo.mynews.Adapter.NytRecycleViewAdapter;
import ltd.kaizo.mynews.Controller.Activities.DetailActivity;
import ltd.kaizo.mynews.Model.ArticleFormatter;
import ltd.kaizo.mynews.Model.NytArticleConverter;
import ltd.kaizo.mynews.Model.NytMostPopularAPI.NytMostPopularAPIData;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.SearchQuery;
import ltd.kaizo.mynews.Model.Utils.ItemClickSupport;
import ltd.kaizo.mynews.Model.Utils.NytStream;
import ltd.kaizo.mynews.R;

import static android.support.constraint.Constraints.TAG;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.KEY_SECTION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_POSITION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_SEARCHQUERY;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.read;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.saveData;

public class NewsFragment extends BaseFragment implements NytRecycleViewAdapter.Listener {
    @BindView(R.id.fragment_news_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_page_rootview)
    LinearLayout fragmentNewsLayout;
    @BindView(R.id.fragment_news_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @State
    String section;
    @State
    int position;
    private NytRecycleViewAdapter adapter;
    private NytArticleConverter nytArticleConverter;
    private Disposable disposable;
    private List<ArticleFormatter> articleFormatterList;
    private SearchQuery searchQuery;
    private Gson gson;
    private String gsonStr = "";

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
        Toasty.Config.getInstance().setInfoColor(getResources().getColor(R.color.blueDark)).apply();
        if (getArguments() != null) {
            this.section = getArguments().getString(KEY_SECTION);
            this.position = getArguments().getInt(Key_POSITION);
            this.gsonStr = getArguments().getString(Key_SEARCHQUERY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
        this.executeHttpRequest();
        this.configureSwipeRefreshLayout();

    }

    private void executeHttpRequest() {
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
            case 3:
                Log.i(TAG, "position 3:  position =" + position);
                gson = new Gson();
                this.searchQuery = gson.fromJson(this.gsonStr, SearchQuery.class);
                Log.i("searchQuery", "term : " + searchQuery.getQueryTerms()
                        + "\n fields : " + searchQuery.getQueryFields()
                        + "\n beginDate : " + searchQuery.getBeginDate()
                        + "\n enDate : " + searchQuery.getEndDate());
                this.executeStreamFetchSearchArticle();
                break;
            default:
                Toasty.info(getContext(), "No article found !", Toast.LENGTH_SHORT).show();
        }
    }

    private void executeStreamFetchSearchArticle() {
        this.disposable = NytStream.streamFetchSearchArticle(
                searchQuery.getQueryTerms(),
                searchQuery.getQueryFields(),
                searchQuery.getBeginDate(),
                searchQuery.getEndDate())
                .subscribeWith(new DisposableObserver<NytSearchArticleApiData>() {
                    @Override
                    public void onNext(NytSearchArticleApiData nytSearchArticleApiData) {
                        if (nytSearchArticleApiData.getNytSearchArticleResponse().getNytSearchArticleDocs().size() > 0) {
                            nytArticleConverter = new NytArticleConverter(nytSearchArticleApiData);
                            updateUI(nytArticleConverter.configureSearchArticleListForAdapter());
                        } else {
                            Toasty.info(getContext(), "No article found !", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("StreamInfo", "search error : " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("StreamInfo", "search complete");
                    }
                });

    }

    private void executeStreamFetchTopStories() {
        this.disposable = NytStream.streamFetchTopStories(section).subscribeWith(new DisposableObserver<NytTopStoriesAPIData>() {
            @Override
            public void onNext(NytTopStoriesAPIData nytTopStoriesAPIData) {
                nytArticleConverter = new NytArticleConverter(nytTopStoriesAPIData);
                Log.i("StreamInfo", "top httpRequest in progress : - status = " + nytTopStoriesAPIData.getStatus() +
                        "\n taille des résultats = " + nytTopStoriesAPIData.getNumResults() +
                        "\n section = " + section);
                updateUI(nytArticleConverter.configureTopStoriesArticleListForAdapter());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("StreamInfo", "top error : " + e);
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
                Log.i("StreamInfo", "MP httpRequest in progress : - status = " + nytMostPopularAPIdata.getStatus() +
                        "\n taille des résultats = " + nytMostPopularAPIdata.getNumResults() +
                        "\n section = " + section);
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
        this.executeStreamFetchTopStories();
        this.executeStreamFetchMostPopularStories();
    }

    public void configureRecycleView() {
        this.articleFormatterList = new ArrayList<>();
        this.adapter = new NytRecycleViewAdapter(this.articleFormatterList, Glide.with(this), this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.news_fragment_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent detailActivity = new Intent(getActivity(), DetailActivity.class);
                        detailActivity.putExtra("articleUrl", OnClickGetUrl(position));
                        startActivity(detailActivity);
                    }
                });
    }

    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.bluePrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequest();
            }
        });
    }

    private void updateUI(List<ArticleFormatter> articleList) {
        swipeRefreshLayout.setRefreshing(false);
        this.articleFormatterList.clear();
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

    public void updateSection(String section) {
        this.section = section;
        saveData(this.position, this.section, this.searchQuery);
        this.executeHttpRequest();
    }

}
