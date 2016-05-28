package Model;

/**
 * Created by fercho on 5/22/2016.
 */
public class MaterialModel
{
    public static final String NAME_TABLE = "material";
    public static final String COLUMN_ID = "idMaterial";
    public static final String COLUMN_ID_TIPO_MATERIAL= "idTipoMaterial";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_NOMBRE = "nomobre";

    public static final String CREATE_TABLE_MATERIAL = "create table " + NAME_TABLE + "(" +
            COLUMN_ID +" integer primary key," +
            COLUMN_ID_TIPO_MATERIAL + " integer null," +
            COLUMN_CODIGO + " text null," +
            COLUMN_NOMBRE + " text not null)";
}
