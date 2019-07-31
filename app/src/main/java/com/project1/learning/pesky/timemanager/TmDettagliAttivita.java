package com.project1.learning.pesky.timemanager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.project1.learning.pesky.timemanager.list_adapters.DetailTranchesAdapter;
import com.project1.learning.pesky.timemanager.model.Utility;

public class TmDettagliAttivita extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    int idAttivita;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_attivita);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Dettagli attività");
        ListView lv = findViewById(R.id.listViewModifica);

        TextView tempoTotale = (TextView) findViewById(R.id.tempoTotale);
        idAttivita = getIntent().getIntExtra(CostantiAttivita.INT_ID_ATTIVITA_DA_MODIFICARE,0);

        // Estrapolo dalla stringa della data recuperata solo il tempo e setto la TextView del tempototale
        tempoTotale.setText(Utility.getFormattedString( TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(this.idAttivita).getTempoTotaleAttivita()));

        TextView descrizione = (TextView) findViewById(R.id.descrizioneAttivita);
        descrizione.setText(TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(this.idAttivita).getDescrizione());



        Log.d("ITEM:", idAttivita +"");
        // Get reference of widgets from XML layout


        // Create an ArrayAdapter from List
        arrayAdapter = new DetailTranchesAdapter(this, R.layout.row_dettaglio, TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(idAttivita).getTranches());

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);
    }
}