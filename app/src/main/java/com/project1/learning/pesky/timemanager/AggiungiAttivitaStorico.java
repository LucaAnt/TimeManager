package com.project1.learning.pesky.timemanager;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ListView;

public class AggiungiAttivitaStorico extends TmNuovaAttivita {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aggiungi_attivita_storico);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Nuova attività storico");

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

}
