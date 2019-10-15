package ltd.kaizo.mynews.controller.ui.search


import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import com.evernote.android.job.JobManager
import com.google.gson.Gson
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_search.*
import ltd.kaizo.mynews.R
import ltd.kaizo.mynews.controller.ui.base.BaseFragment
import ltd.kaizo.mynews.controller.ui.news.NewsFragment
import ltd.kaizo.mynews.controller.ui.notification.AndroidJobCreator
import ltd.kaizo.mynews.controller.ui.notification.NytShowNotificationJob.Companion.cancelJob
import ltd.kaizo.mynews.controller.ui.notification.NytShowNotificationJob.Companion.schedulePeriodicJob
import ltd.kaizo.mynews.model.SearchQuery
import ltd.kaizo.mynews.utils.DataRecordManager.getSearchQueryFromSharedPreferences
import ltd.kaizo.mynews.utils.DataRecordManager.read
import ltd.kaizo.mynews.utils.DataRecordManager.write
import ltd.kaizo.mynews.utils.KEY_POSITION
import ltd.kaizo.mynews.utils.KEY_SEARCHQUERY
import ltd.kaizo.mynews.utils.KEY_SEARCHQUERY_NOTIFICATION
import ltd.kaizo.mynews.utils.KEY_TAG
import ltd.kaizo.mynews.utils.Utils.formatDate
import timber.log.Timber
import java.util.*


/**
 * The type Search fragment.
 */
/**
 * Instantiates a new Search fragment.
 */
class SearchFragment : BaseFragment() {
    /**
     * The Search query.
     */
    private lateinit var searchQuery: SearchQuery
    /**
     * The Begin date set listener.
     */
    private var beginDateSetListener: DatePickerDialog.OnDateSetListener? = null
    /**
     * The End date set listener.
     */
    private var endDateSetListener: DatePickerDialog.OnDateSetListener? = null
    /**
     * The Year.
     */
    private var year: Int = 0
    /**
     * The Day.
     */
    private var day: Int = 0
    /**
     * The Month.
     */
    private var month: Int = 0
    /**
     * The Gson.
     */
    private var gson: Gson? = null
    /**
     * The Begin date.
     */
    private var beginDate: String? = null
    /**
     * The End date.
     */
    private var endDate: String? = null
    /**
     * The Tag.
     */
    private var tag: Int = 0
    /**
     * The Job id.
     */
    private var jobID: Int = 0

    override val fragmentLayout = R.layout.fragment_search

    override fun updateDesign() {
        this.configureDatePicker()
        this.searchQuery = SearchQuery()
        this.configureCalendar()


        when (this.tag) {
            //notification case
            10 -> {
                if (read(KEY_SEARCHQUERY_NOTIFICATION, "").equals("", ignoreCase = true)) {
                    this.searchQuery = getSearchQueryFromSharedPreferences(KEY_SEARCHQUERY_NOTIFICATION)
                }
                this.configureDesignForNotification()
                this.configureNotificationTextView()
                this.configureNotificationSwitch()
            }
            //Search case
            20 -> this.configureSearchButton()
            else -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            this.tag = arguments!!.getInt(KEY_TAG)
        }
    }

    /**
     * configure design according to the activity you're coming from
     */
    override fun configureDesign() {}

    // *******************************

    //        Notification Activity

    // *******************************

    /**
     * Configure notification switch.
     */
    private fun configureNotificationSwitch() {
        fragment_search_notification_switch.setOnCheckedChangeListener { _, isChecked ->
            if (configureNotificationResearch()!!) {

                if (isChecked) {
                    Toasty.success(context!!, getString(R.string.notificationStart), Toast.LENGTH_SHORT).show()
                    Timber.i("job start ")
                    configureNotificationTextView()
                } else {
                    Toasty.warning(context!!, getString(R.string.notificationCancel), Toast.LENGTH_SHORT).show()
                    cancelJob(jobID)
                    Timber.i("job cancel ")
                    configureNotificationTextView()
                    //erase notification in sharedPreferences
                    write(KEY_SEARCHQUERY_NOTIFICATION, "")
                    searchQuery = SearchQuery()
                }
            } else {
                fragment_search_notification_switch.isChecked = false
            }
        }
    }

