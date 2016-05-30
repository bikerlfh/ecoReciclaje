package com.example.tatisramos.ecoreciclaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import Clases.Busqueda;
import Clases.Informacion;

public class DetalleInformacionActivity extends AppCompatActivity {
    public static final String EXTRA_PARAMETER_POSICION = "detalleinformacionactivity.itemposicion";
    private Informacion informacion;
    private TextView lblTitulo,lblDescripcion,lblMaterial,lblFecha;
    private ImageView imgInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_informacion);

        lblTitulo = (TextView)findViewById(R.id.lbl_titulo_detalleinformacion);
        lblDescripcion = (TextView)findViewById(R.id.lbl_descripcion_detalleinformacion);
        lblMaterial = (TextView)findViewById(R.id.lbl_material_detalleinformacion);
        lblMaterial.setText("");
        lblFecha = (TextView)findViewById(R.id.lbl_fecha_detalleinformacion);
        imgInformacion = (ImageView)findViewById(R.id.img_detalleinformacion);

        // Obtener ña posicion con el identificador establecido en la actividad principal
        int posicion = getIntent().getIntExtra(EXTRA_PARAMETER_POSICION, 0);
        this.informacion = Busqueda.ListadoInformacion.get(posicion);
        if (this.informacion != null)
        {
            lblTitulo.setText(this.informacion.getTitulo());
            lblDescripcion.setText(this.informacion.getDescripcion());
            if (this.informacion.material.getIdMaterial() > 0)
                lblMaterial.setText(this.informacion.material.getCodigo() + " - " + this.informacion.material.getNombre());

            lblFecha.setText(this.informacion.getFecha());
            imgInformacion.setImageResource(this.informacion.tipoInformacion.imagen);

        }

    }
}
