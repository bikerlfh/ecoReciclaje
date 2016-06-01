package com.bikerlfh.ecoreciclaje;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bikerlfh.ecoreciclaje.Clases.Busqueda;
import com.bikerlfh.ecoreciclaje.Clases.SitioReciclaje;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.bikerlfh.ecoreciclaje.R;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SitioReciclaje sitioReciclaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        sitioReciclaje =  new SitioReciclaje(this);
        List<Address> addressList = null;
        Geocoder geocoder = new Geocoder(this);
        try {
            Address address = geocoder.getFromLocationName("COLOMBIA",1).get(0);
            LatLng colombia = new LatLng(address.getLatitude(), address.getLongitude());
            //.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(colombia));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // EVENTO DE CLICK AL MARCADOR
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                LatLng position = marker.getPosition();
                if (sitioReciclaje.consultarSitioReciclajePorLatitudLongitud(position.latitude,position.longitude))
                {
                    Toast.makeText(MapsActivity.this,sitioReciclaje.getNombre()+"\n"+sitioReciclaje.getDireccion(),Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        mostrarMarcadorSitiosReciclaje();
    }
    private void mostrarMarcadorSitiosReciclaje()
    {
        for (SitioReciclaje sitio: Busqueda.ListadoSitioReciclaje) {
            Double lat = sitio.getLatitud();
            Double lon = sitio.getLongitud();
            LatLng latLng = new LatLng(lat,lon);
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(sitio.getNombre())
                    .snippet("Dirección:" + sitio.getDireccion())
                    /*.icon(BitmapDescriptorFactory.fromResource(R.drawable.recycling))*/);
        }
    }
}
