package ltd.kaizo.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import ltd.kaizo.mynews.R;
//TODO changer nom fragment
public class FirstFragment extends BaseFragment {
    @BindView(R.id.fragment_first_title)
    TextView title;
    @BindView(R.id.fragment_page_rootview)
    LinearLayout fragmentFirstLayout;
    public FirstFragment() {

    }


    public static BaseFragment newInstance(int position) {
        FirstFragment frag = new FirstFragment();
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
        return R.layout.fragment_first;
    }

    @Override
    protected void configureDesign() {

    }

    @Override
    protected void updateDesign() {
        title.setText("page "+position);
    }


}
