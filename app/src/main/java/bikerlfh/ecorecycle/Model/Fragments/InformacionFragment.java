package bikerlfh.ecorecycle.Model.Fragments;

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

import bikerlfh.ecorecycle.R;

import bikerlfh.ecorecycle.Model.Adapter.RvAdapterInformacion;
import bikerlfh.ecorecycle.Model.Clases.Busqueda;
import bikerlfh.ecorecycle.Model.Clases.Informacion;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InformacionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class InformacionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView rvInformacion;

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

        Informacion informacion = new Informacion(activity);
        Busqueda.ListadoInformacion = informacion.consultarTodoInformacion();
        RvAdapterInformacion adapter = new RvAdapterInformacion(activity);
        rvInformacion.setAdapter(adapter);
        rvInformacion.setItemAnimator(new DefaultItemAnimator());

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
