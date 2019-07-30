package com.project1.learning.pesky.timemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project1.learning.pesky.timemanager.list_adapters.EditTranchesAdapter;

public class TmModificaAttivita extends AppCompatActivity {


    ArrayAdapter<String> arrayAdapter;
    int idAttivita;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_attivita);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Modifica attività");
        setSupportActionBar(toolbar);

        idAttivita =(int) getIntent().getIntExtra(CostantiAttivita.INT_ID_ATTIVITA_DA_MODIFICARE,0);

        note = (EditText)findViewById(R.id.noteText);
        note.setText(TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(this.idAttivita).getDescrizione());

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
        // Recupero le note dell'attività
        TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(this.idAttivita).setDescrizione(note.getText().toString());
        finish();
    }
}