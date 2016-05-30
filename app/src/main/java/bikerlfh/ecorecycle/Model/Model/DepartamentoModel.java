package bikerlfh.ecorecycle.Model.Model;

/**
 * Created by fercho on 5/30/2016.
 */
public class DepartamentoModel
{
    public static final String NAME_TABLE = "departamento";
    public static final String COLUMN_ID = "idDepartamento";
    public static final String COLUMN_ID_PAIS = "idPais";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    public static final String CREATE_TABLE_DEPARTAMENTO = "create table " + NAME_TABLE + "(" +
            COLUMN_ID +" integer primary key," +
            COLUMN_ID_PAIS + " integer not null,"+
            COLUMN_CODIGO + " text not null,"+
            COLUMN_DESCRIPCION + " text not null)";
}
