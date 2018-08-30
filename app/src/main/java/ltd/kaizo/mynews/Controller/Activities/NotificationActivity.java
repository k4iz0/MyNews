package ltd.kaizo.mynews.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.Controller.Fragments.SearchFragment;
import ltd.kaizo.mynews.R;

public class NotificationActivity extends AppCompatActivity {
    @BindView(R.id.activity_notification_toolbar)
    Toolbar toolbar;
    SearchFragment searchFragment;
    @BindView(R.id.fragment_search_button)
    Button searchButton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureAndShowSearchFragment();
        searchButton.setVisibility(View.GONE);
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }

    protected void configureAndShowSearchFragment() {

        searchFragment = (SearchFragment) this.getSupportFragmentManager().findFragmentById(R.id.activity_notification_Framelayout);

        if (searchFragment == null) {
            searchFragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_notification_Framelayout, searchFragment)
                    .commit();
        }


    }
}
