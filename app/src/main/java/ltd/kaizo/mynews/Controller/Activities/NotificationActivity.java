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

public class NotificationActivity extends AppCompatActivity {
    @BindView(R.id.activity_notification_toolbar)
    Toolbar toolbar;
    SearchFragment searchFragment;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureAndShowSearchFragment();

    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }

    protected void configureAndShowSearchFragment() {
        BaseFragment searchFragment = SearchFragment.newInstance(10);
        if (searchFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_notification_Framelayout, searchFragment)
                    .commit();
        }
    }


}

