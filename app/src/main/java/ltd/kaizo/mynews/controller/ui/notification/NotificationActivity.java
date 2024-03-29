package ltd.kaizo.mynews.controller.ui.notification;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.controller.ui.base.BaseFragment;
import ltd.kaizo.mynews.controller.ui.news.NewsFragment;
import ltd.kaizo.mynews.controller.ui.search.SearchFragment;
import ltd.kaizo.mynews.utils.DataRecordManager;

import static ltd.kaizo.mynews.utils.ConstantKt.KEY_POSITION;
import static ltd.kaizo.mynews.utils.ConstantKt.KEY_SEARCHQUERY;

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
        DataRecordManager.INSTANCE.init(getApplicationContext());
        Intent intent = getIntent();
        //getting data from intent
        this.searchQueryNotification = intent.getStringExtra(getString(R.string.notificationSearchExtra));
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
     * Show fragment according to the tag
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
        BaseFragment searchFragment = SearchFragment.Companion.newInstance(10);
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
        args.putString(KEY_SEARCHQUERY, this.searchQueryNotification);
        args.putInt(KEY_POSITION, 3);
        return args;
    }


}

