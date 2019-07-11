package ltd.kaizo.mynews.controller.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_setting.*
import ltd.kaizo.mynews.R
import ltd.kaizo.mynews.utils.DataRecordManager
import ltd.kaizo.mynews.utils.DataRecordManager.read
import ltd.kaizo.mynews.utils.DataRecordManager.write
import ltd.kaizo.mynews.utils.KEY_API_PERIOD
import ltd.kaizo.mynews.utils.KEY_SECTION_CUSTOM

/**
 * The type Setting activity.
 */
class SettingActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var apiPeriod = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        ButterKnife.bind(this)
        DataRecordManager.init(applicationContext)
        this.configureToolbar()
        this.configureSpinner()
        this.configureRadioGroup()
        this.onRadioButtonClicked()

    }

    /**
     * set the checked value at starting according to the sharedPreferences
     */
    private fun configureRadioGroup() {
        apiPeriod = read(KEY_API_PERIOD, "7")
        when (apiPeriod) {
            "1" -> activity_setting_radio1.toggle()
            "7" -> activity_setting_radio7.toggle()
            "30" -> activity_setting_radio30.toggle()
        }
    }

    /**
     * configure the onClick event for the radio buttons
     */
    private fun onRadioButtonClicked() {
        activity_settings_radio_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.activity_setting_radio1 -> {
                    write(KEY_API_PERIOD, "1")
                    apiPeriod = "1"
                }
                R.id.activity_setting_radio7 -> {
                    write(KEY_API_PERIOD, "7")
                    apiPeriod = "7"
                }
                R.id.activity_setting_radio30 -> {
                    write(KEY_API_PERIOD, "30")
                    apiPeriod = "30"
                }
            }
            if (apiPeriod.equals("1", ignoreCase = true)) {
                Toasty.info(applicationContext, getString(R.string.period) + " " + apiPeriod + " " + getString(R.string.day)).show()
            } else {
                Toasty.info(applicationContext, getString(R.string.period) + " " + apiPeriod + " " + getString(R.string.days)).show()
            }
        }
    }

    /**
     * Configure spinnerSection by setting up the adapter and the onClick Listener
     */
    private fun configureSpinner() {
        val customTabPosition = read(KEY_SECTION_CUSTOM, 1)
        val adapter = ArrayAdapter.createFromResource(this, R.array.category_array, R.layout.support_simple_spinner_dropdown_item)
        activity_setting_spinner_section.adapter = adapter
        activity_setting_spinner_section.setSelection(customTabPosition)
        activity_setting_spinner_section.onItemSelectedListener = this
    }

    /**
     * Configure toolbar.
     */
    private fun configureToolbar() {
        setSupportActionBar(activity_setting_toolbar)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        write(KEY_SECTION_CUSTOM, position)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}
}
