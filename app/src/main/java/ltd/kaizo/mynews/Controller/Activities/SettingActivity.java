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
import es.dmoral.toasty.Toasty;
import ltd.kaizo.mynews.Model.Utils.DataRecordManager;
import ltd.kaizo.mynews.R;

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
    private String section;

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
        DataRecordManager.write(DataRecordManager.KEY_SECTION_CUSTOM,parent.getItemAtPosition(position).toString().toUpperCase());
        Toasty.info(this, parent.getItemAtPosition(position)+" selected !").show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
     }
}
