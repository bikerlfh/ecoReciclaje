package com.example.tatisramos.ecoreciclaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import Adapter.RvAdapterInformacion;
import Clases.Busqueda;
import Clases.Informacion;

public class InformacionActivity2 extends AppCompatActivity {

    RecyclerView rvInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion2);

        /******************* RecicleView *******************/
        rvInformacion = (RecyclerView)findViewById(R.id.rvInformacion);
        rvInformacion.setHasFixedSize(true);
        // Asignamos un layoud Manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvInformacion.setLayoutManager(llm);
        /******************* RecicleView *******************/
        Informacion informacion = new Informacion(this);
        Busqueda.ListadoInformacion = informacion.consultarTodoInformacion();
        RvAdapterInformacion adapter = new RvAdapterInformacion();
        rvInformacion.setAdapter(adapter);
    }
}
