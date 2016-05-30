package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tatisramos.ecoreciclaje.DetalleInformacionActivity;
import com.example.tatisramos.ecoreciclaje.R;

import Clases.Busqueda;
import Clases.Informacion;

/**
 * Created by fercho on 5/29/2016.
 */
public class RvAdapterManualidad extends RecyclerView.Adapter<RvAdapterManualidad.ManualidadViewHolder>{
        public class ManualidadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cvManualidad;
        TextView titulo;
        TextView fecha;
        ImageView imagen;

        public ManualidadViewHolder(View itemView) {
            super(itemView);
            cvManualidad = (CardView)itemView.findViewById(R.id.cvManualidad);
            titulo = (TextView)itemView.findViewById(R.id.lbl_cv_titulo_manualidad);
            fecha = (TextView)itemView.findViewById(R.id.lbl_cv_fecha_manualidad);
            imagen = (ImageView)itemView.findViewById(R.id.img_cv_manualidad);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // obtenemos la posicion del item donde se ha dado click
            int position = getPosition();
            // obtenemos el id de la informacion
            int idInformacion = Busqueda.ListadoInformacionManualidad.get(position).getIdInformacion();
            // creamos el intent para abrir la actividad DetalleInformacionActivity
            Intent intent =new Intent(context,DetalleInformacionActivity.class);
            // Pasamos por parametro la posicion
            intent.putExtra(DetalleInformacionActivity.EXTRA_PARAMETER_ID_INFORMACION,idInformacion);
            context.startActivities(new Intent[]{intent});
        }
    }
    private Context context;

    public RvAdapterManualidad(Context context)
    {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Busqueda.ListadoInformacionManualidad.size();
    }

    @Override
    public ManualidadViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_manualidad, viewGroup, false);
        ManualidadViewHolder pvh = new ManualidadViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(ManualidadViewHolder manualidadViewHolder, int position) {
        Informacion info = Busqueda.ListadoInformacionManualidad.get(position);
        manualidadViewHolder.titulo.setText(info.getTitulo());
        manualidadViewHolder.fecha.setText(info.getFecha());
        manualidadViewHolder.imagen.setImageResource(info.tipoInformacion.imagen);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
