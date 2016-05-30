package com.example.tatisramos.ecoreciclaje;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import Adapter.RvAdapterManualidad;
import Clases.Busqueda;
import Clases.Informacion;
import Clases.TipoInformacion;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManualidadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ManualidadFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView rvManualidad;
    TipoInformacion tipoInformacion;

    public ManualidadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manualidad, container, false);

        /******************* RecicleView *******************/
        rvManualidad = (RecyclerView)view.findViewById(R.id.rvManualidad);
        rvManualidad.setHasFixedSize(true);
        // Asignamos un layoud Manager
        // Obtenemos la actividad donde se carga el fragment
        Activity activity =  getActivity();
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        rvManualidad.setLayoutManager(llm);
        /******************* RecicleView *******************/
        tipoInformacion = new TipoInformacion(activity);
        // Se Consulta el tipo de informacion manualidad
        if (tipoInformacion.consultarTipoInformacionPorCodigo("03"))
        {
            Informacion informacion = new Informacion(activity);
            Busqueda.ListadoInformacionManualidad = informacion.consultarInformacionPorIdTipoInformacion(tipoInformacion.getIdTipoInformacion());
            if (Busqueda.ListadoInformacionManualidad.size() == 0)
            {
                Toast.makeText(activity,"No se encontró ninguna manualidad registrada",Toast.LENGTH_LONG);
            }
            RvAdapterManualidad adapter = new RvAdapterManualidad(activity);
            rvManualidad.setAdapter(adapter);
            rvManualidad.setItemAnimator(new DefaultItemAnimator());
        }
        else
        {
            Toast.makeText(activity,"No se encontro el tipo de información (03 - MANUALIDAD)",Toast.LENGTH_LONG);
        }
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
}
