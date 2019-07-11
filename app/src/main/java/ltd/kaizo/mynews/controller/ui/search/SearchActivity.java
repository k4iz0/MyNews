package ltd.kaizo.mynews.controller.ui.search;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.controller.ui.base.BaseFragment;
import ltd.kaizo.mynews.controller.ui.search.SearchFragment;
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
        BaseFragment searchFragment = SearchFragment.Companion.newInstance(20);
        if (searchFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_Framelayout, searchFragment)
                    .commit();
        }

    }

}