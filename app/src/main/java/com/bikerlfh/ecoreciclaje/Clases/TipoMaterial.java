package com.bikerlfh.ecoreciclaje.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.bikerlfh.ecoreciclaje.R;

import java.util.ArrayList;
import java.util.List;


import com.bikerlfh.ecoreciclaje.Model.TipoMaterialModel;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class TipoMaterial
{

    private int idTipoMaterial;
    private String codigo;
    private String descripcion;

    public int imagen;


    private DbManager dbManager;
    private ContentValues contentValues;

    public TipoMaterial(Context context) {
        dbManager= new DbManager(context);
    }

    // Getters and setters

    public int getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(int idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
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

    public boolean insertTipoMaterial(int idTipoMaterial,String codigo,String descripcion){
        contentValues = new ContentValues();
        contentValues.put(TipoMaterialModel.COLUMN_ID,idTipoMaterial);
        contentValues.put(TipoMaterialModel.COLUMN_CODIGO,codigo);
        contentValues.put(TipoMaterialModel.COLUMN_DESCRIPCION,descripcion);

        if(dbManager.Insert(TipoMaterialModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }
    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+TipoMaterialModel.COLUMN_ID+") AS "+TipoMaterialModel.COLUMN_ID+" FROM "+TipoMaterialModel.NAME_TABLE,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(TipoMaterialModel.COLUMN_ID));
        }
        return 0;
    }
    public boolean consultarTipoMaterialPorId(int idTipoMaterial)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(TipoMaterialModel.NAME_TABLE, new String[] { "*" },TipoMaterialModel.COLUMN_ID + "=?",new String[] {String.valueOf(idTipoMaterial)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idTipoMaterial = idTipoMaterial;
            this.codigo =c.getString(c.getColumnIndex(TipoMaterialModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(TipoMaterialModel.COLUMN_DESCRIPCION));
            //this.imagen = CargarImagen(this.codigo);

            return true;
        }
        return false;
    }
    public boolean consultarTipoMaterialPorCodigo(String codigo)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(TipoMaterialModel.NAME_TABLE, new String[] { "*" },TipoMaterialModel.COLUMN_CODIGO + "=?",new String[] {codigo},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idTipoMaterial = c.getInt(c.getColumnIndex(TipoMaterialModel.COLUMN_ID));;
            this.codigo = c.getString(c.getColumnIndex(TipoMaterialModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(TipoMaterialModel.COLUMN_DESCRIPCION));
            //this.imagen = CargarImagen(this.codigo);

            return true;
        }
        return false;
    }
    public List<TipoMaterial> consultarTodoTipoMaterial()
    {
        List<TipoMaterial> ListadoTipoMaterial = new ArrayList<TipoMaterial>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(TipoMaterialModel.NAME_TABLE, new String[] { "*" },null,null,null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListaTipoInformaicon
            do
            {
                TipoMaterial tipoMaterial = new TipoMaterial(this.dbManager.context);
                tipoMaterial.idTipoMaterial = c.getInt(c.getColumnIndex(TipoMaterialModel.COLUMN_ID));
                tipoMaterial.codigo =c.getString(c.getColumnIndex(TipoMaterialModel.COLUMN_CODIGO));
                tipoMaterial.descripcion = c.getString(c.getColumnIndex(TipoMaterialModel.COLUMN_DESCRIPCION));
                //tipoMaterial.imagen = CargarImagen(tipoMaterial.codigo);
                ListadoTipoMaterial.add(tipoMaterial);
            }
            while (c.moveToNext());
        }
        return ListadoTipoMaterial;
    }

}
