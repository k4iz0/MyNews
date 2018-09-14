package ltd.kaizo.mynews.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ltd.kaizo.mynews.R;

import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.KEY_SECTION_CUSTOM;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.read;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.write;

/**
 * The type Setting activity.
 */
public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * The Setting text view.
     */
    @BindView(R.id.activity_setting_textview)
    TextView settingTextView;
    /**
     * The Spinner.
     */
    @BindView(R.id.activity_setting_spinner)
    Spinner spinner;
    /**
     * The Toolbar.
     */
    @BindView(R.id.activity_setting_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureSpinner();

    }

    /**
     * Configure spinner by setting up the adapter and the onClick Listener
     */
    private void configureSpinner() {
        int customTabPosition = read(KEY_SECTION_CUSTOM, 1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(customTabPosition);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Configure toolbar.
     */
    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        write(KEY_SECTION_CUSTOM, position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
