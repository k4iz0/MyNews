package ltd.kaizo.mynews.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.Controller.Fragments.BaseFragment;
import ltd.kaizo.mynews.Controller.Fragments.SearchFragment;
import ltd.kaizo.mynews.R;

/**
 * The type Search activity.
 */
public class SearchActivity extends AppCompatActivity {
    /**
     * The Toolbar.
     */
    @BindView(R.id.activity_search_toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        this.configureAndShowSearchFragment();
        this.configureToolbar();
    }

    /**
     * Configure toolbar.
     */
    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }

    /**
     * Configure and show search fragment.
     */
    protected void configureAndShowSearchFragment() {
        BaseFragment searchFragment = SearchFragment.newInstance(20);
        if (searchFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_Framelayout, searchFragment)
                    .commit();
        }

    }

}