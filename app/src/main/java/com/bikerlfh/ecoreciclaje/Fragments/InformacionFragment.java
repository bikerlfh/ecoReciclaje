package com.bikerlfh.ecoreciclaje.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.tv.TvView;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bikerlfh.ecoreciclaje.Clases.SincronizarDatos;
import com.bikerlfh.ecoreciclaje.Clases.TipoInformacion;
import com.bikerlfh.ecoreciclaje.MenuPrincipal;
import com.bikerlfh.ecoreciclaje.R;

import com.bikerlfh.ecoreciclaje.Adapter.RvAdapterInformacion;
import com.bikerlfh.ecoreciclaje.Clases.Busqueda;
import com.bikerlfh.ecoreciclaje.Clases.Informacion;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InformacionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class InformacionFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener {

    private OnFragmentInteractionListener mListener;
    RecyclerView rvInformacion;
    private SwipeRefreshLayout swipeRefreshLayout;

    public InformacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informacion, container, false);

        /******************* RecicleView *******************/
        rvInformacion = (RecyclerView)view.findViewById(R.id.rvInformacion);
        rvInformacion.setHasFixedSize(true);
        // Asignamos un layoud Manager
        // Obtenemos la actividad donde se carga el fragment
        Activity activity =  getActivity();
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        rvInformacion.setLayoutManager(llm);
        /******************* RecicleView *******************/
        // Se consultan las noticias
        visualizarDatos();
        // find the layout
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        // sets the colors used in the refresh animation
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                                                    android.R.color.holo_green_light,
                                                    android.R.color.holo_orange_light,
                                                    android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
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

    @Override
    public void onRefresh() {
        new AsyncRefresh().execute("");
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

    /**
     * Consulta la infromación de Tipo 01-Noticia y la visualiza en el recivleView rvInformacion.
     */
    private void visualizarDatos()
    {
        TipoInformacion tipoInformacion = new TipoInformacion(getActivity());
        Informacion informacion = new Informacion(getActivity());
        // consultamos el tipo de informacion 01 - NOTICIAS
        if (tipoInformacion.consultarTipoInformacionPorCodigo("01")) {
            // Se consultan todo la informacion de tipo 01- NOTICIAS
            Busqueda.ListadoInformacion = informacion.consultarInformacionPorIdTipoInformacion(tipoInformacion.getIdTipoInformacion());
        }
        else {
            // Si no se encuentra el tipo informacion 01 -NOTICIAS se carga la lista con todo el tipo de informacion
            Busqueda.ListadoInformacion = informacion.consultarTodoInformacion();
        }
        RvAdapterInformacion adapter = new RvAdapterInformacion(getActivity());
        rvInformacion.setAdapter(adapter);
        rvInformacion.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Clase Para refrescar datos de forma asincrona
     */
    class AsyncRefresh extends AsyncTask<String,String,Boolean>
    {
        SincronizarDatos sincronizarDatos = new SincronizarDatos(getActivity());
        @Override
        protected Boolean doInBackground(String... params) {
            Boolean retorno = true;
            // Se sincroniza el tipo información
            if(!sincronizarDatos.SincronizarTipoInformacion()) {
                retorno = false;
            }
            // Se sincroniza el tipo material
            if(!sincronizarDatos.SincronizarTipoMaterial()){
                retorno = false;
            }
            // Se sincroniza los materiales
            if(!sincronizarDatos.SincronizarMaterial()){
                retorno = false;
            }
            // Se sincroniza las informaciónes
            if(!sincronizarDatos.SincronizarInformacion()){
                retorno = false;
            }
            return retorno;
        }
        @Override
        protected void onPostExecute(Boolean resultado) {
            super.onPostExecute(resultado);
            swipeRefreshLayout.setRefreshing(false);
            // Si retrona false, se visualiza el mensaje de error
            if (!resultado){
                Toast.makeText(getActivity(),sincronizarDatos.getMessageError(),Toast.LENGTH_LONG).show();
            }
            else {
                // Se vuelve a cargar los datos
                visualizarDatos();
            }
        }
    }
}
