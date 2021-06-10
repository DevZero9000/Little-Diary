package com.lab.littlediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EntryAdapter.ListItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    String diaryTagline, diaryEntry;
    int date, month, year;

    FloatingActionButton fabCreateDiaryEntry;

    ArrayList<EntryHelper> entryArrayList = new ArrayList<>();

    RecyclerView entryRecycler;
    RecyclerView.Adapter entryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        date = intent.getIntExtra("date", 0);
        month = intent.getIntExtra("month", 0);
        year = intent.getIntExtra("year", 0);

        diaryTagline = intent.getStringExtra("diaryTagline");
        diaryEntry = intent.getStringExtra("diaryEntry");

        fabCreateDiaryEntry = findViewById(R.id.fabCreateDiaryEntry);

        fabCreateDiaryEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        entryRecycler = findViewById(R.id.entryRecyclerView);
        entryRecycler();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }

        return true;
    }

    private void entryRecycler() {

        entryRecycler.setHasFixedSize(false);
        entryRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        if (diaryTagline != null) {
            entryArrayList.add(new EntryHelper(diaryTagline, date, month, year));
        }
        entryAdapter = new EntryAdapter(entryArrayList, this);

        if (!entryArrayList.isEmpty()) {
            entryRecycler.setAdapter(entryAdapter);
        }
    }

    @Override
    public void onListClick(int clickedItemIndex) {
        Intent intent;

        for (int i = 0; i < entryArrayList.size(); i++) {
            if (clickedItemIndex == i) {
                intent = new Intent(MainActivity.this, ViewDiaryActivity.class);
                intent.putExtra("diaryTagline", diaryTagline);
                intent.putExtra("diaryEntry", diaryEntry);

                intent.putExtra("date", date);
                intent.putExtra("month", month);
                intent.putExtra("year", year);

                startActivity(intent);
            }
        }
    }
}