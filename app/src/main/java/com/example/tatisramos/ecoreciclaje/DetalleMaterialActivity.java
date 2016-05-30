package com.example.tatisramos.ecoreciclaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import Clases.Material;

public class DetalleMaterialActivity extends AppCompatActivity {
    public static final String EXTRA_PARAMETER_ID_MATERIAL= "detallematerialactivity.idmaterial";
    private Material material;
    private TextView lblNombre,lblCodigo,lblTipoMaterial,lblDescripcion;
    private ImageView imgMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_material);

        lblNombre = (TextView)findViewById(R.id.lbl_titulo_detallematerial);
        lblDescripcion = (TextView)findViewById(R.id.lbl_descripcion_detallematerial);
        lblTipoMaterial = (TextView)findViewById(R.id.lbl_tipomaterial_detallematerial);
        lblTipoMaterial.setText("");
        lblCodigo = (TextView)findViewById(R.id.lbl_codigo_detallematerial);
        imgMaterial = (ImageView)findViewById(R.id.img_detallematerial);

        int idMaterial = getIntent().getIntExtra(EXTRA_PARAMETER_ID_MATERIAL, 0);
        this.material = new Material(this);
        if (this.material.consultarMaterialPorId(idMaterial))
        {
            lblNombre.setText(this.material.getNombre());
            lblDescripcion.setText(this.material.getDescripcion());

            if (this.material.tipoMaterial.getIdTipoMaterial() > 0)
                lblTipoMaterial.setText(this.material.tipoMaterial.getCodigo() + " - " + this.material.tipoMaterial.getDescripcion());

            lblCodigo.setText(this.material.getCodigo());
            imgMaterial.setImageResource(this.material.tipoMaterial.imagen);

        }

    }
}
