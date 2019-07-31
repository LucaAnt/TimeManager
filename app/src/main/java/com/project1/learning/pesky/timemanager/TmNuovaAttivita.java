package com.project1.learning.pesky.timemanager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project1.learning.pesky.timemanager.list_adapters.FavoriteAdapter;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;

import java.util.ArrayList;
import java.util.List;

public class TmNuovaAttivita extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener, View.OnClickListener {

    List<AttivitaFavoriti> array,filtro;
    ListView listView ;
    EditText textViewNewFavActivity;
    FloatingActionButton addFavButton;
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
        renderList(TmAttivitaGiornaliera.attivita);

        textViewNewFavActivity = findViewById(R.id.newFavActivityEditText);
        addFavButton = findViewById(R.id.saveFavButton);
        addFavButton.setOnClickListener(this);
    }

    // I due eventi di seguito possono essere tolti ed usati solo all'interno di un metodo
    // e togliere implements dalla classe
    // Scatta l'evetno submit quando viene inviata la ricerca
    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.d("PROVA", "Inviato da activity : " + s);
        return false;
    }

    // Ogni volta che viene digitato un carattere all'interno dell barra di ricerca scatta questo evento
    @Override
    public boolean onQueryTextChange(String s)
    {
        if (s=="")
            filtro = TmAttivitaGiornaliera.attivita;

        for(AttivitaFavoriti el:TmAttivitaGiornaliera.attivita)
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
                Log.d("PROVA", "Inviato : " + s);
                return false;
            }

            // Ogni volta che viene digitato un carattere all'interno dell barra di ricerca scatta questo evento
            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("PROVA", "Cambiato : " + s);
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
        intent.putExtra(CostantiAttivita.STR_NEW_ATTIVITA, TmAttivitaGiornaliera.attivita.get(position).getNomeAttivita());
        startActivity(intent);
        finish();

    }

    @Override
    public void onClick(View v)
    {
        TmAttivitaGiornaliera.attivita.add(new AttivitaFavoriti(textViewNewFavActivity.getText().toString(),false));
    }
}
