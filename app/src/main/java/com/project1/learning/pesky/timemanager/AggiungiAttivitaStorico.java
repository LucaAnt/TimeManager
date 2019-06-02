package com.project1.learning.pesky.timemanager;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ListView;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;

import java.util.ArrayList;

public class AggiungiAttivitaStorico extends TmNuovaAttivita {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aggiungi_attivita_storico);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Nuova attività storico");

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        filtro = new ArrayList<>();
        array = new ArrayList<>();

        array.add(new AttivitaFavoriti("Pausa caffe", true));
        array.add(new AttivitaFavoriti("Meeting", false));
        array.add(new AttivitaFavoriti("Pausa pranza", true));
        array.add(new AttivitaFavoriti("Assistenza clienti", false));
        array.add(new AttivitaFavoriti("Pausa caffe", true));
        array.add(new AttivitaFavoriti("Pausa caffe", true));
        array.add(new AttivitaFavoriti("Pausa caffe", true));
        array.add(new AttivitaFavoriti("Pausa caffe", true));
        renderList(array);

    }

}
