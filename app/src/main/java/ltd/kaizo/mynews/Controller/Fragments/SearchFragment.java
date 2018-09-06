package ltd.kaizo.mynews.Controller.Fragments;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.evernote.android.job.JobConfig;
import com.evernote.android.job.JobManager;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import ltd.kaizo.mynews.BuildConfig;
import ltd.kaizo.mynews.Model.SearchQuery;
import ltd.kaizo.mynews.Model.Utils.Androidjob.AndroidJobCreator;
import ltd.kaizo.mynews.R;

import static ltd.kaizo.mynews.Model.Utils.Androidjob.NytShowNotificationJob.cancelJob;
import static ltd.kaizo.mynews.Model.Utils.Androidjob.NytShowNotificationJob.schedulePeriodicJob;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_POSITION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_SEARCHQUERY;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_SEARCHQUERY_NOTIFICATION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_TAG;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.write;

/**
 * The type Search fragment.
 */
public class SearchFragment extends BaseFragment {

    /**
     * The Begin date title.
     */
    @BindView(R.id.fragment_search_textview_begindate)
    TextView beginDateTitle;
    /**
     * The End date title.
     */
    @BindView(R.id.fragment_search_textview_end_date)
    TextView endDateTitle;
    /**
     * The Notification switch.
     */
    @BindView(R.id.fragment_search_notification_switch)
    Switch notificationSwitch;
    /**
     * The Notification text view.
     */
    @BindView(R.id.fragment_search_notification_textview)
    TextView notificationTextView;
    /**
     * The Begin date textview.
     */
    @BindView(R.id.fragment_search_begin_date)
    TextView beginDateTextview;
    /**
     * The End date textview.
     */
    @BindView(R.id.fragment_search_end_date)
    TextView endDateTextview;
    /**
     * The Search button.
     */
    @BindView(R.id.fragment_search_button)
    Button searchButton;
    /**
     * The Check box arts.
     */
    @BindView(R.id.fragment_search_checkbox_arts)
    CheckBox checkBoxArts;
    /**
     * The Check box business.
     */
    @BindView(R.id.fragment_search_checkbox_business)
    CheckBox checkBoxBusiness;
    /**
     * The Check box sports.
     */
    @BindView(R.id.fragment_search_checkbox_sports)
    CheckBox checkBoxSports;
    /**
     * The Check box politics.
     */
    @BindView(R.id.fragment_search_checkbox_politics)
    CheckBox checkBoxPolitics;
    /**
     * The Check box books.
     */
    @BindView(R.id.fragment_search_checkbox_books)
    CheckBox checkBoxBooks;
    /**
     * The Check box travel.
     */
    @BindView(R.id.fragment_search_checkbox_travel)
    CheckBox checkBoxTravel;
    /**
     * The Search editext field.
     */
    @BindView(R.id.fragment_search_edittext)
    EditText searchEdiText;
    /**
     * The Search query.
     */
    private SearchQuery searchQuery;
    /**
     * The Begin date set listener.
     */
    private DatePickerDialog.OnDateSetListener beginDateSetListener;
    /**
     * The End date set listener.
     */
    private DatePickerDialog.OnDateSetListener endDateSetListener;
    /**
     * The Year.
     */
    private int year;
    /**
     * The Day.
     */
    private int day;
    /**
     * The Month.
     */
    private int month;
    /**
     * The Gson.
     */
    private Gson gson;
    /**
     * The Begin date.
     */
    private String beginDate;
    /**
     * The End date.
     */
    private String endDate;
    /**
     * The Tag.
     */
    private int tag;
    private int jobID;

