package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import Model.PaisModel;

/**
 * Created by fercho on 5/30/2016.
 */
public class Pais
{
    private int idPais;
    private String codigo;
    private String descripcion;

    private DbManager dbManager;
    private ContentValues contentValues;

    public Pais(Context context)
    {
        this.dbManager = new DbManager(context);
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
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
    public boolean insertPais(int idPais,String codigo,String descripcion){
        contentValues = new ContentValues();
        contentValues.put(PaisModel.COLUMN_ID,idPais);
        contentValues.put(PaisModel.COLUMN_CODIGO,codigo);
        contentValues.put(PaisModel.COLUMN_DESCRIPCION,descripcion);

        if(dbManager.Insert(PaisModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }

    public boolean consultarPaisPorId(int idPais)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(PaisModel.NAME_TABLE, new String[] { "*" },PaisModel.COLUMN_ID + "=?",new String[] {String.valueOf(idPais)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idPais = idPais;
            this.codigo = c.getString(c.getColumnIndex(PaisModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(PaisModel.COLUMN_DESCRIPCION));
            return true;
        }
        return false;
    }

    public boolean consultarPaisPorCodigo(String codigo)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(PaisModel.NAME_TABLE, new String[] { "*" },PaisModel.COLUMN_CODIGO + "=?",new String[] {codigo},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idPais = c.getInt(c.getColumnIndex(PaisModel.COLUMN_ID));;
            this.codigo = c.getString(c.getColumnIndex(PaisModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(PaisModel.COLUMN_DESCRIPCION));
            return true;
        }
        return false;
    }

    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+PaisModel.COLUMN_ID+") AS "+PaisModel.COLUMN_ID+" FROM "+PaisModel.NAME_TABLE,null);
        // Si hay PAIS
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(PaisModel.COLUMN_ID));
        }
        return 0;
    }
    public List<Pais> consultarTodoPais()
    {
        List<Pais> ListadoPais = new ArrayList<Pais>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(PaisModel.NAME_TABLE, new String[] { "*" },null,null,null,null,null,null);
        // Si hay Pais
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListadoPais
            do
            {
                Pais pais = new Pais(this.dbManager.context);
                pais.idPais = c.getInt(c.getColumnIndex(PaisModel.COLUMN_ID));
                pais.codigo = c.getString(c.getColumnIndex(PaisModel.COLUMN_CODIGO));
                pais.descripcion = c.getString(c.getColumnIndex(PaisModel.COLUMN_DESCRIPCION));
                ListadoPais.add(pais);
            }
            while (c.moveToNext());
        }
        return ListadoPais;
    }

}
