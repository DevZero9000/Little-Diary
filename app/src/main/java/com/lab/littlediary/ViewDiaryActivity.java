package com.lab.littlediary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class ViewDiaryActivity extends AppCompatActivity {

    String diaryTagline, diaryEntry;
    int date, month, year;

    TextView dateDisplay, tagline, entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary);

        dateDisplay = findViewById(R.id.dateViewDiary);
        tagline = findViewById(R.id.taglineViewDiary);
        entry = findViewById(R.id.entryViewDiary);

        Intent intent = getIntent();
        date = intent.getIntExtra("date", 0);
        month = intent.getIntExtra("month", 0);
        year = intent.getIntExtra("year", 0);

        diaryTagline = intent.getStringExtra("diaryTagline");
        diaryEntry = intent.getStringExtra("diaryEntry");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        String monthLongName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

        dateDisplay.setText(date + " " + monthLongName.substring(0, 3));
        tagline.setText(diaryTagline);
        entry.setText(diaryEntry);
    }
}