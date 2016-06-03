package com.bikerlfh.ecoreciclaje.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.bikerlfh.ecoreciclaje.Model.SitioReciclajeModel;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class SitioReciclaje {

    private int idSitioReciclaje;
    private String nombre;
    private String direccion;
    private String telefono;
    private int idMunicipio;
    private double latitud;
    private double longitud;

    private DbManager dbManager;
    private ContentValues contentValues;

    // Objetos Embebidos
    public List<SitioReciclajeMaterial> ListadoSitioReciclajeMaterial;

    public SitioReciclaje(Context context) {
        dbManager= new DbManager(context);
        ListadoSitioReciclajeMaterial = new ArrayList<>();
    }

    public int getIdSitioReciclaje() {
        return idSitioReciclaje;
    }

    public void setIdSitioReciclaje(int idSitioReciclaje) {
        this.idSitioReciclaje = idSitioReciclaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public boolean insertSitioReciclaje(int idSitioReciclaje,String nombre,String direccion, String telefono,int idMunicipio,double latitud,double longitud){
        contentValues = new ContentValues();
        contentValues.put(SitioReciclajeModel.COLUMN_ID,idSitioReciclaje);
        contentValues.put(SitioReciclajeModel.COLUMN_NOMBRE,nombre);
        contentValues.put(SitioReciclajeModel.COLUMN_DIRECCION,direccion);
        contentValues.put(SitioReciclajeModel.COLUMN_TELEFONO,telefono);
        contentValues.put(SitioReciclajeModel.COLUMN_ID_MUNICIPIO,idMunicipio);
        contentValues.put(SitioReciclajeModel.COLUMN_LATITUD,latitud);
        contentValues.put(SitioReciclajeModel.COLUMN_LONGITUD,longitud);

        if(dbManager.Insert(SitioReciclajeModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }

    public boolean consultarSitioReciclajePorId(int idSitioReciclaje)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(SitioReciclajeModel.NAME_TABLE, new String[] { "*" },SitioReciclajeModel.COLUMN_ID + "=?",new String[] {String.valueOf(idSitioReciclaje)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idSitioReciclaje = idSitioReciclaje;
            this.nombre = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_NOMBRE));
            this.direccion = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_DIRECCION));
            this.telefono = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_TELEFONO));
            this.idMunicipio = c.getInt(c.getColumnIndex(SitioReciclajeModel.COLUMN_ID_MUNICIPIO));
            this.latitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LATITUD));
            this.longitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LONGITUD));
            this.ListadoSitioReciclajeMaterial = ConsultarSitioReciclajeMaterial(this.idSitioReciclaje);
            return true;
        }
        return false;
    }

    public boolean consultarSitioReciclajePorLatitudLongitud(double latitud,double longitud)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        //Cursor c = dbManager.RawQuery("SELECT * FROM "+ SitioReciclajeModel.NAME_TABLE + " WHERE "+SitioReciclajeModel.COLUMN_LATITUD + "='"+latitud+"' and "+SitioReciclajeModel.COLUMN_LONGITUD + "='"+longitud+"'",null);
        Cursor c = dbManager.Select(SitioReciclajeModel.NAME_TABLE, new String[] { "*" },SitioReciclajeModel.COLUMN_LATITUD + "=? and "+SitioReciclajeModel.COLUMN_LONGITUD + "=?",new String[] {String.valueOf(latitud),String.valueOf(longitud)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idSitioReciclaje = idSitioReciclaje;
            this.nombre = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_NOMBRE));
            this.direccion = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_DIRECCION));
            this.telefono = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_TELEFONO));
            this.idMunicipio = c.getInt(c.getColumnIndex(SitioReciclajeModel.COLUMN_ID_MUNICIPIO));
            this.latitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LATITUD));
            this.longitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LONGITUD));
            this.ListadoSitioReciclajeMaterial = ConsultarSitioReciclajeMaterial(this.idSitioReciclaje);
            return true;
        }
        return false;
    }

    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+ SitioReciclajeModel.COLUMN_ID+") AS "+SitioReciclajeModel.COLUMN_ID+" FROM "+SitioReciclajeModel.NAME_TABLE,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(SitioReciclajeModel.COLUMN_ID));
        }
        return 0;
    }

    public List<SitioReciclaje> consultarTodoSitioReciclaje()
    {
        List<SitioReciclaje> ListadoSitioReciclaje = new ArrayList<SitioReciclaje>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos
        Cursor c = dbManager.Select(SitioReciclajeModel.NAME_TABLE, new String[] { "*" },null,null,null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto sitio el cual se agrega a la ListadoSitioReciclaje
            do
            {
                SitioReciclaje sitio = new SitioReciclaje(this.dbManager.context);
                sitio.idSitioReciclaje = c.getInt(c.getColumnIndex(SitioReciclajeModel.COLUMN_ID));
                sitio.nombre = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_NOMBRE));
                sitio.direccion = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_DIRECCION));
                sitio.telefono = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_TELEFONO));
                sitio.idMunicipio = c.getInt(c.getColumnIndex(SitioReciclajeModel.COLUMN_ID_MUNICIPIO));
                sitio.latitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LATITUD));
                sitio.longitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LONGITUD));
                sitio.ListadoSitioReciclajeMaterial = ConsultarSitioReciclajeMaterial(sitio.idSitioReciclaje);
                ListadoSitioReciclaje.add(sitio);
            }
            while (c.moveToNext());
        }
        return ListadoSitioReciclaje;
    }

    public List<SitioReciclaje> consultarSitioReciclajePorIdMunicipio(int idMunicipio)
    {
        List<SitioReciclaje> ListadoSitioReciclaje = new ArrayList<SitioReciclaje>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos
        Cursor c = dbManager.Select(SitioReciclajeModel.NAME_TABLE, new String[] { "*" },SitioReciclajeModel.COLUMN_ID_MUNICIPIO,new String[] {String.valueOf(idMunicipio)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto sitio el cual se agrega a la ListadoSitioReciclaje
            do
            {
                SitioReciclaje sitio = new SitioReciclaje(this.dbManager.context);
                sitio.idSitioReciclaje = c.getInt(c.getColumnIndex(SitioReciclajeModel.COLUMN_ID));
                sitio.nombre = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_NOMBRE));
                sitio.direccion = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_DIRECCION));
                sitio.telefono = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_TELEFONO));
                sitio.idMunicipio = c.getInt(c.getColumnIndex(SitioReciclajeModel.COLUMN_ID_MUNICIPIO));
                sitio.latitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LATITUD));
                sitio.longitud = c.getDouble(c.getColumnIndex(SitioReciclajeModel.COLUMN_LONGITUD));
                sitio.ListadoSitioReciclajeMaterial = ConsultarSitioReciclajeMaterial(sitio.idSitioReciclaje);
                ListadoSitioReciclaje.add(sitio);
            }
            while (c.moveToNext());
        }
        return ListadoSitioReciclaje;
    }

    private List<SitioReciclajeMaterial> ConsultarSitioReciclajeMaterial(int idSitioReciclaje)
    {
        SitioReciclajeMaterial sitioReciclajeMaterial = new SitioReciclajeMaterial(this.dbManager.context);
        return sitioReciclajeMaterial.consultarSitioReciclajeMaterialPorIdSitioReciclaje(idSitioReciclaje);
    }

}
