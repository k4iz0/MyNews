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

        return NewsFragment.newInstance(position);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page "+position;
    }
}
