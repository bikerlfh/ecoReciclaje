package com.bikerlfh.ecoreciclaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bikerlfh.ecoreciclaje.Clases.Material;

public class DetalleMaterialActivity extends AppCompatActivity {
    public static final String EXTRA_PARAMETER_ID_MATERIAL= "detallematerialactivity.idmaterial";
    private Material material;
    private TextView lblCodigo,lblNombre,lblTipoMaterial,lblDescripcion;
    private ImageView imgMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_material);

        lblCodigo = (TextView)findViewById(R.id.lbl_codigo_detallematerial);
        lblNombre = (TextView)findViewById(R.id.lbl_titulo_detallematerial);
        lblTipoMaterial = (TextView)findViewById(R.id.lbl_tipomaterial_detallematerial);
        lblTipoMaterial.setText("");
        lblDescripcion = (TextView)findViewById(R.id.lbl_descripcion_detallematerial);
        lblDescripcion.setMovementMethod(new ScrollingMovementMethod());
        imgMaterial = (ImageView)findViewById(R.id.img_detallematerial);

        int idMaterial = getIntent().getIntExtra(EXTRA_PARAMETER_ID_MATERIAL, 0);
        this.material = new Material(this);
        if (this.material.consultarMaterialPorId(idMaterial))
        {
            lblCodigo.setText(this.material.getCodigo());
            lblNombre.setText(this.material.getNombre());
            if (this.material.tipoMaterial.getIdTipoMaterial() > 0)
                lblTipoMaterial.setText(this.material.tipoMaterial.getCodigo() + " - " + this.material.tipoMaterial.getDescripcion());
            lblDescripcion.setText(this.material.getDescripcion());
            imgMaterial.setImageResource(this.material.imagen);

        }

    }
}
