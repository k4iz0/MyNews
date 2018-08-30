package ltd.kaizo.mynews.Controller.Fragments;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import ltd.kaizo.mynews.R;
import ltd.kaizo.mynews.Utils.SearchQuery;

import static ltd.kaizo.mynews.Controller.Fragments.NewsFragment.Key_POSITION;
import static ltd.kaizo.mynews.Controller.Fragments.NewsFragment.Key_SEARCHQUERY;

/**
 * The type Search fragment.
 */
public class SearchFragment extends BaseFragment {
    public static final String Key_TAG = "";
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
    private int tag;

    /**
     * Instantiates a new Search fragment.
     */
    public SearchFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance(int tag) {
        SearchFragment frag = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Key_TAG, tag);
        frag.setArguments(bundle);
        return frag;

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
        if (this.tag == 10) {
            searchButton.setVisibility(View.INVISIBLE);
        } else {

            this.configureSearchButton();
        }
    }

    @Override
    protected void updateDesign() {

    }

    /**
     * Configure search button.
     */
    private void configureSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                configureSearchRequest();

                if (searchQuery.getQueryTerms().trim().equals("")) {
                    Toast.makeText(getContext(), "You need to enter a query term", Toast.LENGTH_SHORT).show();
                } else if (searchQuery.getQueryFields().equals("")) {
                    Toast.makeText(getContext(), "You need to select at least one field", Toast.LENGTH_SHORT).show();
                } else if (dateIsValid(searchQuery.getBeginDate(), searchQuery.getEndDate())) {
                    Toast.makeText(getContext(), "You need to select a valid time period !", Toast.LENGTH_SHORT).show();
                } else {
                    configureAndShowNewsFragment();
                }
            }
        });

    }

    /**
     * Configure and show news fragment.
     */
    private void configureAndShowNewsFragment() {
        BaseFragment newsFragment = new NewsFragment();
        gson = new Gson();
        Bundle args = new Bundle();
        args.putString(Key_SEARCHQUERY, gson.toJson(searchQuery));
        args.putInt(Key_POSITION, 3);
        newsFragment.setArguments(args);

        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_search_Framelayout, newsFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Configure search request.
     */
    private void configureSearchRequest() {
        searchQuery.setQueryTerms(searchEdiText.getText().toString());
        this.configureCheckboxValues();
        searchQuery.setBeginDate(beginDateTextview.getText().toString());
        searchQuery.setEndDate(endDateTextview.getText().toString());

    }

    /**
     * check if the begin date isn't greater than the end date
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the boolean
     */
    private Boolean dateIsValid(String date1, String date2) {
        return date2.compareTo(date1) < 0;
    }

    /**
     * Configure checkbox values.
     */
    private void configureCheckboxValues() {
        if (checkBoxArts.isChecked()) searchQuery.setQueryFields(checkBoxArts.getText().toString());
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
        if (beginDateTextview.getText() == "") {
            String date = add0ToDate(day) + "/" + add0ToDate(month) + "/" + year;
            beginDateTextview.setText(date);
        }
        if (endDateTextview.getText() == "") {
            String date = add0ToDate(day) + "/" + add0ToDate(month) + "/" + year;
            endDateTextview.setText(date);
        }
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
