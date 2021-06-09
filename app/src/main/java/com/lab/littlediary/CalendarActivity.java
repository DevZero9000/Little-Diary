package com.lab.littlediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    CalendarView diaryCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        diaryCalendarView = findViewById(R.id.diaryCalendarView);
        diaryCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView v, int year, int month, int dayOfMonth) {
                Toast.makeText(CalendarActivity.this, month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });
    }
}