    /**
     * Instantiates a new Search fragment.
     */

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * New instance base fragment.
     *
     * @param tag the tag
     * @return the base fragment
     */
    public static BaseFragment newInstance(int tag) {
        SearchFragment frag = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Key_TAG, tag);
        frag.setArguments(bundle);
        return frag;

    }

    @Override
    protected void updateDesign() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tag = getArguments().getInt(Key_TAG);
        }
    }

    @Override
    protected void configureDesign() {
        searchQuery = new SearchQuery();
        this.configureCalendar();
        this.configureDatePicker();

        switch (this.tag) {
            case 10:
                this.configureDesignForNotification();
                this.configureNotificationSwitch();
                break;
            case 20:
                this.configureSearchButton();
                break;
            default:
                break;
        }
    }

    private void configureNotificationSwitch() {
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (configureNotificationResearch()) {

                    if (isChecked) {
                        Toast.makeText(getContext(), "ON", Toast.LENGTH_SHORT).show();
                        Log.i("notificationJob", "job start ");
                    } else {
                        Toast.makeText(getContext(), "OFF", Toast.LENGTH_SHORT).show();
                        cancelJob(jobID);
                        Log.i("notificationJob", "job cancel ");
                        //erase notification in sharedPreferences
                        write(Key_SEARCHQUERY_NOTIFICATION, "");
                    }
                } else {
                    notificationSwitch.setChecked(false);
                }
            }
        });
    }

    private void configureNotificationJob() {
        JobManager.create(getContext()).addJobCreator(new AndroidJobCreator());
        jobID = schedulePeriodicJob();
    }


    /**
     * Configure design for the notification activity
     * to show and hide elements
     */
    private void configureDesignForNotification() {
        this.searchButton.setVisibility(View.GONE);
        this.endDateTextview.setVisibility(View.GONE);
        this.beginDateTextview.setVisibility(View.GONE);
        this.beginDateTitle.setVisibility(View.GONE);
        this.endDateTitle.setVisibility(View.GONE);
        this.notificationSwitch.setVisibility(View.VISIBLE);
        this.notificationTextView.setVisibility(View.VISIBLE);
    }

    /**
     * Configure search button by setting up the listener
     * and check some constraint before launch
     */
    private void configureSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configureSearchRequest();
                boolean execute = false;

                if (searchQuery.getQueryTerms().trim().equals("")) {
                    Toasty.error(getContext(), "You need to enter a query term", Toast.LENGTH_SHORT).show();
                } else if (searchQuery.getQueryFields().equals("")) {
                    Toasty.error(getContext(), "You need to select at least one field", Toast.LENGTH_SHORT).show();
                } else if (searchQuery.getBeginDate() != null && searchQuery.getEndDate() != null) {
                    if (dateIsValid(searchQuery.getBeginDate(), searchQuery.getEndDate())) {
                        Toasty.error(getContext(), "You need to select a valid time period !", Toast.LENGTH_SHORT).show();
                    }
                    else execute = true;
                }
                else {execute =true;}
                if (execute) {
                    configureAndShowNewsFragment();
                }
            }
        });

    }

    private Boolean configureNotificationResearch() {
        this.configureSearchRequest();
        Boolean isValid = false;
        if (this.searchQuery.getQueryTerms().trim().equals("")) {
            Toasty.error(getContext(), "You need to enter a query term", Toast.LENGTH_SHORT).show();
        } else if (this.searchQuery.getQueryFields().equals("")) {
            Toasty.error(getContext(), "You need to select at least one field", Toast.LENGTH_SHORT).show();
        } else {
            gson = new Gson();
            write(Key_SEARCHQUERY_NOTIFICATION,gson.toJson(this.searchQuery));
            this.configureNotificationJob();
            isValid = true;
        }
        return isValid;
    }

    /**
     * Configure and show news fragment.
     */
    private void configureAndShowNewsFragment() {
        BaseFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(saveDataToBundle());
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_search_Framelayout, newsFragment)
                .addToBackStack(null)
                .commit();
    }

    private Bundle saveDataToBundle() {
        gson = new Gson();
        Bundle args = new Bundle();
        args.putString(Key_SEARCHQUERY, gson.toJson(searchQuery));
        args.putInt(Key_POSITION, 3);
        return args;
    }

    /**
     * Configure search request by setting value to the searchQuery object
     */
    private void configureSearchRequest() {
        this.beginDate = beginDateTextview.getText().toString();
        this.endDate = endDateTextview.getText().toString();
        searchQuery.setQueryTerms(searchEdiText.getText().toString());
        this.configureCheckboxValues();
        if (!beginDate.equals("") || !beginDate.equals("")) {
            searchQuery.setBeginDate(beginDate);
            searchQuery.setEndDate(endDate);
        }

    }

    /**
     * check if the begin date isn't greater than the end date
     *
     * @param date1 the date 1
     * @param date2 the date 2star wars
     * @return the boolean
     */
    private Boolean dateIsValid(String date1, String date2) {
        return date2.compareTo(date1) < 0;
    }

    /**
     * Configure checkbox values.
     */
    private void configureCheckboxValues() {
        if (checkBoxArts.isChecked())
            searchQuery.setQueryFields(checkBoxArts.getText().toString());
        if (checkBoxBusiness.isChecked())
            searchQuery.setQueryFields(checkBoxBusiness.getText().toString());
        if (checkBoxSports.isChecked())
            searchQuery.setQueryFields(checkBoxSports.getText().toString());
        if (checkBoxPolitics.isChecked())
            searchQuery.setQueryFields(checkBoxPolitics.getText().toString());
        if (checkBoxBooks.isChecked())
            searchQuery.setQueryFields(checkBoxBooks.getText().toString());
        if (checkBoxTravel.isChecked())
            searchQuery.setQueryFields(checkBoxTravel.getText().toString());
    }

    /**
     * Configure date picker.
     */
    private void configureDatePicker() {

        beginDateTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        beginDateSetListener,
                        year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        beginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String beginDate = add0ToDate(dayOfMonth) + "/" + add0ToDate(month) + "/" + year;
                beginDateTextview.setText(beginDate);
            }
        };
        endDateTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        endDateSetListener,
                        year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String endDate = add0ToDate(dayOfMonth) + "/" + add0ToDate(month) + "/" + year;
                endDateTextview.setText(endDate);
            }
        };
    }

    /**
     * Configure calendar.
     */
    private void configureCalendar() {
        Calendar cal = Calendar.getInstance();
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Add 0 to date string when date number < 10
     *
     * @param nb the int of day or month
     * @return the string of day or month with 0
     */
    private String add0ToDate(int nb) {
        if (nb < 10) {
            return "0" + nb;
        } else {
            return "" + nb;
        }
    }
}
