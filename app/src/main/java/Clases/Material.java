package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Model.MaterialModel;
import Model.TipoInformacionModel;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class Material {

    private int idMaterial;
    private int idTipoMaterial;
    private String codigo;
    private String nombre;

    private DbManager dbManager;
    private  ContentValues contentValues;

    public Material(Context context) {
        dbManager= new DbManager(context);
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean insertMaterial(int idMaterial,int idTipoMaterial,String codigo, String nombre){
        contentValues = new ContentValues();
        contentValues.put(MaterialModel.COLUMN_ID,idMaterial);
        contentValues.put(MaterialModel.COLUMN_ID_TIPO_MATERIAL,idTipoMaterial);
        contentValues.put(MaterialModel.COLUMN_CODIGO,codigo);
        contentValues.put(MaterialModel.COLUMN_NOMBRE,nombre);

        if(dbManager.Insert(MaterialModel.NAME_TABLE,contentValues))
            return true;
        return false;
    }

    public boolean consultarMaterialPorId(int idMaterial)
    {
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { "*" },MaterialModel.COLUMN_ID + "=?",new String[] {String.valueOf(idMaterial)},null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            this.idMaterial = idMaterial;
            this.idTipoMaterial = c.getInt(c.getColumnIndex(MaterialModel.COLUMN_ID_TIPO_MATERIAL));
            this.codigo = c.getString(c.getColumnIndex(MaterialModel.COLUMN_CODIGO));
            this.nombre = c.getString(c.getColumnIndex(MaterialModel.COLUMN_NOMBRE));
            return true;
        }
        return false;
    }
    public int consultarMaxId()
    {
        Cursor c = dbManager.RawQuery("SELECT MAX("+ MaterialModel.COLUMN_ID+") AS "+MaterialModel.COLUMN_ID+" FROM "+MaterialModel.NAME_TABLE,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            if (c.getCount() > 0)
                return c.getInt(c.getColumnIndex(MaterialModel.COLUMN_ID));
        }
        return 0;
    }

    public List<Material> consultarTodoMaterial()
    {
        List<Material> ListadoMaterial = new ArrayList<Material>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { "*" },null,null,null,null,null,null);
        // Si hay Tipo Informacion
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto mat el cual se agrega a la ListadoMaterial
            do
            {
                Material mat = new Material(this.dbManager.context);
                mat.idMaterial = idMaterial;
                mat.idTipoMaterial = c.getInt(c.getColumnIndex(MaterialModel.COLUMN_ID_TIPO_MATERIAL));
                mat.codigo = c.getString(c.getColumnIndex(MaterialModel.COLUMN_CODIGO));
                mat.nombre = c.getString(c.getColumnIndex(MaterialModel.COLUMN_NOMBRE));
                ListadoMaterial.add(mat);
            }
            while (c.moveToNext());
        }
        return ListadoMaterial;
    }

    public List<Material> consultarMaterialPoridTipoMaterial(int idTipoMaterial)
    {
        List<Material> ListadoMaterial = new ArrayList<Material>();
        // Se realiza la consulta a la base de datos.
        // Indicamos que nos traiga todos los campos y con un order By del COLUMN_ID
        Cursor c = dbManager.Select(MaterialModel.NAME_TABLE, new String[] { "*" },MaterialModel.COLUMN_ID_TIPO_MATERIAL + "=?",new String[] {String.valueOf(idTipoMaterial)},null,null,null,null);
        if (c.moveToFirst())
        {
            // Recorremos el cursor y llenamos el Objeto mat el cual se agrega a la ListadoMaterial
            do
            {
                Material mat = new Material(this.dbManager.context);
                mat.idMaterial = idMaterial;
                mat.idTipoMaterial = c.getInt(c.getColumnIndex(MaterialModel.COLUMN_ID_TIPO_MATERIAL));
                mat.codigo = c.getString(c.getColumnIndex(MaterialModel.COLUMN_CODIGO));
                mat.nombre = c.getString(c.getColumnIndex(MaterialModel.COLUMN_NOMBRE));
                ListadoMaterial.add(mat);
            }
            while (c.moveToNext());
        }
        return ListadoMaterial;
    }
}
