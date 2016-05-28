package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Model.InformacionModel;

/**
 * Created by TatisRamos on 13/05/2016.
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

    public Informacion(Context context) {
        dbManager= new DbManager(context);
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
    public List<Informacion> consultarTodoInformacion()
    {
        List<Informacion> ListadoInformacion = new ArrayList<Informacion>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(InformacionModel.NAME_TABLE, new String[] { InformacionModel.COLUMN_ID,InformacionModel.COLUMN_ID_TIPO_INFORMACION,InformacionModel.COLUMN_ID_MATERIAL,
                                                                                InformacionModel.COLUMN_TITULO,InformacionModel.COLUMN_DESCRIPCION,
                                                                                InformacionModel.COLUMN_FECHA},null,null,null,null,InformacionModel.COLUMN_ID,null);
        // Si hay informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListaInformacion
            do
            {
                Informacion inf = new Informacion(this.dbManager.context);
                inf.idInformacion = c.getInt(c.getColumnIndex(InformacionModel.COLUMN_ID));
                inf.idTipoInformacion = c.getInt(c.getColumnIndex(InformacionModel.COLUMN_ID_TIPO_INFORMACION));
                inf.idMaterial = c.getInt(c.getColumnIndex(InformacionModel.COLUMN_ID_MATERIAL));
                inf.titulo = c.getString(c.getColumnIndex(InformacionModel.COLUMN_TITULO));
                inf.descripcion = c.getString(c.getColumnIndex(InformacionModel.COLUMN_DESCRIPCION));
                inf.fecha = c.getString(c.getColumnIndex(InformacionModel.COLUMN_FECHA));
                ListadoInformacion.add(inf);
            }
           while (c.moveToNext());
        }
        return ListadoInformacion;
    }
}
