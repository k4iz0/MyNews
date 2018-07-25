package ltd.kaizo.mynews.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import icepick.State;
import ltd.kaizo.mynews.Controller.Fragments.FirstFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    @State
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
    this.position = position;
        return FirstFragment.newInstance();

    }
}
