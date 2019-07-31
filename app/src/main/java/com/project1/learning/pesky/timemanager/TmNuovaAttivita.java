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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project1.learning.pesky.timemanager.list_adapters.FavoriteAdapter;
import com.project1.learning.pesky.timemanager.list_adapters.FavoriteToggleButtonListener;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TmNuovaAttivita extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener, View.OnClickListener , FavoriteToggleButtonListener {

    List<AttivitaFavoriti> toRenderList;
    ListView listView ;
    EditText textViewNewFavActivity;
    FloatingActionButton addFavButton;
    FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tm_nuova_attivita);
        Toolbar myToolbar = findViewById(R.id.toolbar_nuova_attivita);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Nuova attività");
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        textViewNewFavActivity = findViewById(R.id.newFavActivityEditText);
        addFavButton = findViewById(R.id.saveFavButton);
        addFavButton.setOnClickListener(this);

        toRenderList = TmAttivitaGiornaliera.attivita;
        favoriteAdapter = new FavoriteAdapter(this,toRenderList);
        listView.setAdapter(favoriteAdapter);
        favoriteAdapter.setFavoriteToggleButtonListener(this);
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

        searchView.setOnQueryTextListener(this);

        return true;
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
        insertNewActivity();
    }

    // I due eventi di seguito possono essere tolti ed usati solo all'interno di un metodo
    // e togliere implements dalla classe
    // Scatta l'evetno submit quando viene inviata la ricerca
    @Override
    public boolean onQueryTextSubmit(String s)
    {
        insertNewActivity();
        return true;
    }

    // Ogni volta che viene digitato un carattere all'interno dell barra di ricerca scatta questo evento
    @Override
    public boolean onQueryTextChange(String s)
    {
        toRenderList = new ArrayList<>();
        if (s=="")
            toRenderList = TmAttivitaGiornaliera.attivita;

        for(AttivitaFavoriti el:TmAttivitaGiornaliera.attivita)
        {
            if(el.getNomeAttivita().contains(s))
            {
                toRenderList.add(el);
            }

        }
        favoriteAdapter = new FavoriteAdapter(this,toRenderList);
        listView.setAdapter(favoriteAdapter);
        favoriteAdapter.setFavoriteToggleButtonListener(this);
        favoriteAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onToggleFavoriteClick(int item)
    {
        toRenderList.get(item).toggleFavorito();
        Collections.sort(toRenderList, Collections.reverseOrder());
        favoriteAdapter.notifyDataSetChanged();;
    }

    public void insertNewActivity()
    {
        String toAddActivity = textViewNewFavActivity.getText().toString();
        Boolean activityIsPresent=false;
        for (AttivitaFavoriti a:TmAttivitaGiornaliera.attivita)
        {
            if (a.getNomeAttivita().compareToIgnoreCase(toAddActivity)==0)
                activityIsPresent = true;
        }
        if (!activityIsPresent)
            TmAttivitaGiornaliera.attivita.add(new AttivitaFavoriti(toAddActivity,false));
        else
            Toast.makeText(this, "Attivita già presente o attivita non valida", Toast.LENGTH_SHORT).show();
        favoriteAdapter.notifyDataSetChanged();
    }


}
