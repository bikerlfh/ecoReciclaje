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
import bikerlfh.ecorecycle.Model.DetalleMaterialActivity;
import bikerlfh.ecorecycle.R;

import bikerlfh.ecorecycle.Model.Clases.Busqueda;
import bikerlfh.ecorecycle.Model.Clases.Material;

/**
 * Created by Tatiana on 30/05/2016.
 */
public class RvAdapterMaterial extends RecyclerView.Adapter<RvAdapterMaterial.MaterialViewHolder> {
    public class MaterialViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cvMaterial;
        TextView titulo;
        ImageView imagen;

        public MaterialViewHolder(View itemView) {
            super(itemView);
            cvMaterial = (CardView)itemView.findViewById(R.id.cvMaterial);
            titulo = (TextView)itemView.findViewById(R.id.lbl_cv_titulo_material);
            imagen = (ImageView)itemView.findViewById(R.id.img_cv_material);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            // obtenemos la posicion del item donde se ha dado click
            int position = getPosition();
            // obtenemos el id del material
            int idMaterial = Busqueda.ListadoMaterial.get(position).getIdMaterial();
            // creamos el intent para abrir la actividad DetalleInformacionActivity
            Intent intent =new Intent(context,DetalleInformacionActivity.class);
            // Pasamos por parametro la posicion
            intent.putExtra(DetalleMaterialActivity.EXTRA_PARAMETER_ID_MATERIAL,idMaterial);
            context.startActivities(new Intent[]{intent});
        }
    }
    private Context context;

    public RvAdapterMaterial(Context context)
    {
        this.context = context;
    }
    @Override
    public int getItemCount() {
        return Busqueda.ListadoMaterial.size();
    }

    @Override
    public MaterialViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_material, viewGroup, false);
        MaterialViewHolder pvh = new RvAdapterMaterial.MaterialViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MaterialViewHolder materialViewHolder, int i) {
        Material material = Busqueda.ListadoMaterial.get(i);
        materialViewHolder.titulo.setText(material.getNombre());
        materialViewHolder.imagen.setImageResource(material.tipoMaterial.imagen);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
