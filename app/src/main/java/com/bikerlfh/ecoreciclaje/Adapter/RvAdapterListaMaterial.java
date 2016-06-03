package com.bikerlfh.ecoreciclaje.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bikerlfh.ecoreciclaje.Clases.Busqueda;
import com.bikerlfh.ecoreciclaje.Clases.Material;
import com.bikerlfh.ecoreciclaje.MapsActivity;
import com.bikerlfh.ecoreciclaje.R;

/**
 * Created by fercho on 6/2/2016.
 */
public class RvAdapterListaMaterial extends RecyclerView.Adapter<RvAdapterListaMaterial.ListaMaterialViewHolder> {
    public class ListaMaterialViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cvListaMaterial;
        TextView titulo;
        ImageView imagen;

        public ListaMaterialViewHolder(View itemView) {
            super(itemView);
            cvListaMaterial = (CardView)itemView.findViewById(R.id.cardview_listamaterial);
            titulo = (TextView)itemView.findViewById(R.id.lbl_cv_titulo_listamaterial);
            imagen = (ImageView)itemView.findViewById(R.id.img_cv_listamaterial);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            // obtenemos la posicion del item donde se ha dado click
            int position = getPosition();
            // obtenemos el id del material
            int idMaterial = Busqueda.ListadoMaterial.get(position).getIdMaterial();
            // creamos el intent para abrir la actividad DetalleInformacionActivity
            Intent intent =new Intent(context,MapsActivity.class);
            // Pasamos por parametro la posicion
            intent.putExtra(MapsActivity.EXTRA_PARAMETER_ID_MATERIAL,idMaterial);
            context.startActivities(new Intent[]{intent});
        }
    }

    private Context context;
    public RvAdapterListaMaterial(Context context)
    {
        this.context = context;
    }
    @Override
    public int getItemCount() {
        return Busqueda.ListadoMaterial.size();
    }

    @Override
    public ListaMaterialViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_lista_material, viewGroup, false);
        ListaMaterialViewHolder pvh = new RvAdapterListaMaterial.ListaMaterialViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ListaMaterialViewHolder listaMaterialViewHolder, int i) {
        Material mat = Busqueda.ListadoMaterial.get(i);
        listaMaterialViewHolder.titulo.setText(mat.getNombre());
        listaMaterialViewHolder.imagen.setImageResource(mat.imagen);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
