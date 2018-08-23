package ltd.kaizo.mynews.Controller.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import ltd.kaizo.mynews.Controller.Activities.MainActivity;
import ltd.kaizo.mynews.R;

public class SearchFragment extends BaseFragment {
    @BindView(R.id.fragment_search_begin_date)
    TextView beginDateTextview;
    @BindView(R.id.fragment_search_end_date)
    TextView endDateTextview;
    @BindView(R.id.fragment_search_button)
    Button searchButton;
    private DatePickerDialog.OnDateSetListener beginDateSetListener;
    private DatePickerDialog.OnDateSetListener endDateSetListener;
    private int year;
    private int day;
    private int month;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void configureDesign() {
        this.configureCalendar();
        this.configureDatePicker();
        this.configureSearchButton();
    }

    @Override
    protected void updateDesign() {

    }

        private void configureSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getContext(), MainActivity.class);
                startActivity(mainActivity);

            }
        });

    }
    private void configureDatePicker() {

        beginDateTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        beginDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        beginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String beginDate = dayOfMonth + "/" + month + "/" + year;
                beginDateTextview.setText(beginDate);
            }
        };
        endDateTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        endDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String endDate = dayOfMonth + "/" + month + "/" + year;
                endDateTextview.setText(endDate);
            }
        };
    }

    private void configureCalendar() {
        Calendar cal = Calendar.getInstance();
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        if (beginDateTextview.getText() == "") {
            String date = day + "/" + month + "/" + year;
            beginDateTextview.setText(date);
        }
        if (endDateTextview.getText() == "") {
            String date = day + "/" + month + "/" + year;
            endDateTextview.setText(date);
        }
    }
}
