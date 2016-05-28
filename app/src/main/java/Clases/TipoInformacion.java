package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Model.InformacionModel;
import Model.TipoInformacionModel;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class TipoInformacion {

    private int idTipoInformacion;
    private String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean insertTipoInformacion(int idTipoInformacion,String descripcion){
        contentValues = new ContentValues();
        contentValues.put(TipoInformacionModel.COLUMN_ID,idTipoInformacion);
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
            this.descripcion = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_DESCRIPCION));
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
                inf.descripcion = c.getString(c.getColumnIndex(TipoInformacionModel.COLUMN_DESCRIPCION));
                ListadoTipoInformacion.add(inf);
            }
            while (c.moveToNext());
        }
        return ListadoTipoInformacion;
    }
}
