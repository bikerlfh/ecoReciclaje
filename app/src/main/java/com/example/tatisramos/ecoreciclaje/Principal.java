package com.example.tatisramos.ecoreciclaje;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import Clases.Informacion;
import Clases.SincronizarDatos;
import Clases.WebService;

public class Principal extends AppCompatActivity {

    //private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this.deleteDatabase("ecoreciclaje.sqlite");

        new AsyncSincronizar().execute("", "", "");
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Principal.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }

            ;
        }, DURACION_SPLASH);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AsyncSincronizar extends AsyncTask<String,String,String>
    {
        ProgressDialog pd = new ProgressDialog(Principal.this);
        SincronizarDatos sincronizarDatos = new SincronizarDatos(Principal.this);
        String resultado = "",resTipoInfo,resTipoMaterial,resInformacion;
        @Override
        protected void onPreExecute() {
            pd.setMessage("Sincronizando Datos ...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            resTipoInfo = sincronizarDatos.SincronizarTipoInformacion();
            resTipoMaterial = sincronizarDatos.SincronizarTipoMaterial();
            resInformacion = sincronizarDatos.SincronizarInformacion();
            if (resTipoInfo.contains("Error"))
            {
                resultado = resTipoInfo + ". ";
            }
            if (resTipoMaterial.contains("Error"))
            {
                resultado += resTipoMaterial+ ". ";
            }
            if (resInformacion.contains("Error"))
            {
                resultado += resInformacion+ ". ";
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            if (resultado.length() == 0)
                resultado = "Proceso de sincronización completado";
            Toast.makeText(Principal.this,resultado,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Principal.this, MenuPrincipal.class);
            startActivity(intent);
            finish();
        }
    }
}


