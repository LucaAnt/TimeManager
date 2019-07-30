package com.project1.learning.pesky.timemanager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project1.learning.pesky.timemanager.list_adapters.GiornataCorrenteAdapter;
import com.project1.learning.pesky.timemanager.model.Attivita;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;
import com.project1.learning.pesky.timemanager.model.Utility;
import com.project1.learning.pesky.timemanager.persistencev2.TmDatabaseAccessor;
import com.project1.learning.pesky.timemanager.persistencev2.TmRoomDb;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.project1.learning.pesky.timemanager.model.Attivita.Status.COMPLETED;
import static com.project1.learning.pesky.timemanager.model.Attivita.Status.PAUSED;
import static com.project1.learning.pesky.timemanager.model.Attivita.Status.RUNNING;
import static com.project1.learning.pesky.timemanager.model.Utility.getFormattedString;

public class TmAttivitaGiornaliera extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, GiornataCorrenteAdapter.TmAttivitaGiornalieraAdapterListener
{

    TmDatabaseAccessor roomDbAccessor;
    public static Giornata giornataCorrente;
    public static List<AttivitaFavoriti> attivita;
    GiornataCorrenteAdapter giornataAdapter;
    private Timer timer;
    private TextView orarioLavorativo,orarioAttivitaTotale;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tm_attivita_giornaliera);

        //Configura e Imposta la toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.attivitagiornalieratitolo);
        setSupportActionBar(toolbar);

        //Raccoglioe gli handler delle view da aggiornare
        orarioLavorativo = (TextView) findViewById(R.id.textViewOrarioLavorativoTotale);
        orarioAttivitaTotale = (TextView) findViewById(R.id.textViewOrarioAttivitaTotale);

        //carica la Giornata corrente e i favoriti dal DB
        roomDbAccessor = TmDatabaseAccessor.getInstance(this);



        try{
            TmAttivitaGiornaliera.giornataCorrente=TmAttivitaGiornaliera.giornataCorrente =this.roomDbAccessor.loadDay(Utility.getStringData(new Date()));
        }catch (NullPointerException e)
        {
            TmAttivitaGiornaliera.giornataCorrente= new Giornata();
        }


        TmAttivitaGiornaliera.attivita = this.roomDbAccessor.loadAllActivities();
        /*
        TmAttivitaGiornaliera.attivita.add(new AttivitaFavoriti("Riunione con piva",false));
        TmAttivitaGiornaliera.attivita.add(new AttivitaFavoriti("Riunione con Zorzetto",false));
        TmAttivitaGiornaliera.attivita.add(new AttivitaFavoriti("Riunione con zaza",false));
        */
        //Imposta l'adapter della list view per le entry delle attivita
        ListView listView = findViewById(R.id.ListViewAttivita);
        giornataAdapter = new GiornataCorrenteAdapter(this, this.giornataCorrente.getAttivita(),this);
        listView.setAdapter(giornataAdapter);
        listView.setOnItemClickListener(this);
        findViewById(R.id.floatingActionButton).setOnClickListener(this);

        //Crea e imposta il timer in un altro Task per aggiornare tutto ogni 1000 ms
        timer = new Timer();
        RefreshFieldsTimerTask  refreshTask= new RefreshFieldsTimerTask(this);

        timer.scheduleAtFixedRate(refreshTask, 0, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.roomDbAccessor.saveAllActivities(TmAttivitaGiornaliera.attivita);
        this.roomDbAccessor.saveDay(TmAttivitaGiornaliera.giornataCorrente);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.getStringExtra(CostantiAttivita.STR_NEW_ATTIVITA)!=null)
        {
            this.giornataCorrente.newAttivita(intent.getStringExtra(CostantiAttivita.STR_NEW_ATTIVITA));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_attivitagiornaliera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_calendar:
                Toast.makeText(this,"Click Calendar",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,TmCalendarPick.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                Toast.makeText(this,"Click Logout",Toast.LENGTH_LONG).show();
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId())
        {
            case R.id.floatingActionButton:
                //giornataDiProva.newAttivita("Dummy attivita "+giornataDiProva.getAttivita().size());
                intent = new Intent(this,TmNuovaAttivita.class);
                startActivity(intent);
                this.giornataAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        Toast.makeText(this,"Click Entry "+ position,Toast.LENGTH_LONG).show();
    }


    @Override
    public void startStopAttivita(int modelEntryId)
    {
        Attivita currAtt = this.giornataCorrente.getAttivita().get(modelEntryId);
        Attivita currentRunningAttivita;


        //Gestione della vecchia attivita
        if (this.giornataCorrente.hasRuningAttivita())
        {
            currentRunningAttivita = this.giornataCorrente.getCurrentRunningActivity();
            if (!currAtt.getNome().equals(currentRunningAttivita.getNome()))
            {   //Se il bottone playPause dell'attivita cliccata è un altro rispetto a quello dell'attivita correntemente in esecuzione impila la tranche e lo imposta a completato
                currentRunningAttivita.pauseAttivita();
                currentRunningAttivita.status = COMPLETED;
                currAtt.status = RUNNING;
                currAtt.startAttivita();

            } else {
                //Se se il bottone play cliccato corrispode all'attivita corrente
                switch(currentRunningAttivita.status)
                {
                    case PAUSED:
                        currentRunningAttivita.startAttivita();
                        currentRunningAttivita.status = RUNNING;
                        break;
                    case COMPLETED:
                        currentRunningAttivita.startAttivita();
                        currentRunningAttivita.status = RUNNING;
                        break;
                    case RUNNING:
                        currentRunningAttivita.pauseAttivita();
                        currentRunningAttivita.status = PAUSED;
                        break;
                    default:break;
                }
            }
        }else{
            //Se non ci sono altre attività già in esecuzione starta direttamente  la nuova
            this.giornataCorrente.completeAllActivity();
            currAtt.startAttivita();
            currAtt.status = RUNNING;
            }


        this.giornataAdapter.notifyDataSetChanged();
    }

    @Override
    public void editAttivita(int modelEntryId)
    {
        Intent intent = new Intent(this,TmModificaAttivita.class);
        intent.putExtra(CostantiAttivita.INT_ID_ATTIVITA_DA_MODIFICARE,modelEntryId);
        startActivity(intent);
        this.giornataAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteeAttivita(int modelEntryId)
    {
        this.giornataCorrente.getAttivita().remove(modelEntryId);
        this.giornataAdapter.notifyDataSetChanged();
        Toast.makeText(this,"Attivita cancellata " + modelEntryId,Toast.LENGTH_LONG).show();
    }

    public void refreshAll()
    {
        this.giornataCorrente.refreshTotalTime();
        orarioLavorativo.setText(getFormattedString(new Date ((new Date()).getTime() - this.giornataCorrente.getDataDiOggi().getTime())));
        orarioAttivitaTotale.setText(getFormattedString(this.giornataCorrente.getTotalTime()));
        this.giornataAdapter.notifyDataSetChanged();
    }



    //timer che gestisce il refresh
    class RefreshFieldsTimerTask extends TimerTask
    {
        TmAttivitaGiornaliera context=null;

        public RefreshFieldsTimerTask(TmAttivitaGiornaliera context) {
            this.context = context;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    context.refreshAll();
                }
            });
        }
    }


}
