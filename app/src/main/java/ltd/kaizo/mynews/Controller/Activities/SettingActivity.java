package ltd.kaizo.mynews.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import ltd.kaizo.mynews.Model.Utils.DataRecordManager;
import ltd.kaizo.mynews.R;

import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.KEY_API_PERIOD;
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
    @BindView(R.id.activity_setting_spinner_section)
    Spinner spinnerSection;
    /**
     * The Toolbar.
     */
    @BindView(R.id.activity_setting_toolbar)
    Toolbar toolbar;
    /**
     * The Radio group.
     */
    @BindView(R.id.activity_settings_radio_group)
    RadioGroup radioGroup;
    /**
     * The Radio 1.
     */
    @BindView(R.id.activity_setting_radio1)
    RadioButton radio1;
    /**
     * The Radio 7.
     */
    @BindView(R.id.activity_setting_radio7)
    RadioButton radio7;
    /**
     * The Radio 30.
     */
    @BindView(R.id.activity_setting_radio30)
    RadioButton radio30;
    /**
     * The Api period.
     */
    String apiPeriod = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        DataRecordManager.init(getApplicationContext());
        this.configureToolbar();
        this.configureSpinner();
        this.configureRadioGroup();
        this.onRadioButtonClicked();

    }

    /**
     * set the checked value at starting according to the sharedPreferences
     */
    private void configureRadioGroup() {
        apiPeriod = read(KEY_API_PERIOD, "7");
        switch (apiPeriod) {
            case "1":
                radio1.toggle();
                break;
            case "7":
                radio7.toggle();
                break;
            case "30":
                radio30.toggle();
                break;

        }
    }

    /**
     * configure the onClick event for the radio buttons
     */
    private void onRadioButtonClicked() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.activity_setting_radio1:
                        write(KEY_API_PERIOD, "1");
                        apiPeriod = "1";
                        break;
                    case R.id.activity_setting_radio7:
                        write(KEY_API_PERIOD, "7");
                        apiPeriod = "7";
                        break;
                    case R.id.activity_setting_radio30:
                        write(KEY_API_PERIOD, "30");
                        apiPeriod = "30";
                        break;
                }
                if (apiPeriod.equalsIgnoreCase("1")) {
                    Toasty.info(getApplicationContext(), getString(R.string.period)+" " + apiPeriod + getString(R.string.day)).show();
                } else {
                    Toasty.info(getApplicationContext(), getString(R.string.period)+" " + apiPeriod + getString(R.string.days)).show();
                }

            }
        });
    }

    /**
     * Configure spinnerSection by setting up the adapter and the onClick Listener
     */
    private void configureSpinner() {
        int customTabPosition = read(KEY_SECTION_CUSTOM, 1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, R.layout.support_simple_spinner_dropdown_item);
        spinnerSection.setAdapter(adapter);
        spinnerSection.setSelection(customTabPosition);
        spinnerSection.setOnItemSelectedListener(this);
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
