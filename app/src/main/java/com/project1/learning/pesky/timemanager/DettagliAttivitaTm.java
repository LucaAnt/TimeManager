package com.project1.learning.pesky.timemanager;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DettagliAttivitaTm extends TmModificaAttivita {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_attivita);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Dettagli attività");

        ListView listView = (ListView)findViewById(R.id.listViewModifica);

        String [] array = {"09:00","09:00","09:00","09:00"};
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, R.layout.row_modifica, R.id.textViewList1, array);
        listView.setAdapter(arrayAdapter);


    }
}
