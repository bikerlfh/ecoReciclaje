package com.bikerlfh.ecoreciclaje.Clases;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;

import android.location.LocationListener;
import android.widget.Toast;

import com.bikerlfh.ecoreciclaje.Fragments.SitioReciclajeFragment;


/**
 * Created by fercho on 6/2/2016.
 */
public class MyLocationListener implements LocationListener {
    SitioReciclajeFragment sitioReciclajeFragment;

    public MyLocationListener(SitioReciclajeFragment mainActivity) {
        this.sitioReciclajeFragment = mainActivity;
    }
    @Override
    public void onLocationChanged(Location loc) {
        // Este método se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la detecci—n de un cambio de ubicacion
        this.sitioReciclajeFragment.setLocation(loc);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es desactivado
        Toast.makeText(this.sitioReciclajeFragment.getActivity(),"GPS Desactivado",Toast.LENGTH_LONG);
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es activado
        Toast.makeText(this.sitioReciclajeFragment.getActivity(),"GPS Activado",Toast.LENGTH_LONG);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Este mŽtodo se ejecuta cada vez que se detecta un cambio en el
        // status del proveedor de localizaci—n (GPS)
        // Los diferentes Status son:
        // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
        // TEMPORARILY_UNAVAILABLE -> Temp˜ralmente no disponible pero se
        // espera que este disponible en breve
        // AVAILABLE -> Disponible
    }
}
