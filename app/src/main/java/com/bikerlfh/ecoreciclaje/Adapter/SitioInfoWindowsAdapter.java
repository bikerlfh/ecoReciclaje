package com.bikerlfh.ecoreciclaje.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bikerlfh.ecoreciclaje.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by fercho on 6/2/2016.
 */
public class SitioInfoWindowsAdapter implements GoogleMap.InfoWindowAdapter
{
    private View popup = null;
    private LayoutInflater inflater=null;

    public SitioInfoWindowsAdapter(LayoutInflater inflater) {
        this.inflater=inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return(null);
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (popup == null) {
            popup=inflater.inflate(R.layout.popup, null);
        }

        TextView tv=(TextView)popup.findViewById(R.id.title);

        tv.setText(marker.getTitle());
        tv=(TextView)popup.findViewById(R.id.snippet);
        tv.setText(marker.getSnippet());

        return(popup);
    }
}
