package ltd.kaizo.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;
import ltd.kaizo.mynews.R;

public class FirstFragment extends BaseFragment {

    private static final String KEY_POSITION="position";
    @BindView(R.id.fragment_first_title)
    TextView title;
    @BindView(R.id.fragment_page_rootview)
    LinearLayout fragmentFirstLayout;

    public FirstFragment() {

    }



    public static BaseFragment newInstance() {

       return  new FirstFragment();

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
        title.setText("page ");
    }




}
