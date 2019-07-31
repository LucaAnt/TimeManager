package com.project1.learning.pesky.timemanager.list_adapters;

import android.content.Context;
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
import com.project1.learning.pesky.timemanager.model.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailTranchesAdapter extends ArrayAdapter {
    Context context;
    List<Tranche> toDisplayTranche;
    int layoutRes;


    public DetailTranchesAdapter(Context context, int resource, List<Tranche> list) {
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
                listItem = LayoutInflater.from(context).inflate(R.layout.row_dettaglio,parent,false);

        final Tranche currentTranche = this.toDisplayTranche.get(position);

        //Imposta le Views
        final TextView start = listItem.findViewById(R.id.textViewList1);


        start.setText(Utility.getFormattedString(currentTranche.getStart()));

        final TextView end= listItem.findViewById(R.id.textViewList2);

        end.setText(Utility.getFormattedString(currentTranche.getEnd()));

        TextView tranceDiff= listItem.findViewById(R.id.textViewList3);
        tranceDiff.setText(Utility.getFormattedString(currentTranche.getDiffEndStart()));

        return listItem;
    }
}