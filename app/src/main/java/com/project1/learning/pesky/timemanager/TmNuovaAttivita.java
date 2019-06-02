package com.project1.learning.pesky.timemanager;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import com.project1.learning.pesky.timemanager.list_adapters.FavoriteAdapter;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TmNuovaAttivita extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener{

    List<AttivitaFavoriti> array,filtro;
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tm_nuova_attivita);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_nuova_attivita);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Nuova attività");

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        filtro = new ArrayList<>();
        renderList(DB.listaAttivita);
    }

    // I due eventi di seguito possono essere tolti ed usati solo all'interno di un metodo
    // e togliere implements dalla classe
    // Scatta l'evetno submit quando viene inviata la ricerca
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    // Ogni volta che viene digitato un carattere all'interno dell barra di ricerca scatta questo evento
    @Override
    public boolean onQueryTextChange(String s)
    {
        if (s=="")
            filtro = DB.listaAttivita;

        for(AttivitaFavoriti el:DB.listaAttivita)
        {
            if(el.getNomeAttivita().contains(s))
                filtro.add(el);
        }

        renderList(filtro);
        filtro = new ArrayList<>();
        return true;
    }
    public void renderList(List<AttivitaFavoriti> l)
    {
        FavoriteAdapter arrayAdapter = new FavoriteAdapter(this,l);
        listView.setAdapter(arrayAdapter);
    }

    // Viene creato il menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Prendo l'oggetto menu_main_new_attivita e lo inserisco all'interno di menu gli elementi di menu_main_new_attivita
        getMenuInflater().inflate(R.menu.menu_main_new_attivita, menu);

        // Recupero l'oggetto searchItem con action_ricerca all'interno della creazione del menu
        MenuItem searchItem = menu.findItem(R.id.action_ricerca);

        // Recuperiamo dalla searchItem l'evento ActionView
        final SearchView searchView = (SearchView) searchItem.getActionView();

        // Inizializzo un Listener richiamando l'interfaccia (interface) OnQueryTeextListener che contiene due metodi
        // dell'interfaccia che sono due eventi
        SearchView.OnQueryTextListener mioListener = new SearchView.OnQueryTextListener() {

            // Scatta l'evetno submit quando viene inviata la ricerca
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            // Ogni volta che viene digitato un carattere all'interno dell barra di ricerca scatta questo evento
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        };

        // Associamo questo listener al componente grafico searcView
        searchView.setOnQueryTextListener(mioListener);
        // E' possibile avere più listener, questo è implementato dalla interfaccia della classe
        searchView.setOnQueryTextListener(this);

        return true;
    }

    // Viene chiamato quando selezioniamo un item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_ricerca) {

         }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Recupero l'attività selezionata
        Intent intent = new Intent(this, TmAttivitaGiornaliera.class);
        intent.putExtra(CostantiAttivita.STR_NEW_ATTIVITA, DB.listaAttivita.get(position).getNomeAttivita());
        startActivity(intent);
        finish();

    }



}
