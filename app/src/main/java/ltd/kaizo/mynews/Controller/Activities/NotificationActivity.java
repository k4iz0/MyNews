package ltd.kaizo.mynews.Controller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.Controller.Fragments.BaseFragment;
import ltd.kaizo.mynews.Controller.Fragments.NewsFragment;
import ltd.kaizo.mynews.Controller.Fragments.SearchFragment;
import ltd.kaizo.mynews.R;

import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_POSITION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_SEARCHQUERY_NOTIFICATION;

public class NotificationActivity extends AppCompatActivity {
    @BindView(R.id.activity_notification_toolbar)
    Toolbar toolbar;
    private String searchQueryNotification = "";
    private int keyTag = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        searchQueryNotification = intent.getStringExtra("notificationSearch");
        keyTag = intent.getIntExtra("Key_TAG", 0);
        this.configureToolbar();
        switch (keyTag) {
            case 0:
                this.configureAndShowSearchFragment();
                break;
            case 30:
                this.configureAndShowNewsFragment();
        }

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

    /**
     * Configure and show news fragment.
     */
    private void configureAndShowNewsFragment() {
        BaseFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(saveDataToBundle());
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_notification_Framelayout, newsFragment)
                .addToBackStack(null)
                .commit();
    }

    private Bundle saveDataToBundle() {
        Bundle args = new Bundle();
        args.putString(Key_SEARCHQUERY_NOTIFICATION, this.searchQueryNotification);
        args.putInt(Key_POSITION, 3);
        return args;
    }


}