    /**
     * Configure notification job.
     */
    private fun configureNotificationJob() {
        JobManager.create(context!!).addJobCreator(AndroidJobCreator())
        jobID = schedulePeriodicJob()
    }


    /**
     * Configure design for the notification activity
     * to show and hide elements
     */
    private fun configureDesignForNotification() {
        fragment_search_button!!.visibility = View.GONE
        fragment_search_textview_end_date.visibility = View.GONE
        fragment_search_begin_date.visibility = View.GONE
        fragment_search_textview_begindate.visibility = View.GONE
        fragment_search_end_date.visibility = View.GONE
        fragment_search_notification_switch.visibility = View.VISIBLE
        fragment_search_notification_textview.visibility = View.VISIBLE
        if (read(KEY_SEARCHQUERY_NOTIFICATION, "") == "") {
            this.fragment_search_notification_switch.isChecked = true
        }
    }

    /**
     * set the notification textView
     * according to the notification's  switch status
     */
    private fun configureNotificationTextView() {
        if (fragment_search_notification_switch.isChecked) {
            this.fragment_search_notification_textview.text = Html.fromHtml("Notification enable for <br><b>" + searchQuery.queryTerms + "</b>, cancel ?")
            fragment_search_edittext.setText(searchQuery.queryTerms)
        } else {
            this.fragment_search_notification_textview.text = getString(R.string.enable_notifications)
            fragment_search_edittext.setText("")
        }
    }

    /**
     * Configure notification research boolean.
     *
     * @return the boolean
     */
    private fun configureNotificationResearch(): Boolean? {
        this.configureSearchRequest()
        var isValid: Boolean? = false
        when {
            this.searchQuery.queryTerms.trim { it <= ' ' } == "" -> Toasty.error(context!!, getString(R.string.noQueryTerm), Toast.LENGTH_SHORT).show()
            this.searchQuery.queryFields == "" -> Toasty.error(context!!, getString(R.string.noField), Toast.LENGTH_SHORT).show()
            else -> {
                gson = Gson()
                write(KEY_SEARCHQUERY_NOTIFICATION, gson!!.toJson(this.searchQuery))
                this.configureNotificationJob()
                isValid = true
            }
        }
        return isValid
    }

    // *******************************

    //        Search Activity

    // *******************************

    /**
     * Configure search button by setting up the listener
     * and check some constraint before launch
     */
    private fun configureSearchButton() {
        fragment_search_button.setOnClickListener {
            configureSearchRequest()
            var execute = false
            if (searchQuery.queryTerms.trim { it <= ' ' } == "") {
                Toasty.error(context!!, "You need to enter a query term", Toast.LENGTH_SHORT).show()
            } else if (searchQuery.queryFields == "") {
                Toasty.error(context!!, "You need to select at least one field", Toast.LENGTH_SHORT).show()
            } else {
                if (dateIsValid(searchQuery.beginDate!!, searchQuery.endDate!!)) {
                    Toasty.error(context!!, "You need to select a valid time period !", Toast.LENGTH_SHORT).show()
                } else
                    execute = true
            }
            if (execute) {
                configureAndShowNewsFragment()
            }
        }

    }

    /**
     * Configure and show newsFragment.
     */
    private fun configureAndShowNewsFragment() {
        val newsFragment = NewsFragment()
        newsFragment.arguments = saveDataToBundle()
        fragmentManager!!.beginTransaction()
                .replace(R.id.activity_search_Framelayout, newsFragment)
                .addToBackStack(null)
                .commit()
    }

