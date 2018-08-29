package ltd.kaizo.mynews.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.Controller.Fragments.SearchFragment;
import ltd.kaizo.mynews.R;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.activity_search_toolbar)
    Toolbar toolbar;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        this.configureAndShowSearchFragment();
        this.configureToolbar();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }


    protected void configureAndShowSearchFragment() {

        searchFragment = (SearchFragment) this.getSupportFragmentManager().findFragmentById(R.id.activity_search_Framelayout);

        if (searchFragment == null) {
            searchFragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_Framelayout, searchFragment)
                    .commit();
        }

    }

}