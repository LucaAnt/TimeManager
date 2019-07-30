package com.project1.learning.pesky.timemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class TmCalendarPick extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm_calendar_pick);
        //Configura e Imposta la toolbar
        Toolbar toolbar = findViewById(R.id.ToolbarCalendarPick);
        toolbar.setTitle(R.string.calendariogiornatetitolo);
        setSupportActionBar(toolbar);
        calendarView= findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);

        Log.d("getChildAt:",calendarView.getChildAt(0).toString() );

    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
    {
        String giornoStr = year + "/" + month+"/"+dayOfMonth;
        Log.d("Selected", year + "/" + month+"/"+dayOfMonth);
        Intent modifyDayIntent = new Intent(this,TmModificaAttivita.class);
        modifyDayIntent.putExtra(CostantiAttivita.INT_ID_GIORNO_STORICO_DA_MODIFICARE,giornoStr);
        startActivity(modifyDayIntent);
        this.finish();
    }


}
