package ltd.kaizo.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import ltd.kaizo.mynews.R;
public class NewsFragment extends BaseFragment {
    @BindView(R.id.fragment_news_title)
    TextView title;
    @BindView(R.id.fragment_page_rootview)
    LinearLayout fragmentNewsLayout;
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
    protected int getFragmentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void configureDesign() {

    }

    @Override
    protected void updateDesign() {
        title.setText("page "+position);
    }


}
