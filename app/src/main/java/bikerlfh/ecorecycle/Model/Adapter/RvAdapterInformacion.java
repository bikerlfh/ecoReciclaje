package bikerlfh.ecorecycle.Model.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bikerlfh.ecorecycle.Model.DetalleInformacionActivity;
import bikerlfh.ecorecycle.R;

import bikerlfh.ecorecycle.Model.Clases.Busqueda;
import bikerlfh.ecorecycle.Model.Clases.Informacion;

/**
 * Created by fercho on 5/29/2016.
 */
public class RvAdapterInformacion extends RecyclerView.Adapter<RvAdapterInformacion.InformacionViewHolder>{
    public  class InformacionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cvInformacion;
        TextView titulo;
        TextView fecha;
        ImageView imagen;

        public InformacionViewHolder(View itemView) {
            super(itemView);
            cvInformacion = (CardView)itemView.findViewById(R.id.cvInformacion);
            titulo = (TextView)itemView.findViewById(R.id.lbl_cv_titulo_informacion);
            fecha = (TextView)itemView.findViewById(R.id.lbl_cv_fecha_informacion);
            imagen = (ImageView)itemView.findViewById(R.id.img_cv_informacion);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // obtenemos la posicion del item donde se ha dado click
            int position = getPosition();
            int idInformacion = Busqueda.ListadoInformacion.get(position).getIdInformacion();
            // creamos el intent para abrir la actividad DetalleInformacionActivity
            Intent intent =new Intent(context,DetalleInformacionActivity.class);
            // Pasamos por parametro la posicion
            intent.putExtra(DetalleInformacionActivity.EXTRA_PARAMETER_ID_INFORMACION,idInformacion);
            context.startActivities(new Intent[]{intent});

        }
    }
    //List<Informacion> ListaInformacion;
    private Context context;
    public RvAdapterInformacion(Context context){
        //this.ListaInformacion = informacions;
        this.context = context;
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
