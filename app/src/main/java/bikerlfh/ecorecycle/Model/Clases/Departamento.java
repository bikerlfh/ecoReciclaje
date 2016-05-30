package bikerlfh.ecorecycle.Model.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import bikerlfh.ecorecycle.Model.Model.DepartamentoModel;

/**
 * Created by fercho on 5/30/2016.
 */
public class Departamento
{
    private int idDepartamento;
    private int idPais;
    private String codigo;
    private String descripcion;

    private DbManager dbManager;
    private ContentValues contentValues;

    public Departamento(Context context)
    {
        this.dbManager = new DbManager(context);
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
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

    public boolean insertDepartamento(int idDepartamento,int idPais,String codigo,String descripcion){
        contentValues = new ContentValues();
        contentValues.put(DepartamentoModel.COLUMN_ID,idDepartamento);
        contentValues.put(DepartamentoModel.COLUMN_ID_PAIS,idPais);
        contentValues.put(DepartamentoModel.COLUMN_CODIGO,codigo);
        contentValues.put(DepartamentoModel.COLUMN_DESCRIPCION,descripcion);

        if(dbManager.Insert(DepartamentoModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }

    public boolean consultarDepartamentoPorId(int idDepartamento)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(DepartamentoModel.NAME_TABLE, new String[] { "*" },DepartamentoModel.COLUMN_ID + "=?",new String[] {String.valueOf(idDepartamento)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idDepartamento = c.getInt(c.getColumnIndex(DepartamentoModel.COLUMN_ID));
            this.idPais = c.getInt(c.getColumnIndex(DepartamentoModel.COLUMN_ID_PAIS));
            this.codigo = c.getString(c.getColumnIndex(DepartamentoModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(DepartamentoModel.COLUMN_DESCRIPCION));
            return true;
        }
        return false;
    }

    public boolean consultarDepartamentoPorCodigo(String codigo)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(DepartamentoModel.NAME_TABLE, new String[] { "*" },DepartamentoModel.COLUMN_CODIGO + "=?",new String[] {codigo},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idDepartamento = c.getInt(c.getColumnIndex(DepartamentoModel.COLUMN_ID));
            this.idPais = c.getInt(c.getColumnIndex(DepartamentoModel.COLUMN_ID_PAIS));
            this.codigo = c.getString(c.getColumnIndex(DepartamentoModel.COLUMN_CODIGO));
            this.descripcion = c.getString(c.getColumnIndex(DepartamentoModel.COLUMN_DESCRIPCION));
            return true;
        }
        return false;
    }

    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+DepartamentoModel.COLUMN_ID+") AS "+DepartamentoModel.COLUMN_ID+" FROM "+DepartamentoModel.NAME_TABLE,null);
        // Si hay PAIS
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(DepartamentoModel.COLUMN_ID));
        }
        return 0;
    }
    public List<Departamento> consultarDepartamentoPorIdPais(int idPais)
    {
        List<Departamento> ListadoDepartamento = new ArrayList<Departamento>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(DepartamentoModel.NAME_TABLE, new String[] { "*" },DepartamentoModel.COLUMN_ID_PAIS + "=?",new String[]{String.valueOf(idPais)},null,null,null,null);
        // Si hay Departamento
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto inf el cual se agrega a la ListadoDepartamento
            do
            {
                Departamento departamento = new Departamento(this.dbManager.context);
                departamento.idDepartamento = c.getInt(c.getColumnIndex(DepartamentoModel.COLUMN_ID));
                departamento.idPais = c.getInt(c.getColumnIndex(DepartamentoModel.COLUMN_ID_PAIS));
                departamento.codigo = c.getString(c.getColumnIndex(DepartamentoModel.COLUMN_CODIGO));
                departamento.descripcion = c.getString(c.getColumnIndex(DepartamentoModel.COLUMN_DESCRIPCION));
                ListadoDepartamento.add(departamento);
            }
            while (c.moveToNext());
        }
        return ListadoDepartamento;
    }
}
