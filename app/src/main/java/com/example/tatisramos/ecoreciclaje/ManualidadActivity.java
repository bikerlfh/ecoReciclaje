package com.example.tatisramos.ecoreciclaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;

import Adapter.RvAdapterInformacion;
import Adapter.RvAdapterManualidad;
import Clases.Busqueda;
import Clases.Informacion;
import Clases.TipoInformacion;

public class ManualidadActivity extends AppCompatActivity {

    RecyclerView rvManualidad;
    Informacion informacion;
    TipoInformacion tipoInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualidad);

        /******************* RecicleView *******************/
        rvManualidad = (RecyclerView) findViewById(R.id.rvManualidad);
        rvManualidad.setHasFixedSize(true);
        // Asignamos un layoud Manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvManualidad.setLayoutManager(llm);
        /******************* RecicleView *******************/

        tipoInformacion = new TipoInformacion(this);
        // Se Consulta el tipo de informacion manualidad
        if (tipoInformacion.consultarTipoInformacionPorCodigo("03"))
        {
            Informacion informacion = new Informacion(this);
            Busqueda.ListadoInformacionManualidad = informacion.consultarInformacionPorIdTipoInformacion(tipoInformacion.getIdTipoInformacion());
            if (Busqueda.ListadoInformacionManualidad.size() == 0)
            {
                Toast.makeText(this,"No se encontró ninguna manualidad registrada",Toast.LENGTH_LONG);
                //finish();
            }
            RvAdapterManualidad adapter = new RvAdapterManualidad(this);
            rvManualidad.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(this,"No se encontro el tipo de información (03 - MANUALIDAD)",Toast.LENGTH_LONG);
        }
    }
}
