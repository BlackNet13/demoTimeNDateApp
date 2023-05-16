package sg.rp.edu.rp.c346.id22038845.demotimendateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    TimePicker tp;
    Button btnDisplayDate;
    Button btnDisplayTime;
    Button btnReset;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link var to id
        dp=findViewById(R.id.datePicker);
        tp=findViewById(R.id.timePicker);
        btnDisplayDate=findViewById(R.id.buttonDisplayDate);
        btnDisplayTime=findViewById(R.id.buttonDisplayTime);
        tvDisplay=findViewById(R.id.textViewDisplay);
        btnReset=findViewById(R.id.resetBtn);

        //date/time picker settings:
        Calendar cal= Calendar.getInstance();
        //dp.setMinDate(System.currentTimeMillis());

        //has a limit, if a month does not have certain day it will over show till the next mth
        int maxDay = 30;
        int maxMonth = 6;
        int maxYear = 2023;
        cal.set(maxYear, maxMonth-1 , maxDay);
        dp.setMaxDate(cal.getTimeInMillis());

        //checks if time selection change
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

            }
        });

        btnDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText("Time is " + timeStr(tp.getCurrentHour(),tp.getCurrentMinute()));
            }
        });


        //check if date selection change
        dp.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                dp.init(year,month,day,null);

            }
        });

        btnDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvDisplay.setText("Date is " + dateStr(dp.getDayOfMonth(), dp.getMonth()+1, dp.getYear()));
            }
        });

        //reset Button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rDay = 1;
                int rMonth = 1-1;
                int rYear = 2020;
                dp.updateDate(rYear,rMonth,rDay);
                tp.setCurrentHour(0);
                tp.setCurrentMinute(0);

            }
        });
    }

    //converts time into string
    private String timeStr(int hour, int min){
        //allows you to show AM and PM, works when return string ampm with the hour and min in desired string format.
       /* String ampm ="AM";
        if(hour>=12){
            if(hour!=12){
                hour-=12;
            }
            ampm="PM";
        }else if(hour==0){
            hour=12;
        }*/
        return hour +" : "+ min;
    }

    //converts date into string
    private String dateStr(int day, int month, int year){
        return day+" / "+month+" / "+year;
    }

    //

    //converts month into mth in words to be used with dateStr when returning month: getMth(month)
   /*private String getMth(int mth){
        String strMth[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        return strMth[mth-1];
    }*/
}