package ltd.kaizo.mynews.Views.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ltd.kaizo.mynews.Controller.Fragments.BaseFragment;
import ltd.kaizo.mynews.Controller.Fragments.NewsFragment;
import ltd.kaizo.mynews.Model.TabsNames;

public class PageAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;
    int position;
    private String section;
    private BaseFragment[] fragmentsArray;
    private String[] titleArray;

    public PageAdapter(FragmentManager mgr, String section) {
        super(mgr);
        this.section = section;
        this.configureArray();
    }


    @Override

    public int getCount() {

        return (NUM_ITEMS);

    }

    private void configureArray() {
        this.fragmentsArray = new NewsFragment[NUM_ITEMS];
        this.titleArray = new String[]{"TOP STORIES", "MOST POPULAR", "SCIENCE"};
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


    public void updateSection(String section) {
        this.section = section;
        if (fragmentsArray[0] != null)
            ((NewsFragment) fragmentsArray[0]).updateSection(this.section);
        if (fragmentsArray[1] != null)
            ((NewsFragment) fragmentsArray[1]).updateSection(this.section);
    }
}