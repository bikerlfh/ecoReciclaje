package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import Model.MunicipioModel;

/**
 * Created by fercho on 5/30/2016.
 */
public class Municipio
{
    private int idMunicipio;
    private int idDepartamento;
    private String codigo;
    private String descripcion;

    private DbManager dbManager;
    private ContentValues contentValues;

    public Municipio(Context context)
    {
        this.dbManager = new DbManager(context);
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
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


    public boolean insertMunicipio(int idMunicipio,int idDepartamento,String codigo,String descripcion){
        contentValues = new ContentValues();
        contentValues.put(MunicipioModel.COLUMN_ID,idMunicipio);
        contentValues.put(MunicipioModel.COLUMN_ID_DEPARTAMENTO,idDepartamento);
        contentValues.put(MunicipioModel.COLUMN_CODIGO,codigo);
        contentValues.put(MunicipioModel.COLUMN_DESCRIPCION,descripcion);

        if(dbManager.Insert(MunicipioModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }

    public boolean consultarMunicipioPorId(int idMunicipio)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MunicipioModel.NAME_TABLE, new String[] { "*" },MunicipioModel.COLUMN_ID + "=?",new String[] {String.valueOf(idMunicipio)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idMunicipio = c.getInt(c.getColumnIndex(MunicipioModel.COLUMN_ID));
            this.idDepartamento = c.getInt(c.getColumnIndex(MunicipioModel.COLUMN_ID_DEPARTAMENTO));
            this.codigo = c.getString(c.getColumnIndex(MunicipioModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(MunicipioModel.COLUMN_DESCRIPCION));
            return true;
        }
        return false;
    }

    public boolean consultarMunicipioPorCodigo(String codigo)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MunicipioModel.NAME_TABLE, new String[] { "*" },MunicipioModel.COLUMN_CODIGO + "=?",new String[] {codigo},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idMunicipio = c.getInt(c.getColumnIndex(MunicipioModel.COLUMN_ID));
            this.idDepartamento = c.getInt(c.getColumnIndex(MunicipioModel.COLUMN_ID_DEPARTAMENTO));
            this.codigo = c.getString(c.getColumnIndex(MunicipioModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(MunicipioModel.COLUMN_DESCRIPCION));
            return true;
        }
        return false;
    }

    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+MunicipioModel.COLUMN_ID+") AS "+MunicipioModel.COLUMN_ID+" FROM "+MunicipioModel.NAME_TABLE,null);
        // Si hay PAIS
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(MunicipioModel.COLUMN_ID));
        }
        return 0;
    }
    public List<Municipio> consultarMunicipioPorIdDepartamento(int idDepartamento)
    {
        List<Municipio> ListadoMunicipio = new ArrayList<Municipio>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MunicipioModel.NAME_TABLE, new String[] { "*" },MunicipioModel.COLUMN_ID_DEPARTAMENTO + "=?",new String[]{String.valueOf(idDepartamento)},null,null,null,null);
        // Si hay Municipio
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListadoMunicipio
            do
            {
                Municipio municipio = new Municipio(this.dbManager.context);
                municipio.idMunicipio = c.getInt(c.getColumnIndex(MunicipioModel.COLUMN_ID));
                municipio.idDepartamento = c.getInt(c.getColumnIndex(MunicipioModel.COLUMN_ID_DEPARTAMENTO));
                municipio.codigo = c.getString(c.getColumnIndex(MunicipioModel.COLUMN_CODIGO));
                municipio.descripcion = c.getString(c.getColumnIndex(MunicipioModel.COLUMN_DESCRIPCION));
                ListadoMunicipio.add(municipio);
            }
            while (c.moveToNext());
        }
        return ListadoMunicipio;
    }

}
