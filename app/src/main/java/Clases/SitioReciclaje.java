package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Model.MaterialModel;
import Model.SitioReciclajeModel;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class SitioReciclaje {

    private int idSitioReciclaje;
    private String nombre;
    private String direccion;
    private String propietario;
    private String latitud;
    private String longitud;

    private DbManager dbManager;
    private ContentValues contentValues;

    public SitioReciclaje(Context context) {
        dbManager= new DbManager(context);
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

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public boolean insertSitioReciclaje(int idSitioReciclaje,String nombre,String direccion, String propietario,String latitud,String longitud){
        contentValues = new ContentValues();
        contentValues.put(SitioReciclajeModel.COLUMN_ID,idSitioReciclaje);
        contentValues.put(SitioReciclajeModel.COLUMN_NOMBRE,nombre);
        contentValues.put(SitioReciclajeModel.COLUMN_DIRECCION,direccion);
        contentValues.put(SitioReciclajeModel.COLUMN_PROPIETARIO,propietario);
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
        Cursor c = dbManager.Select(SitioReciclajeModel.NAME_TABLE, new String[] { SitioReciclajeModel.COLUMN_ID,
                                                                                   SitioReciclajeModel.COLUMN_NOMBRE,
                                                                                   SitioReciclajeModel.COLUMN_DIRECCION,
                                                                                   SitioReciclajeModel.COLUMN_PROPIETARIO,
                                                                                   SitioReciclajeModel.COLUMN_LATITUD,
                                                                                   SitioReciclajeModel.COLUMN_LONGITUD},SitioReciclajeModel.COLUMN_ID + "=?",new String[] {String.valueOf(idSitioReciclaje)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idSitioReciclaje = idSitioReciclaje;
            this.nombre = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_NOMBRE));
            this.direccion = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_DIRECCION));
            this.propietario = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_PROPIETARIO));
            this.latitud = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_LATITUD));
            this.longitud = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_LONGITUD));
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
        Cursor c = dbManager.Select(SitioReciclajeModel.NAME_TABLE, new String[] { SitioReciclajeModel.COLUMN_ID,
                                                                            SitioReciclajeModel.COLUMN_NOMBRE,
                                                                            SitioReciclajeModel.COLUMN_DIRECCION,
                                                                            SitioReciclajeModel.COLUMN_PROPIETARIO,
                                                                            SitioReciclajeModel.COLUMN_LATITUD,
                                                                            SitioReciclajeModel.COLUMN_LONGITUD },null,null,null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto sitio el cual se agrega a la ListadoSitioReciclaje
            do
            {
                SitioReciclaje sitio = new SitioReciclaje(this.dbManager.context);
                sitio.nombre = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_NOMBRE));
                sitio.direccion = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_DIRECCION));
                sitio.propietario = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_PROPIETARIO));
                sitio.latitud = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_LATITUD));
                sitio.longitud = c.getString(c.getColumnIndex(SitioReciclajeModel.COLUMN_LONGITUD));
                ListadoSitioReciclaje.add(sitio);
            }
            while (c.moveToNext());
        }
        return ListadoSitioReciclaje;
    }
}
