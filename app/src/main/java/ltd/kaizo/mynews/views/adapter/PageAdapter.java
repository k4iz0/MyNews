package ltd.kaizo.mynews.views.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ltd.kaizo.mynews.controller.ui.base.BaseFragment;
import ltd.kaizo.mynews.controller.ui.news.NewsFragment;
import ltd.kaizo.mynews.Model.TabsNames;

/**
 * The type Page adapter.
 */
public class PageAdapter extends FragmentPagerAdapter {

    /**
     * The constant NUM_ITEMS.
     */
    private static int NUM_ITEMS = 3;
    /**
     * The Position.
     */
    int position;
    /**
     * The Section.
     */
    private String section;
    /**
     * The Fragments array.
     */
    private BaseFragment[] fragmentsArray;
    /**
     * The Title array.
     */
    private String[] titleArray;
    /**
     * The custom tab category name
     */
    private String customTab;

    /**
     * Instantiates a new Page adapter.
     *
     * @param mgr       the manager
     * @param section   the section
     * @param customTab the custom tab's name
     */
    public PageAdapter(FragmentManager mgr, String section, String customTab) {
        super(mgr);
        this.section = section;
        this.customTab = customTab;
        this.configureArray();
    }


    @Override

    public int getCount() {

        return (NUM_ITEMS);

    }

    /**
     * Configure array of fragment and title for viewPager's tabs
     */
    private void configureArray() {
        this.fragmentsArray = new NewsFragment[NUM_ITEMS];
        this.titleArray = new String[]{"TOP STORIES", "MOST POPULAR", this.customTab};
    }

    @Override

    public Fragment getItem(int position) {
        TabsNames tabsNames = TabsNames.values()[position];
        switch (tabsNames) {
            case TOP_STORIES:
                fragmentsArray[0] = NewsFragment.Companion.newInstance(position, this.section);
                return fragmentsArray[0];
            case MOST_POPULAR:
                fragmentsArray[1] = NewsFragment.Companion.newInstance(position, this.section);
                return fragmentsArray[1];
            case CUSTOM_TAB:
                return NewsFragment.Companion.newInstance(position, this.titleArray[2].toLowerCase());
            default:
                return NewsFragment.Companion.newInstance(position, this.section);

        }


    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.titleArray[position];
    }


    /**
     * Update section.
     *
     * @param section the section
     */
    public void updateSection(String section) {
        this.section = section;
        if (fragmentsArray[0] != null)
            ((NewsFragment) fragmentsArray[0]).updateSection(this.section);
        if (fragmentsArray[1] != null)
            ((NewsFragment) fragmentsArray[1]).updateSection(this.section);
    }
}
