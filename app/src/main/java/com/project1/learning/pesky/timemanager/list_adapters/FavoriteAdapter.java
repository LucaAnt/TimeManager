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

public class FavoriteAdapter extends ArrayAdapter<AttivitaFavoriti>
{


        private Context mContext;
        private List<AttivitaFavoriti> favoriteList;
        FavoriteToggleButtonListener favoriteClickListener;

        public FavoriteAdapter( Context context, List<AttivitaFavoriti> list) {
            super(context, 0 , list);
            mContext = context;
            favoriteList = list;

        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.row,parent,false);

            AttivitaFavoriti currentAttivita = favoriteList.get(position);

            TextView nomeAttivita = listItem.findViewById(R.id.textViewList);
            nomeAttivita.setText(currentAttivita.getNomeAttivita());

            ToggleButton preferitoAttivita = listItem.findViewById(R.id.toggleButton);
            preferitoAttivita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(favoriteList.get(position).isFavorito() == true) {
                        v.setBackgroundResource(R.drawable.preferito_attivo);
                    } else {
                        v.setBackgroundResource(R.drawable.preferito_non_attivo);
                    }
                    favoriteClickListener.onToggleFavoriteClick(position);
                }
            });

            if(currentAttivita.isFavorito()) {
                preferitoAttivita.setBackgroundResource(R.drawable.preferito_attivo);
            } else {
                preferitoAttivita.setBackgroundResource(R.drawable.preferito_non_attivo);
            }

            return listItem;
        }

        public void setFavoriteToggleButtonListener(FavoriteToggleButtonListener listener)
        {
            favoriteClickListener = listener;
        }

}
