package com.project1.learning.pesky.timemanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.project1.learning.pesky.timemanager.model.Giornata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, GiornataCorrenteAdapter.TmAttivitaGiornalieraAdapterListener
{
    Giornata giornataDiProva;
    GiornataCorrenteAdapter giornataAdapter;
    private Timer timer;
    private TextView orarioLavorativo,orarioAttivitaTotale;
    private boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configura e Imposta la toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.attivitagiornalieratitolo);
        setSupportActionBar(toolbar);

        //Raccoglioe gli handler delle view da aggiornare
        orarioLavorativo = (TextView) findViewById(R.id.textViewOrarioLavorativoTotale);
        orarioAttivitaTotale = (TextView) findViewById(R.id.textViewOrarioAttivitaTotale);

        //Instanzia una giornata dummy
        giornataDiProva = new Giornata();
        /*
        giornataDiProva.newAttivita("Servizio Clienti");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Servizio Spedizione");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
       giornataDiProva.newAttivita("Attuario");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Servizio Vendita");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Riunione con Fain");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Riunione con Zazza");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Riunione con Piva");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Riunione con Pascolutti");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Riunione con Gola");
        giornataDiProva.getCurrentAttivita().addDummyTranches();
        giornataDiProva.newAttivita("Riunione con Di Santo");
        giornataDiProva.getCurrentAttivita().addDummyTranches();*/
        //Fine giornata Dummy

        ListView listView = findViewById(R.id.ListViewAttivita);
        giornataAdapter = new GiornataCorrenteAdapter(this, giornataDiProva.getAttivita(),this);
        listView.setAdapter(giornataAdapter);
        listView.setOnItemClickListener(this);
        findViewById(R.id.floatingActionButton).setOnClickListener(this);

        //Refresh Fields
        timer = new Timer();
        RefreshFieldsTimerTask  refreshTask= new RefreshFieldsTimerTask(this);

        timer.scheduleAtFixedRate(refreshTask, 0, 1000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.status = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.status=false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_calendar:
                Toast.makeText(this,"Click Calendar",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_logout:
                Toast.makeText(this,"Click Logout",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.floatingActionButton:
                giornataDiProva.newAttivita("Dummy attivita "+giornataDiProva.getAttivita().size());
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
        Attivita currAtt = this.giornataDiProva.getAttivita().get(modelEntryId);
        Attivita oldAtt =  giornataDiProva.getCurrentAttivita();
        if(!currAtt.getNome().equals(oldAtt.getNome()))
        {
            oldAtt.status = Attivita.Status.COMPLETED;
            if(oldAtt.hasRunningTranche())
                oldAtt.pauseAttivita();
            giornataDiProva.refreshOldAttivita(currAtt.getNome());

        }
        switch(currAtt.status)
        {
            case PAUSED:currAtt.status = Attivita.Status.RUNNING;
                currAtt.startAttivita();
                break;
            case COMPLETED:
                currAtt.startAttivita();
                currAtt.status = Attivita.Status.RUNNING;
                break;
            case RUNNING:
                currAtt.pauseAttivita();
                currAtt.status = Attivita.Status.PAUSED;
                break;
            default:break;

        }
        this.giornataAdapter.notifyDataSetChanged();
    }

    @Override
    public void editAttivita(int modelEntryId)
    {
        this.giornataAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteeAttivita(int modelEntryId)
    {
        this.giornataDiProva.getAttivita().remove(modelEntryId);
        this.giornataAdapter.notifyDataSetChanged();
        Toast.makeText(this,"Attivita cancellata " + modelEntryId,Toast.LENGTH_LONG).show();
    }

    public void refreshAll()
    {
        Date total;
        //Log.d("Timer:", (new Date())+"");

        total =new Date ((new Date()).getTime() - this.giornataDiProva.getDataDiOggi().getTime());
        orarioLavorativo.setText(getFormattedString(total));

        orarioAttivitaTotale.setText(getFormattedString(this.giornataDiProva.getTotalTime()));

    }

    public static String getFormattedString(Date d)
    {
        return d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
    }

    class RefreshFieldsTimerTask extends TimerTask {
        MainActivity context=null;

        public RefreshFieldsTimerTask(MainActivity context) {
            this.context = context;
        }

        public void run() {
            context.refreshAll();
        }
    }


}
