package com.bikerlfh.ecoreciclaje.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;

import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bikerlfh.ecoreciclaje.Adapter.RvAdapterListaMaterial;
import com.bikerlfh.ecoreciclaje.Clases.Busqueda;
import com.bikerlfh.ecoreciclaje.Clases.Material;
import com.bikerlfh.ecoreciclaje.Clases.MyLocationListener;
import com.bikerlfh.ecoreciclaje.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SitioReciclajeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SitioReciclajeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvMaterial;
    private EditText textMyLocation;

    public SitioReciclajeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sitio_reciclaje, container, false);
       // textMyLocation = (EditText) view.findViewById(R.id.txt_my_location);
        /******************* RecicleView *******************/
        rvMaterial = (RecyclerView) view.findViewById(R.id.rvListadoMaterial);
        rvMaterial.setHasFixedSize(true);

        // Asignamos un layoud Manager
        // Obtenemos la actividad donde se carga el fragment
        Activity activity = getActivity();
        GridLayoutManager lLayout = new GridLayoutManager(activity, 4);
        rvMaterial.setLayoutManager(lLayout);

        /******************* RecicleView *******************/

        Material material = new Material(activity);
        Busqueda.ListadoMaterial = material.consultarTodoMaterial();
        RvAdapterListaMaterial adapter = new RvAdapterListaMaterial(activity);
        rvMaterial.setAdapter(adapter);
        rvMaterial.setItemAnimator(new DefaultItemAnimator());

        /* LOCALIZACION**/
        /*
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(activity, "El Gps se encuenta apagado.\nFavor Ingrese su ubicación", Toast.LENGTH_LONG).show();
        } else {
            MyLocationListener myLocationListener = new MyLocationListener(this);
            int a  = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED &&
                                                  ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_DENIED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) myLocationListener);
            }
        }*/
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void setLocation(Location location)
    {
        //Obtener la direcci—n de la calle a partir de la latitud y la longitud
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(getActivity(),  Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    textMyLocation.setText(address.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
