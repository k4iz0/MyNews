package ltd.kaizo.mynews.controller.ui.news

import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog.*
import ltd.kaizo.mynews.Utils.DataRecordManager.KEY_SECTION_CUSTOM
import ltd.kaizo.mynews.Utils.DataRecordManager.read
import ltd.kaizo.mynews.R
import ltd.kaizo.mynews.Utils.Utils.showSnackBar
import ltd.kaizo.mynews.controller.ui.base.BaseActivity
import ltd.kaizo.mynews.controller.ui.notification.NotificationActivity
import ltd.kaizo.mynews.controller.ui.search.SearchActivity
import ltd.kaizo.mynews.controller.ui.settings.SettingActivity
import ltd.kaizo.mynews.views.adapter.PageAdapter
import timber.log.Timber

/**
 * The type Main activity.
 */
class NewsActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
     lateinit var newsViewModel: NewsViewModel
    override fun configureViewModel() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    override fun configureObserver() {
        newsViewModel.message.observe(this, Observer { message ->
            if (message != "" && message != null) {
                showSnackBar(activity_main_coordinator_layout,message)
                newsViewModel.message.value =""
            }
        })
    }

    override fun getFragmentLayout() = R.layout.activity_main

    override fun configureDesign() {
        this.configureViewPagerWithTabs(this.section)
        this.configureToolbar()
        this.configureDrawerLayout()
        this.configureNavigationView()
    }

    /**
     * The Section.
     */
    private var section = "world"
    /**
     * The View pager adapter.
     */
    private var viewPagerAdapter: PageAdapter? = null
    /**
     * The Help url.
     */
    private val helpUrl = "http://bfy.tw/JedE"

    /**
     * Configure toolbar.
     */
    private fun configureToolbar() {
        setSupportActionBar(activity_main_toolbar)
    }

    // *******************************
    //        VIEWPAGER
    // *******************************

    private fun configureViewPagerWithTabs(section: String) {
        val titlePosition = read(KEY_SECTION_CUSTOM, 1)
        val customTab = resources.getStringArray(R.array.category_array)[titlePosition].toUpperCase()
        viewPagerAdapter = PageAdapter(supportFragmentManager, section, customTab)
        activity_main_viewpager.adapter = viewPagerAdapter
        activity_main_tabs.setupWithViewPager(activity_main_viewpager)
        activity_main_tabs.tabMode = TabLayout.MODE_FIXED
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    /**
     * manage the click event on the item menu
     * @param item
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_activity_main_search -> {
                val searchActivity = Intent(this@NewsActivity, SearchActivity::class.java)
                startActivity(searchActivity)
                return true
            }
            R.id.menu_activity_main_notif -> {
                val notificationActivity = Intent(this@NewsActivity, NotificationActivity::class.java)
                startActivity(notificationActivity)
                return true
            }
            R.id.menu_activity_main_help -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(helpUrl))
                startActivity(browserIntent)
                return true
            }
            R.id.menu_activity_main_about -> {
                openDialog()
                return true
            }
            R.id.menu_activity_main_setting -> {
                val settingActivity = Intent(this@NewsActivity, SettingActivity::class.java)
                startActivity(settingActivity)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * method to configure AlertDialog on "about" click
     */
    private fun openDialog() {
        // Linkify the url
        val str = SpannableString(getString(R.string.nyt_url))
        Linkify.addLinks(str, Linkify.ALL)
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.layout_dialog, null)
        dialog_nyt_url.text = str
        builder.setView(view)
                .setCancelable(true)
                .show()
        dialog_nyt_url.movementMethod = LinkMovementMethod.getInstance()
    }
    // *******************************
    //         NAVIGATION DRAWER
    // *******************************

    /**
     * Configure navigation drawer listener.
     */
    private fun configureNavigationView() {
        activity_main_nav_view.setNavigationItemSelectedListener(this)
    }

    /**
     * Configure navigation drawer layout.
     */
    private fun configureDrawerLayout() {
        val toggle = ActionBarDrawerToggle(this, activity_main_drawer_layout, activity_main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        activity_main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

    }

    /**
     * update the section according to the item'value on click
     *
     * @param item
     * @return boolean
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.activity_main_drawer_Science -> this.section = getString(R.string.Science).toLowerCase()

            R.id.activity_main_drawer_world -> this.section = getString(R.string.World).toLowerCase()

            R.id.activity_main_drawer_Technology -> this.section = getString(R.string.Technology).toLowerCase()

            R.id.activity_main_drawer_Arts -> this.section = getString(R.string.Arts).toLowerCase()

            R.id.activity_main_drawer_Books -> this.section = getString(R.string.Books).toLowerCase()

            R.id.activity_main_drawer_Politics -> this.section = getString(R.string.Politics).toLowerCase()

            R.id.activity_main_drawer_Health -> this.section = getString(R.string.Health).toLowerCase()

            R.id.activity_main_drawer_Travel -> this.section = getString(R.string.Travel).toLowerCase()

        }
        Timber.i("onNavigationItemSelected: %s", this.section)
        this.viewPagerAdapter!!.updateSection(this.section)
        activity_main_viewpager.currentItem = 0
        activity_main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