    /**
     * Save data to bundle bundle.
     *
     * @return the bundle
     */
    private fun saveDataToBundle(): Bundle {
        gson = Gson()
        val args = Bundle()
        args.putString(KEY_SEARCHQUERY, gson!!.toJson(searchQuery))
        args.putInt(KEY_POSITION, 3)
        return args
    }

    /**
     * Configure search request by setting value to the searchQuery object
     */
    private fun configureSearchRequest() {
        this.beginDate = fragment_search_begin_date.text.toString()
        this.endDate = fragment_search_end_date.text.toString()
        this.searchQuery.queryTerms = fragment_search_edittext.text.toString()
        this.configureCheckboxValues()
        //for SearchActivity
        if (tag == 20) {
            if (beginDate != "" || beginDate != "") {
                searchQuery.beginDate = formatDate(beginDate as String)
                searchQuery.endDate = formatDate(endDate as String)
            }
        }

    }

    /**
     * check if the begin date isn't greater than the end date
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the boolean
     */
    private fun dateIsValid(date1: String, date2: String): Boolean {
        return date2 < date1
    }

    /**
     * Configure checkbox values.
     */
    private fun configureCheckboxValues() {
        if (fragment_search_checkbox_arts.isChecked)
            searchQuery.queryFields = fragment_search_checkbox_arts.text.toString()
        if (fragment_search_checkbox_business.isChecked)
            searchQuery.queryFields = fragment_search_checkbox_business.text.toString()
        if (fragment_search_checkbox_sports.isChecked)
            searchQuery.queryFields = fragment_search_checkbox_sports.text.toString()
        if (fragment_search_checkbox_politics.isChecked)
            searchQuery.queryFields = fragment_search_checkbox_politics.text.toString()
        if (fragment_search_checkbox_books.isChecked)
            searchQuery.queryFields = fragment_search_checkbox_books.text.toString()
        if (fragment_search_checkbox_travel.isChecked)
            searchQuery.queryFields = fragment_search_checkbox_travel.text.toString()
    }

    /**
     * Configure date picker.
     */
    private fun configureDatePicker() {

        fragment_search_begin_date.setOnClickListener {
            val dialog = DatePickerDialog(Objects.requireNonNull(context),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    beginDateSetListener,
                    year, month, day)
            Objects.requireNonNull(dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        beginDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            var month = month
            month += 1
            val beginDate = add0ToDate(dayOfMonth) + "/" + add0ToDate(month) + "/" + year
            fragment_search_begin_date.text = beginDate
        }
        fragment_search_end_date.setOnClickListener {
            val dialog = DatePickerDialog(Objects.requireNonNull(context),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    endDateSetListener,
                    year, month, day)
            Objects.requireNonNull(dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
        endDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            var month = month
            month += 1
            val endDate = add0ToDate(dayOfMonth) + "/" + add0ToDate(month) + "/" + year
            fragment_search_end_date.text = endDate
        }
    }

    /**
     * Configure calendar.
     */
    private fun configureCalendar() {
        val cal = Calendar.getInstance()
        this.year = cal.get(Calendar.YEAR)
        this.month = cal.get(Calendar.MONTH)
        this.day = cal.get(Calendar.DAY_OF_MONTH)
    }

    /**
     * Add 0 to date string when date number < 10
     *
     * @param nb the int of day or month
     * @return the string of day or month with 0
     */
    private fun add0ToDate(nb: Int): String {
        return if (nb < 10) {
            "0$nb"
        } else {
            "" + nb
        }
    }

    override fun configureViewModel() {}

    override fun configureObserver() {}

    companion object {

        /**
         * New instance base fragment.
         *
         * @param tag the tag
         * @return the base fragment
         */
        fun newInstance(tag: Int): BaseFragment {
            val frag = SearchFragment()
            val bundle = Bundle()
            bundle.putInt(KEY_TAG, tag)
            frag.arguments = bundle
            return frag

        }
    }
}// Required empty public constructor
