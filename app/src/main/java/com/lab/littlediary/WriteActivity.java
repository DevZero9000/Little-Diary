package com.lab.littlediary;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class WriteActivity extends AppCompatActivity {

    TextView dateDisplay;
    EditText diaryTaglineEditText;
    EditText diaryEntryEditText;
    Button btnSaveEntry;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        dateDisplay = findViewById(R.id.dateDisplayWrite);
        diaryTaglineEditText = findViewById(R.id.diaryTaglineWrite);
        diaryEntryEditText = findViewById(R.id.diaryEntryMultiLineWrite);
        btnSaveEntry = findViewById(R.id.btnSaveWrite);

        Intent intent = getIntent();
        int date = intent.getIntExtra("date", 0);
        int month = intent.getIntExtra("month", 0);
        int year = intent.getIntExtra("year", 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        String monthLongName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

        dateDisplay.setText(dayLongName + " " + date + " " + monthLongName + " " + year);

        btnSaveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tagline = diaryTaglineEditText.getText().toString();
                String entry = diaryEntryEditText.getText().toString();

                if (TextUtils.isEmpty(tagline)) {
                    diaryTaglineEditText.setError("Please write the tagline for your entry");
                    return;
                }

                if (TextUtils.isEmpty(entry)) {
                    diaryTaglineEditText.setError("Please write your diary entry");
                    return;
                }

                Intent intent = new Intent(WriteActivity.this, MainActivity.class);
                intent.putExtra("diaryTagline", tagline);
                intent.putExtra("diaryEntry", entry);

                intent.putExtra("date", date);
                intent.putExtra("month", month);
                intent.putExtra("year", year);

                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}