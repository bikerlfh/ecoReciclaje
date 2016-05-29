package Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tatisramos.ecoreciclaje.R;

import java.util.List;

import Clases.Busqueda;
import Clases.Informacion;

/**
 * Created by fercho on 5/29/2016.
 */
public class RvAdapterInformacion extends RecyclerView.Adapter<RvAdapterInformacion.InformacionViewHolder>{
    public static class InformacionViewHolder extends RecyclerView.ViewHolder {
        CardView cvInformacion;
        TextView titulo;
        TextView fecha;
        ImageView imagen;

        InformacionViewHolder(View itemView) {
            super(itemView);
            cvInformacion = (CardView)itemView.findViewById(R.id.cvInformacion);
            titulo = (TextView)itemView.findViewById(R.id.lbl_titulo_informacion);
            fecha = (TextView)itemView.findViewById(R.id.lbl_fecha_informacion);
            imagen = (ImageView)itemView.findViewById(R.id.imgIformacion);
        }
    }
    //List<Informacion> ListaInformacion;

    public RvAdapterInformacion(){
        //this.ListaInformacion = informacions;
    }

    @Override
    public int getItemCount() {
        return Busqueda.ListadoInformacion.size();
    }

    @Override
    public InformacionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_informacion, viewGroup, false);
        InformacionViewHolder pvh = new InformacionViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(InformacionViewHolder informacionViewHolder, int i) {
            Informacion info = Busqueda.ListadoInformacion.get(i);
            informacionViewHolder.titulo.setText(info.getTitulo());
            informacionViewHolder.fecha.setText(info.getFecha());
            informacionViewHolder.imagen.setImageResource(info.tipoInformacion.imagen);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
