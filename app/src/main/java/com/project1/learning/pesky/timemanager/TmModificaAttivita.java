package com.project1.learning.pesky.timemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project1.learning.pesky.timemanager.list_adapters.EditTranchesAdapter;

public class TmModificaAttivita extends AppCompatActivity implements View.OnClickListener
{


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

        // Get reference of widgets from XML layout
        ListView lv = (ListView) findViewById(R.id.listViewModifica);
        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.addButton);
        FloatingActionButton saveBtn = (FloatingActionButton) findViewById(R.id.saveTrancheButton);
        addBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);


        idAttivita =(int) getIntent().getIntExtra(CostantiAttivita.INT_ID_ATTIVITA_DA_MODIFICARE,0);

        note = (EditText)findViewById(R.id.noteText);
        note.setText(TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(this.idAttivita).getDescrizione());




        // Create an ArrayAdapter from List
        arrayAdapter = new EditTranchesAdapter(this, R.layout.row_modifica, TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(idAttivita).getTranches(),idAttivita);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);
    }




    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addButton:
                // Aggiungie una tranche alla attività selezionata, ho modificato il model di attivita il metodo addDummyTranches
                TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(idAttivita).addManualTranche();
                arrayAdapter.notifyDataSetChanged();
                break;
            // Salvataggio delle modifiche e passo l'attività modificata
            case R.id.saveTrancheButton:
                // Recupero le note dell'attività
                TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(this.idAttivita).setDescrizione(note.getText().toString());
                Toast.makeText(this,"Attivita Modificata",Toast.LENGTH_LONG).show();
                finish();
                break;
                default:
                    break;
        }
    }
}