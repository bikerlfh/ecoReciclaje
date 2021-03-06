package com.bikerlfh.ecoreciclaje;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.bikerlfh.ecoreciclaje.Clases.SincronizarDatos;
import com.bikerlfh.ecoreciclaje.Clases.SitioReciclaje;

public class Principal extends AppCompatActivity {

    //private final int DURACION_SPLASH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this.deleteDatabase("ecoreciclaje.sqlite");

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Principal.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }

            ;
        }, DURACION_SPLASH);*/
        // Validamos el estado de internet
        if (isOnline())
            new AsyncSincronizar().execute("", "", "");
        else {
            Toast.makeText(Principal.this, "Por favor verifique su conexión a internet!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Principal.this, MenuPrincipal.class);
            startActivity(intent);
            finish();
        }
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

    class AsyncSincronizar extends AsyncTask<String,String,Boolean>
    {
        ProgressDialog pd = new ProgressDialog(Principal.this);
        SincronizarDatos sincronizarDatos = new SincronizarDatos(Principal.this);
        String resultado = "";
        @Override
        protected void onPreExecute() {
            pd.setMessage("Sincronizando Datos ...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean retorno = true;
            // sincroniza pais
            if (!sincronizarDatos.SincronizarPais())
            {
                resultado = sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza departamento
            if (!sincronizarDatos.SincronizarDepartamento())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza municipio
            if (!sincronizarDatos.SincronizarMunicipio())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza tipo informacion
            if (!sincronizarDatos.SincronizarTipoInformacion())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza tipo material
            if (!sincronizarDatos.SincronizarTipoMaterial())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza informacion
            if (!sincronizarDatos.SincronizarInformacion())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza material
            if (!sincronizarDatos.SincronizarMaterial())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza sitio reciclaje
            if (!sincronizarDatos.SincronizarSitioReciclaje())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            // sincroniza sitio reciclaje material
            if (!sincronizarDatos.SincronizarSitioReciclajeMaterial())
            {
                resultado += sincronizarDatos.getMessageError()+ ". ";
                retorno = false;
            }
            return retorno;
        }

        @Override
        protected void onPostExecute(Boolean proceso) {
            pd.dismiss();
            if (proceso)
                resultado = "Proceso de sincronización completado";
            Toast.makeText(Principal.this,resultado,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Principal.this, MenuPrincipal.class);
            startActivity(intent);
            finish();
        }
    }

    /***
     *
     * @return true si hay conexion a internet
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}


