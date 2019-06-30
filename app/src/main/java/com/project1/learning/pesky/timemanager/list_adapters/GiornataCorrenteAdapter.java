package com.project1.learning.pesky.timemanager.list_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project1.learning.pesky.timemanager.DB;
import com.project1.learning.pesky.timemanager.TmAttivitaGiornaliera;
import com.project1.learning.pesky.timemanager.R;
import com.project1.learning.pesky.timemanager.model.Attivita;
import java.util.List;

import static com.project1.learning.pesky.timemanager.model.Utility.getFormattedString;


public class GiornataCorrenteAdapter extends ArrayAdapter
{
    private Context mContext;
    private List<Attivita> attivitaList;
    TmAttivitaGiornalieraAdapterListener attivitaGiornalieraListener;
    ImageButton  btnPlayPause;
    View listItem;

    public GiornataCorrenteAdapter(Context context,List<Attivita> list,TmAttivitaGiornalieraAdapterListener attivitaGiornalieraListener)
    {
        super(context, 0 , list);
        mContext = context;
        attivitaList = list;
        this.attivitaGiornalieraListener = attivitaGiornalieraListener;
    }


    public View getView(final int position, View convertView, ViewGroup parent)
    {
        listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listview_entry_attivita,parent,false);

        final Attivita currentAttivita = attivitaList.get(position);

        //Gestione pallino colorato
        TextView pallino = (TextView) listItem.findViewById(R.id.textViewPallino);
        switch (currentAttivita.status)
        {
            case PAUSED:
                pallino.setTextColor(mContext.getResources().getColor(R.color.colorAttivitaPausa));
                break;
            case COMPLETED:
                pallino.setTextColor(mContext.getResources().getColor(R.color.colorAttivitaCompleta));
                break;
            case RUNNING:
                pallino.setTextColor(mContext.getResources().getColor(R.color.colorAttivitaAttiva));
            break;
            default:break;

        }


        //Gestione labesl
        TextView nomeAttivita = (TextView) listItem.findViewById(R.id.textViewNomeAttivita);
        nomeAttivita.setText(currentAttivita.getNome());

        TextView tempoTotaleAttivita = (TextView) listItem.findViewById(R.id.textViewTimer);
        tempoTotaleAttivita.setText(getFormattedString(currentAttivita.getTempoTotaleAttivita()));


        //Gestione Bottoni interni
        //Bottone Play/Pause/Resume
        btnPlayPause = listItem.findViewById(R.id.AttivitaEntryPlayPause);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Click Play/Pause on " + position,Toast.LENGTH_LONG).show();
                attivitaGiornalieraListener.startStopAttivita(position);
            }
        });

        //Gestione Dinamica Bottoni Play/Pause/Resume e label timer
        switch (currentAttivita.status)
        {
            case PAUSED:
                btnPlayPause.setImageResource(R.drawable.music_player_play);
                break;
            case COMPLETED:
                btnPlayPause.setImageResource(R.drawable.music_player_play);
                break;
            case RUNNING:
                btnPlayPause.setImageResource(R.drawable.pause);
                break;
            default:break;
        }

        ImageButton  btnEdit = listItem.findViewById(R.id.AttivitaEntryEdit);
        if (!TmAttivitaGiornaliera.giornataCorrente.hasRuningAttivita())
        {

            btnEdit.setClickable(true);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Click edit on " + position, Toast.LENGTH_LONG).show();
                    attivitaGiornalieraListener.editAttivita(position);

                }
            });
        }else{
            btnEdit.setClickable(false);
        }

        ImageButton  btnDelete = listItem.findViewById(R.id.AttivitaEntryDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attivitaGiornalieraListener.deleteeAttivita(position);
            }
        });


        return listItem;
    }



    public void setMyListener(TmAttivitaGiornalieraAdapterListener AttivitaGiornalieraListener) {
        this.attivitaGiornalieraListener = AttivitaGiornalieraListener;
    }
    public  interface  TmAttivitaGiornalieraAdapterListener
    {
        public void startStopAttivita(int modelEntryId);
        public void editAttivita(int modelEntryId);
        public void deleteeAttivita(int modelEntryId);
    }

}
