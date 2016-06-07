package com.bikerlfh.ecoreciclaje;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.bikerlfh.ecoreciclaje.Fragments.SitioReciclajeFragment;
import com.bikerlfh.ecoreciclaje.R;

import com.bikerlfh.ecoreciclaje.Fragments.InformacionFragment;
import com.bikerlfh.ecoreciclaje.Fragments.ManualidadFragment;
import com.bikerlfh.ecoreciclaje.Fragments.MaterialFragment;
import com.bikerlfh.ecoreciclaje.Fragments.TipsFragment;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   InformacionFragment.OnFragmentInteractionListener,
                   TipsFragment.OnFragmentInteractionListener,
                   ManualidadFragment.OnFragmentInteractionListener,
                   MaterialFragment.OnFragmentInteractionListener , SitioReciclajeFragment.OnFragmentInteractionListener{

    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
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
        fab.hide();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        InformacionFragment informacionFragment = new InformacionFragment();
        loadFragment(informacionFragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

    @SuppressWarnings("StatementWithEmptyBody")

    @Override

    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        //Intent intent = null;
        // Objeto para cargar el fragment seleccionado
        android.support.v4.app.Fragment newFragment = null;
        switch (item.getItemId())
        {
            case R.id.nav_informacion:
                newFragment = new InformacionFragment();
            break;
            case R.id.nav_tips:
                newFragment = new TipsFragment();
            break;
            case R.id.nav_manualidades:
                newFragment = new ManualidadFragment();
            break;
            case R.id.nav_reciclaje:
                newFragment = new MaterialFragment();
            break;
            case R.id.nav_lugares:
                newFragment = new SitioReciclajeFragment();
            break;
        }
        // Se valida que el fragment no este cargado
        if (newFragment != null) {
            // Se obtiene el fragment actualmente cargado
            android.support.v4.app.Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.layout_principal);
            // si no hay fragment cargado O el fragment cargado es diferente al nuevo
            // se carga el fragment
            if (currentFragment == null || !currentFragment.getClass().getName().equalsIgnoreCase(newFragment.getClass().getName())) {
                loadFragment(newFragment);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void loadFragment(android.support.v4.app.Fragment newFragment)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_principal, newFragment,newFragment.getClass().getName())
                .addToBackStack("F_MAIN")
                .commit();
    }
}