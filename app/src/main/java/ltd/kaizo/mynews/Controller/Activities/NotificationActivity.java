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
import ltd.kaizo.mynews.Model.Utils.DataRecordManager;
import ltd.kaizo.mynews.R;

import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.KEY_POSITION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.KEY_SEARCHQUERY_NOTIFICATION;

/**
 * The type Notification activity.
 */
public class NotificationActivity extends AppCompatActivity {
    /**
     * The Toolbar.
     */
    @BindView(R.id.activity_notification_toolbar)
    Toolbar toolbar;
    /**
     * The Search query notification.
     */
    private String searchQueryNotification = "";
    /**
     * The Key tag.
     */
    private int keyTag = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //bind view
        ButterKnife.bind(this);
        DataRecordManager.init(getApplicationContext());
        Intent intent = getIntent();
        //getting data from intent
        searchQueryNotification = intent.getStringExtra(getString(R.string.notificationSearchExtra));
        keyTag = intent.getIntExtra(getString(R.string.Key_TAGExtra), 0);
        this.configureToolbar();
        this.showFragment(keyTag);

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
     * Show fragment.
     *
     * @param keyTag the key tag
     */
    private void showFragment(int keyTag) {
        if (keyTag == 30) {
            this.configureAndShowNewsFragment();
        } else {
            this.configureAndShowSearchFragment();
        }

    }

    /**
     * Configure and show search fragment.
     */
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

    /**
     * Save data to bundle bundle.
     *
     * @return the bundle
     */
    private Bundle saveDataToBundle() {
        Bundle args = new Bundle();
        args.putString(KEY_SEARCHQUERY_NOTIFICATION, this.searchQueryNotification);
        args.putInt(KEY_POSITION, 3);
        return args;
    }


}

