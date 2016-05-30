package com.bikerlfh.ecoreciclaje.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.bikerlfh.ecoreciclaje.Model.MaterialModel;
import com.bikerlfh.ecoreciclaje.Model.SitioReciclajeMaterialModel;

/**
 * Created by fercho on 5/24/2016.
 */
public class SitioReciclajeMaterial {
    private long idSitioReciclajeMaterial;
    private int idMaterial;
    private long idSitioReciclaje;

    private DbManager dbManager;
    private ContentValues contentValues;

    public SitioReciclajeMaterial(Context context) {
        dbManager= new DbManager(context);
    }

    public long getIdSitioReciclajeMaterial() {
        return idSitioReciclajeMaterial;
    }

    public void setIdSitioReciclajeMaterial(long idSitioReciclajeMaterial) {
        this.idSitioReciclajeMaterial = idSitioReciclajeMaterial;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public long getIdSitioReciclaje() {
        return idSitioReciclaje;
    }

    public void setIdSitioReciclaje(long idSitioReciclaje) {
        this.idSitioReciclaje = idSitioReciclaje;
    }

    public boolean insertSitioReciclajeMaterial(long idSitioReciclajeMaterial,int idMaterial,long idSitioReciclaje){
        contentValues = new ContentValues();
        contentValues.put(SitioReciclajeMaterialModel.COLUMN_ID,idSitioReciclajeMaterial);
        contentValues.put(SitioReciclajeMaterialModel.COLUMN_ID_MATERIAL,idMaterial);
        contentValues.put(SitioReciclajeMaterialModel.COLUMN_ID_SITIO_RECICLAJE,idSitioReciclaje);

        if(dbManager.Insert(SitioReciclajeMaterialModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }

    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+ SitioReciclajeMaterialModel.COLUMN_ID+") AS "+SitioReciclajeMaterialModel.COLUMN_ID+" FROM "+SitioReciclajeMaterialModel.NAME_TABLE,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(SitioReciclajeMaterialModel.COLUMN_ID));
        }
        return 0;
    }

    public boolean consultarSitioReciclajeMaterialPorId(int idSitioReciclajeMaterial)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { "*" },SitioReciclajeMaterialModel.COLUMN_ID + "=?",new String[] {String.valueOf(idSitioReciclajeMaterial)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idSitioReciclajeMaterial = c.getLong(c.getColumnIndex(SitioReciclajeMaterialModel.COLUMN_ID));
            this.idMaterial = c.getInt(c.getColumnIndex(SitioReciclajeMaterialModel.COLUMN_ID_MATERIAL));
            this.idSitioReciclaje = c.getLong(c.getColumnIndex(SitioReciclajeMaterialModel.COLUMN_ID_SITIO_RECICLAJE));
            return true;
        }
        return false;
    }

    public List<SitioReciclajeMaterial> consultarSitioReciclajeMaterialPorIdMaterial(int idMaterial)
    {
        List<SitioReciclajeMaterial> ListadoSitioReciclajeMaterial = new ArrayList<>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { "*" },SitioReciclajeMaterialModel.COLUMN_ID_MATERIAL + "=?",new String[] {String.valueOf(idMaterial)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            do
            {
                ListadoSitioReciclajeMaterial.add(this.CrearObjetoSitioReciclajeMaterial(c));
            }
            while (c.moveToNext());
        }
        c.close();
        return ListadoSitioReciclajeMaterial;
    }
    public List<SitioReciclajeMaterial> consultarSitioReciclajeMaterialPorIdSitioReciclaje(int idSitioReciclaje)
    {
        List<SitioReciclajeMaterial> ListadoSitioReciclajeMaterial = new ArrayList<>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { "*" },SitioReciclajeMaterialModel.COLUMN_ID_SITIO_RECICLAJE + "=?",new String[] {String.valueOf(idSitioReciclaje)},null,null,null,null);
        if (c.moveToFirst())
        {
            do
            {
                ListadoSitioReciclajeMaterial.add(this.CrearObjetoSitioReciclajeMaterial(c));
            }
            while (c.moveToNext());
        }
        c.close();
        return ListadoSitioReciclajeMaterial;
    }

    private SitioReciclajeMaterial CrearObjetoSitioReciclajeMaterial(Cursor cursor)
    {
        SitioReciclajeMaterial sitioReciclajeMaterial = new SitioReciclajeMaterial(this.dbManager.context);
        sitioReciclajeMaterial.idSitioReciclajeMaterial = cursor.getLong(cursor.getColumnIndex(SitioReciclajeMaterialModel.COLUMN_ID));
        sitioReciclajeMaterial.idMaterial = cursor.getInt(cursor.getColumnIndex(SitioReciclajeMaterialModel.COLUMN_ID_MATERIAL));
        sitioReciclajeMaterial.idSitioReciclaje = cursor.getLong(cursor.getColumnIndex(SitioReciclajeMaterialModel.COLUMN_ID_SITIO_RECICLAJE));
        return sitioReciclajeMaterial;
    }

}
