package ltd.kaizo.mynews.views.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ltd.kaizo.mynews.model.TabsNames
import ltd.kaizo.mynews.utils.NUM_ITEMS
import ltd.kaizo.mynews.controller.ui.news.NewsFragment
/**
 * The type Page adapter.
 */
class PageAdapter(fragmentManager: FragmentManager, private var section: String?,
                  private val customTab: String) : FragmentPagerAdapter(fragmentManager) {
    /**
     * The Title array.
     */
    private var titleArray: Array<String>? = null

    init {
        this.configureArray()
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    /**
     * Configure array of fragment and title for viewPager's tabs
     */
    private fun configureArray() {
        this.titleArray = arrayOf("TOP STORIES", "MOST POPULAR", this.customTab)
    }

    override fun getItem(position: Int): Fragment {
        return when (TabsNames.values()[position]) {
            TabsNames.TOP_STORIES -> NewsFragment.newInstance(position, this.section!!)
            TabsNames.MOST_POPULAR -> NewsFragment.newInstance(position, this.section!!)
            TabsNames.CUSTOM_TAB -> NewsFragment.newInstance(position, this.titleArray!![2].toLowerCase())
            else -> NewsFragment.newInstance(position, this.section!!)
        }
    }

    override fun getPageTitle(position: Int) = this.titleArray!![position]

}
