package com.project1.learning.pesky.timemanager.list_adapters;


import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.project1.learning.pesky.timemanager.R;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<AttivitaFavoriti> {


        private Context mContext;
        FavoriteAdapter me;
        private List<AttivitaFavoriti> favoriteList = new ArrayList<>();

        public FavoriteAdapter( Context context, List<AttivitaFavoriti> list) {
            super(context, 0 , list);
            mContext = context;
            favoriteList = list;
            me =this;

        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.row,parent,false);

            AttivitaFavoriti currentAttivita = favoriteList.get(position);

            TextView nomeAttivita = (TextView) listItem.findViewById(R.id.textViewList);
            nomeAttivita.setText(currentAttivita.getNomeAttivita());

            ToggleButton preferitoAttivita = (ToggleButton) listItem.findViewById(R.id.toggleButton);
            preferitoAttivita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("PROVA","" +position);
                    favoriteList.get(position).toggleFavorito();
                    if(favoriteList.get(position).isFavorito() == true) {
                        v.setBackgroundResource(R.drawable.preferito_attivo);
                    } else {
                        v.setBackgroundResource(R.drawable.preferito_non_attivo);
                    }
                    me.notifyDataSetChanged();;
                }
            });

            if(currentAttivita.isFavorito() == true) {
                preferitoAttivita.setBackgroundResource(R.drawable.preferito_attivo);
            } else {
                preferitoAttivita.setBackgroundResource(R.drawable.preferito_non_attivo);
            }


            // Ordinamentto in base se una attività è preferita oppure no
            // Prima i preferiti, e poi il resto
            Collections.sort(favoriteList,Collections.reverseOrder());
            for(AttivitaFavoriti d:favoriteList) {
                Log.d("PROVA", "Lista " + d.getNomeAttivita());
            }


            return listItem;
        }

}
