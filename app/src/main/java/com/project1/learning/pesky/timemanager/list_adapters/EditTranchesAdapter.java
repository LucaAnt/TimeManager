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
import com.project1.learning.pesky.timemanager.model.Tranche;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditTranchesAdapter extends ArrayAdapter {
    Context context;
    List<Tranche> toDisplayTranche;
    int layoutRes;


    public EditTranchesAdapter(Context context, int resource,List<Tranche> list) {
        super(context, resource, list);

        this.context = context;
        this.layoutRes = resource;
        this.toDisplayTranche = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(context).inflate(R.layout.row_modifica,parent,false);

        Tranche currentTranche = this.toDisplayTranche.get(position);

        //Imposta le Views
        final EditText start = listItem.findViewById(R.id.textViewList1);

        start.setText(currentTranche.getStart().getHours() +":" +currentTranche.getStart().getMinutes()+":" +currentTranche.getStart().getSeconds());

        final EditText end= listItem.findViewById(R.id.textViewList2);

        end.setText(currentTranche.getEnd().getHours() +":" +currentTranche.getEnd().getMinutes()+":" +currentTranche.getEnd().getSeconds());

        TextView tranceDiff= listItem.findViewById(R.id.textViewList3);
        Log.d("DIFF", currentTranche.getDiffEndStart().getHours() +":" +currentTranche.getDiffEndStart().getMinutes()+":" +currentTranche.getDiffEndStart().getSeconds());
        tranceDiff.setText(currentTranche.getDiffEndStart().getHours() +":" +currentTranche.getDiffEndStart().getMinutes()+":" +currentTranche.getDiffEndStart().getSeconds());

        ImageButton modifica = (ImageButton)listItem.findViewById(R.id.imageButton);
        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start1 = start.getText().toString();
                String end1 = end.getText().toString();
                Date date1= null;
                Date date2= null;
                try {
                    date1 = new SimpleDateFormat("HH:mm:ss").parse(start1);
                    date2 = new SimpleDateFormat("HH:mm:ss").parse(end1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d("PROVA", "onClick: " + date1 + " - " + date2);
            }
        });

        return listItem;
    }
}