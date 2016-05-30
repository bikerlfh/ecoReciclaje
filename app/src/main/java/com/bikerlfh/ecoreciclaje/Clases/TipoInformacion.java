package com.bikerlfh.ecoreciclaje.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import bikerlfh.ecorecycle.R;

import java.util.ArrayList;
import java.util.List;

import com.bikerlfh.ecoreciclaje.Model.TipoInformacionModel;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class TipoInformacion {

    private int idTipoInformacion;
    private String codigo;
    private String descripcion;

    public int imagen;

    private DbManager dbManager;
    private ContentValues contentValues;

    public TipoInformacion(Context context) {
        dbManager= new DbManager(context);
    }

    public int getIdTipoInformacion() {
        return idTipoInformacion;
    }

    public void setIdTipoInformacion(int idTipoInformacion) {
        this.idTipoInformacion = idTipoInformacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean insertTipoInformacion(int idTipoInformacion,String codigo,String descripcion){
        contentValues = new ContentValues();
        contentValues.put(TipoInformacionModel.COLUMN_ID,idTipoInformacion);
        contentValues.put(TipoInformacionModel.COLUMN_CODIGO,codigo);
        contentValues.put(TipoInformacionModel.COLUMN_DESCRIPCION,descripcion);

        if(dbManager.Insert(TipoInformacionModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }

    public boolean consultarTipoInformacionPorId(int idTipoInformacion)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(TipoInformacionModel.NAME_TABLE, new String[] { "*" },TipoInformacionModel.COLUMN_ID + "=?",new String[] {String.valueOf(idTipoInformacion)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idTipoInformacion = idTipoInformacion;
            this.codigo = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_DESCRIPCION));
            this.imagen = CargarImagen(this.codigo);

            return true;
        }
        return false;
    }

    public boolean consultarTipoInformacionPorCodigo(String codigo)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(TipoInformacionModel.NAME_TABLE, new String[] { "*" },TipoInformacionModel.COLUMN_CODIGO + "=?",new String[] {codigo},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idTipoInformacion = c.getInt(c.getColumnIndex(TipoInformacionModel.COLUMN_ID));;
            this.codigo = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_DESCRIPCION));
            this.imagen = CargarImagen(this.codigo);

            return true;
        }
        return false;
    }

    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+TipoInformacionModel.COLUMN_ID+") AS "+TipoInformacionModel.COLUMN_ID+" FROM "+TipoInformacionModel.NAME_TABLE,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(TipoInformacionModel.COLUMN_ID));
        }
        return 0;
    }
    public List<TipoInformacion> consultarTodoTipoInformacion()
    {
        List<TipoInformacion> ListadoTipoInformacion = new ArrayList<TipoInformacion>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(TipoInformacionModel.NAME_TABLE, new String[] { "*" },null,null,null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListaTipoInformaicon
            do
            {
                TipoInformacion inf = new TipoInformacion(this.dbManager.context);
                inf.idTipoInformacion = c.getInt(c.getColumnIndex(TipoInformacionModel.COLUMN_ID));
                inf.codigo = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_CODIGO));
                inf.descripcion = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_DESCRIPCION));
                inf.imagen = CargarImagen(inf.codigo);
                ListadoTipoInformacion.add(inf);
            }
            while (c.moveToNext());
        }
        return ListadoTipoInformacion;
    }

    private int CargarImagen(String codigo)
    {
        int imagen = 0;
        switch (codigo)
        {
            case "01":
                imagen = R.drawable.ic_inf;
            break;
            case "02":
                imagen = R.drawable.ic_tips;
            break;
            case "03":
                imagen = R.drawable.ic_man;
            break;
        }
        return imagen;
    }
}
