package com.project1.learning.pesky.timemanager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.project1.learning.pesky.timemanager.list_adapters.EditTranchesAdapter;

public class TmModificaAttivita extends AppCompatActivity {


    ArrayAdapter<String> arrayAdapter;
    int idAttivita;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_attivita);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Modifica attività");
        idAttivita = getIntent().getIntExtra(CostantiAttivita.INT_ID_ATTIVITA_DA_MODIFICARE,0);


        note = (EditText)findViewById(R.id.noteText);

        // Get reference of widgets from XML layout
         ListView lv = (ListView) findViewById(R.id.listViewModifica);
         FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.addButton);


        // Create an ArrayAdapter from List
        arrayAdapter = new EditTranchesAdapter(this, R.layout.row_modifica, TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(idAttivita).getTranches());

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Aggiungo una tranche alla attività selezionata, ho modificato il model di attivita il metodo addDummyTranches
                TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(idAttivita).addManualTranche();
                arrayAdapter.notifyDataSetChanged();
            }

        });

    }

    // Salvataggio delle modifiche e passo l'attività modificata

    public void onClickModifica(View v) {
        // Chiama l'activity Classifica
        //Intent intent = new Intent(this, SecondaActivity.class);
        //intent.putExtra("messaggio1", messaggio1);
        //startActivity(intent);

        // Recupero le note dell'attività
        String noteAttivita = note.getText().toString();
    }
}
