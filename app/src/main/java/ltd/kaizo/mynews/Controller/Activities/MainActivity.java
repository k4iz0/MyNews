package ltd.kaizo.mynews.Controller.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;
import ltd.kaizo.mynews.Model.Utils.DataRecordManager;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Views.Adapter.PageAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.activity_main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.activity_main_tabs)
    TabLayout tabs;
    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_main_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.activity_main_nav_view)
    NavigationView navigationView;
    @State
    String section = "world";
    private PageAdapter viewPagerAdapter;
    private String helpUrl = "http://bfy.tw/JedE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DataRecordManager.init(getApplicationContext());
        this.configureViewPagerWithTabs(this.section);
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();

    }

    // *******************************

    //         CONFIGURATION

    // *******************************

    private void configureNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        MenuItem item = menu.add(R.id.activity_main_drawer_group, R.id.activity_main_drawer_Arts, 3, R.string.Arts);
        item.setActionView(R.layout.menu_icon);

    }

    private void configureDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
    }

    private void configureViewPagerWithTabs(String section) {
        viewPagerAdapter = new PageAdapter(getSupportFragmentManager(), section);
        viewPager.setAdapter(viewPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_search:
                Intent searchActivity = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchActivity);
                return true;
            case R.id.menu_activity_main_notif:
                Intent notificationActivity = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(notificationActivity);
                return true;
            case R.id.menu_activity_main_help:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(helpUrl));
                startActivity(browserIntent);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.activity_main_drawer_Science:
                this.section = getString(R.string.Science).toLowerCase();
                break;

            case R.id.activity_main_drawer_world:
                this.section = getString(R.string.World).toLowerCase();
                break;

            case R.id.activity_main_drawer_Technology:
                this.section = getString(R.string.Technology).toLowerCase();
                break;

            case R.id.activity_main_drawer_Arts:
                this.section = getString(R.string.Arts).toLowerCase();
                break;

            case R.id.activity_main_drawer_Books:
                this.section = getString(R.string.Books).toLowerCase();
                break;

            case R.id.activity_main_drawer_Politics:
                this.section = getString(R.string.Politics).toLowerCase();
                break;

            case R.id.activity_main_drawer_Health:
                this.section = getString(R.string.Health).toLowerCase();
                break;

            case R.id.activity_main_drawer_Travel:
                this.section = getString(R.string.Travel).toLowerCase();
                break;

            default:
                break;
        }
        this.viewPagerAdapter.updateSection(this.section);
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
