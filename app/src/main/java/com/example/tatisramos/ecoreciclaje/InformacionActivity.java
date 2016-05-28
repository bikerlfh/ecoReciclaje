package com.example.tatisramos.ecoreciclaje;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterInformacion;
import Clases.Busqueda;
import Clases.Informacion;
import Clases.WebService;

public class InformacionActivity extends AppCompatActivity {
    private Informacion informacion;
    private ListView gridViewInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        gridViewInformacion = (ListView)findViewById(R.id.gviewInformacion);
        // Se obtienen toda la informacion y se asignan al atributo statico ListadoInformacion de la clase Busqueda
        informacion = new Informacion(this);
        Busqueda.ListadoInformacion = informacion.consultarTodoInformacion();
        AdapterInformacion adapterInformacion = new AdapterInformacion(this);
        gridViewInformacion.setAdapter(adapterInformacion);
        //gridViewInformacion.setOnItemClickListener(this);
    }




}
