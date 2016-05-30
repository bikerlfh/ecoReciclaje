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

import com.bikerlfh.ecoreciclaje.DetalleInformacionActivity;
import com.bikerlfh.ecoreciclaje.R;

import com.bikerlfh.ecoreciclaje.Clases.Busqueda;
import com.bikerlfh.ecoreciclaje.Clases.Informacion;

/**
 * Created by Tatiana on 29/05/2016.
 */
public class RvAdapterTips extends RecyclerView.Adapter<RvAdapterTips.TipsViewHolder> {
    public class TipsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CardView cvTips;
    TextView titulo;
    TextView fecha;
    ImageView imagen;

        public TipsViewHolder(View itemView) {
            super(itemView);
            cvTips = (CardView)itemView.findViewById(R.id.cvTips);
            titulo = (TextView)itemView.findViewById(R.id.lbl_cv_titulo_tips);
            fecha = (TextView)itemView.findViewById(R.id.lbl_cv_fecha_tips);
            imagen = (ImageView)itemView.findViewById(R.id.img_cv_tips);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // obtenemos la posicion del item donde se ha dado click
            int position = getPosition();
            // obtenemos el id de la informacion
            int idInformacion = Busqueda.ListadoInformacionTips.get(position).getIdInformacion();
            // creamos el intent para abrir la actividad DetalleInformacionActivity
            Intent intent =new Intent(context,DetalleInformacionActivity.class);
            // Pasamos por parametro la posicion
            intent.putExtra(DetalleInformacionActivity.EXTRA_PARAMETER_ID_INFORMACION,idInformacion);
            context.startActivities(new Intent[]{intent});
        }
    }
    private Context context;

    public RvAdapterTips(Context context)
    {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Busqueda.ListadoInformacionTips.size();
    }

    @Override
    public TipsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_tips, viewGroup, false);
        TipsViewHolder pvh = new RvAdapterTips.TipsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TipsViewHolder tipsViewHolder, int position) {
        Informacion info = Busqueda.ListadoInformacionTips.get(position);
        tipsViewHolder.titulo.setText(info.getTitulo());
        tipsViewHolder.fecha.setText(info.getFecha());
        tipsViewHolder.imagen.setImageResource(info.tipoInformacion.imagen);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



}
