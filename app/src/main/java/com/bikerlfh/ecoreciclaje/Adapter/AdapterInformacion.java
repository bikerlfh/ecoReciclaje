package com.bikerlfh.ecoreciclaje.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import bikerlfh.ecorecycle.R;

import com.bikerlfh.ecoreciclaje.Clases.Busqueda;
import com.bikerlfh.ecoreciclaje.Clases.Informacion;

/**
 * Created by fercho on 5/22/2016.
 */
public class AdapterInformacion extends BaseAdapter
{
    private Context context;
    public AdapterInformacion(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Busqueda.ListadoInformacion.size();
    }

    @Override
    public Informacion getItem(int position) {
        return Busqueda.ListadoInformacion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getIdInformacion();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item2, viewGroup, false);
        }

        TextView lblTitulo = (TextView) view.findViewById(R.id.lblTitulo);
        TextView lblFecha = (TextView) view.findViewById(R.id.lblFecha);

        final Informacion item = getItem(position);

        lblTitulo.setText(context.getString(R.string.title_titulo) + ": " + item.getTitulo());
        lblFecha.setText(context.getString(R.string.title_fecha)+": "+item.getFecha());
        return view;
    }
}
