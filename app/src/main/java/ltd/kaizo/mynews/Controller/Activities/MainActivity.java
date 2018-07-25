package ltd.kaizo.mynews.Controller.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.Adapter.PageAdapter;
import ltd.kaizo.mynews.R;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_viewpager)
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureViewPager();
    }

    private void configureViewPager() {
    viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
    }
}
