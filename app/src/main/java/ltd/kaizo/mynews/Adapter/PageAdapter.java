package ltd.kaizo.mynews.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ltd.kaizo.mynews.Controller.Fragments.NewsFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;
    int position;

    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }


    @Override

    public int getCount() {

        return (NUM_ITEMS);

    }


    @Override

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewsFragment.newInstance(position,"home");
            case 1:
                return NewsFragment.newInstance(position, "all-sections");
            case 2:
                return NewsFragment.newInstance(position, "world");
            default:
                return NewsFragment.newInstance(position, "home");

        }


    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "BUSINESS";
            default:
                return null;

        }
    }
}
