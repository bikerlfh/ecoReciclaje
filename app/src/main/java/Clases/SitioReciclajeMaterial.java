package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import Model.MaterialModel;
import Model.SitioReciclajeMaterialModel;

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
    public boolean consultarSitioReciclajeMaterialPorId(int idSitioReciclajeMaterial)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { SitioReciclajeMaterialModel.COLUMN_ID,
                SitioReciclajeMaterialModel.COLUMN_ID_MATERIAL,
                SitioReciclajeMaterialModel.COLUMN_ID_SITIO_RECICLAJE},SitioReciclajeMaterialModel.COLUMN_ID + "=?",new String[] {String.valueOf(idSitioReciclajeMaterial)},null,null,null,null);
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

    public boolean consultarSitioReciclajeMaterialPorIdMaterial(int idMaterial)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { SitioReciclajeMaterialModel.COLUMN_ID,
                SitioReciclajeMaterialModel.COLUMN_ID_MATERIAL,
                SitioReciclajeMaterialModel.COLUMN_ID_SITIO_RECICLAJE},SitioReciclajeMaterialModel.COLUMN_ID_MATERIAL + "=?",new String[] {String.valueOf(idMaterial)},null,null,null,null);
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
}
