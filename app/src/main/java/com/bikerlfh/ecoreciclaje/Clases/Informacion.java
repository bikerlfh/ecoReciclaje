package com.bikerlfh.ecoreciclaje.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.bikerlfh.ecoreciclaje.Model.InformacionModel;

/**
 * Created by TatisRamos on 13/05/2016.
 * Modificated by Luis Fernando Henriquez Arciniegas on 25/08/2016
 */
public class Informacion
{
    private int idInformacion;
    private int idTipoInformacion;
    private int idMaterial;
    private String titulo;
    private String descripcion;
    private String fecha;

    private DbManager dbManager;
    private ContentValues contentValues;

    /** Propiedades Publicas */
    public TipoInformacion tipoInformacion;
    public Material material;

    public Informacion(Context context) {
        dbManager= new DbManager(context);
        this.tipoInformacion = new TipoInformacion(context);
        this.material = new Material(context);
    }

     public int getIdInformacion() {
        return idInformacion;
    }

    public void setIdInformacion(int idInformacion) {
        this.idInformacion = idInformacion;
    }

    public int getIdTipoInformacion() {
        return idTipoInformacion;
    }

    public void setIdTipoInformacion(int idTipoInformacion) {
        this.idTipoInformacion = idTipoInformacion;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public boolean insertInformacion(int idInformacion,int idTipoInformacion,int idMaterial,String titulo,String descripcion, String fecha) {
        try {
            contentValues = new ContentValues();
            contentValues.put(InformacionModel.COLUMN_ID, idInformacion);
            if (idTipoInformacion > 0)
                contentValues.put(InformacionModel.COLUMN_ID_TIPO_INFORMACION, idTipoInformacion);
            if (idMaterial > 0)
                contentValues.put(InformacionModel.COLUMN_ID_MATERIAL, idMaterial);
            contentValues.put(InformacionModel.COLUMN_TITULO, titulo);
            contentValues.put(InformacionModel.COLUMN_DESCRIPCION, descripcion);
            contentValues.put(InformacionModel.COLUMN_FECHA, fecha);

            if (dbManager.Insert(InformacionModel.NAME_TABLE, contentValues))
                return true;
            return false;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    /**
     * Retorna el Max idInformacion.
     * Este metodo se utiliza para comparar los datos de la base de datos local con los del servidor
     * @return Max(idInformacion)
     */
    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+InformacionModel.COLUMN_ID+") AS "+InformacionModel.COLUMN_ID+" FROM "+InformacionModel.NAME_TABLE,null);
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(InformacionModel.COLUMN_ID));
        }
        return 0;
    }

    public  Boolean consultarInformacionPorId(int idInformacion)
    {
        Cursor c = dbManager.Select(InformacionModel.NAME_TABLE,new String[] { "*" },InformacionModel.COLUMN_ID + "=?",new String[] {String.valueOf(idInformacion)},null,null,null,null);
        if (c.moveToFirst())
        {
            this.idInformacion = c.getInt(c.getColumnIndex(InformacionModel.COLUMN_ID));
            this.idTipoInformacion = c.getInt(c.getColumnIndex(InformacionModel.COLUMN_ID_TIPO_INFORMACION));
            this.idMaterial = c.getInt(c.getColumnIndex(InformacionModel.COLUMN_ID_MATERIAL));
            this.titulo = c.getString(c.getColumnIndex(InformacionModel.COLUMN_TITULO));
            this.descripcion = c.getString(c.getColumnIndex(InformacionModel.COLUMN_DESCRIPCION));
            this.fecha = c.getString(c.getColumnIndex(InformacionModel.COLUMN_FECHA));
            // Cargamos los objetos embebidos
            this.tipoInformacion.consultarTipoInformacionPorId(this.idTipoInformacion);
            this.material.consultarMaterialPorId(this.idMaterial);
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public List<Informacion> consultarTodoInformacion()
    {
        List<Informacion> ListadoInformacion = new ArrayList<Informacion>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(InformacionModel.NAME_TABLE, new String[] { "*" },null,null,null,null,InformacionModel.COLUMN_ID,null);
        // Si hay informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListaInformacion
            do
            {
                ListadoInformacion.add(this.CrearObjetoInformacion(c));
            }
           while (c.moveToNext());
        }
        c.close();
        return ListadoInformacion;
    }

    public List<Informacion> consultarInformacionPorIdTipoInformacion(int idTipoInformacion)
    {
        List<Informacion> ListadoInformacion = new ArrayList<Informacion>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(InformacionModel.NAME_TABLE, new String[] { "*" },InformacionModel.COLUMN_ID_TIPO_INFORMACION+"=?",new String[] {String.valueOf(idTipoInformacion)},null,null,InformacionModel.COLUMN_FECHA + " DESC",null);
        // Si hay informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListaInformacion
            do
            {
                ListadoInformacion.add(this.CrearObjetoInformacion(c));
            }
            while (c.moveToNext());
        }
        c.close();
        return ListadoInformacion;
    }

    public List<Informacion> consultarInformacionPorIdTipoMaterial(int idTipoMaterial)
    {
        List<Informacion> ListadoInformacion = new ArrayList<Informacion>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(InformacionModel.NAME_TABLE, new String[] { "*" },InformacionModel.COLUMN_ID_MATERIAL+"=?",new String[] {String.valueOf(idMaterial)},null,null,InformacionModel.COLUMN_ID,null);
        // Si hay informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListaInformacion
            do
            {
                ListadoInformacion.add(this.CrearObjetoInformacion(c));
            }
            while (c.moveToNext());
        }
        c.close();
        return ListadoInformacion;
    }

    /**
     * CrearObjetoInformacion devuelve un objeto el cual se esta creando dependiendo del cursor.
     * @param cursor es el cursor que se esta recorriendo para crear el objeto Informacion
     * @return
     */
    private Informacion CrearObjetoInformacion(Cursor cursor)
    {
        Informacion inf = new Informacion(this.dbManager.context);
        inf.idInformacion = cursor.getInt(cursor.getColumnIndex(InformacionModel.COLUMN_ID));
        inf.idTipoInformacion = cursor.getInt(cursor.getColumnIndex(InformacionModel.COLUMN_ID_TIPO_INFORMACION));
        inf.idMaterial = cursor.getInt(cursor.getColumnIndex(InformacionModel.COLUMN_ID_MATERIAL));
        inf.titulo = cursor.getString(cursor.getColumnIndex(InformacionModel.COLUMN_TITULO));
        inf.descripcion = cursor.getString(cursor.getColumnIndex(InformacionModel.COLUMN_DESCRIPCION));
        inf.fecha = cursor.getString(cursor.getColumnIndex(InformacionModel.COLUMN_FECHA));
        // Cargamos los objetos embebidos
        inf.tipoInformacion.consultarTipoInformacionPorId(inf.idTipoInformacion);
        inf.material.consultarMaterialPorId(inf.idMaterial);
        return inf;
    }
}
