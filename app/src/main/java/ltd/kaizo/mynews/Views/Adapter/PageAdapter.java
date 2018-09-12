package ltd.kaizo.mynews.Views.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ltd.kaizo.mynews.Controller.Activities.MainActivity;
import ltd.kaizo.mynews.Controller.Fragments.BaseFragment;
import ltd.kaizo.mynews.Controller.Fragments.NewsFragment;
import ltd.kaizo.mynews.Model.TabsNames;
import ltd.kaizo.mynews.Model.Utils.DataRecordManager;

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
     * Instantiates a new Page adapter.
     *
     * @param mgr     the manager
     * @param section the section
     */
    public PageAdapter(FragmentManager mgr, String section) {
        super(mgr);
        this.section = section;
        this.configureArray();
    }


    @Override

    public int getCount() {

        return (NUM_ITEMS);

    }

    /**
     * Configure array.
     */
    private void configureArray() {
        this.fragmentsArray = new NewsFragment[NUM_ITEMS];
        this.titleArray = new String[]{"TOP STORIES", "MOST POPULAR", "SCIENCE"};
        this.titleArray[2] = DataRecordManager.read(DataRecordManager.KEY_SECTION_CUSTOM,"SCIENCE");
    }

    @Override

    public Fragment getItem(int position) {
        TabsNames tabsNames = TabsNames.values()[position];
        switch (tabsNames) {
            case TOP_STORIES:
                fragmentsArray[0] = NewsFragment.newInstance(position, this.section);
                return fragmentsArray[0];
            case MOST_POPULAR:
                fragmentsArray[1] = NewsFragment.newInstance(position, this.section);
                return fragmentsArray[1];
            case CUSTOM_TAB:
                return NewsFragment.newInstance(position, this.titleArray[2].toLowerCase());
            default:
                return NewsFragment.newInstance(position, this.section);

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
