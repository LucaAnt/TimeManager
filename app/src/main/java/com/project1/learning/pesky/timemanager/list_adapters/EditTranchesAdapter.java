package com.project1.learning.pesky.timemanager.list_adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.project1.learning.pesky.timemanager.R;
import com.project1.learning.pesky.timemanager.TmAttivitaGiornaliera;
import com.project1.learning.pesky.timemanager.model.Tranche;
import com.project1.learning.pesky.timemanager.model.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditTranchesAdapter extends ArrayAdapter {
    Context context;
    int layoutRes;
    List<Tranche> trancheList;
    int attivitaId=0;


    public EditTranchesAdapter(Context context, int resource, List<Tranche> trancheList,int attivitaId)
    {
        super(context, resource,trancheList);
        this.context = context;
        this.layoutRes = resource;
        this.trancheList = trancheList;
        this.attivitaId=attivitaId;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(context).inflate(R.layout.row_modifica,parent,false);

        Tranche currentTranche = trancheList.get(position);

        //Imposta le Views
        final EditText start = listItem.findViewById(R.id.textViewList1);

        start.setText(Utility.getFormattedString(currentTranche.getStart()));

        final EditText end= listItem.findViewById(R.id.textViewList2);

        end.setText(Utility.getFormattedString(currentTranche.getEnd()));

        TextView tranceDiff= listItem.findViewById(R.id.textViewList3);
        tranceDiff.setText(Utility.getFormattedString(currentTranche.getDiffEndStart()));

        ImageButton modifica = (ImageButton)listItem.findViewById(R.id.imageButton);
        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start1 = start.getText().toString();
                String end1 = end.getText().toString();
                Date start= null;
                Date end= null;
                try {
                    start = new SimpleDateFormat("HH:mm:ss").parse(start1);
                    end = new SimpleDateFormat("HH:mm:ss").parse(end1);
                    trancheList.set(position,new Tranche(start,end,TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(attivitaId).getId()));
                    //TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(attivitaId).getTranches().set(position,new Tranche(start,end,TmAttivitaGiornaliera.giornataCorrente.getAttivita().get(attivitaId).getId()));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return listItem;
    }
}