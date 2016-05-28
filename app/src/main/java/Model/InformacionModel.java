package Model;

/**
 * Created by fercho on 5/22/2016.
 */
public class InformacionModel
{
    public static final String NAME_TABLE = "informacion";
    public static final String COLUMN_ID = "idInformacion";
    public static final String COLUMN_ID_TIPO_INFORMACION = "idTipoInformacion";
    public static final String COLUMN_ID_MATERIAL = "idMaterial";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_FECHA = "fecha";

    public static final String CREATE_TABLE_INFORMACION = "create table " + NAME_TABLE + "(" +
            COLUMN_ID +" integer primary key," +
            COLUMN_ID_TIPO_INFORMACION + " integer not null," +
            COLUMN_ID_MATERIAL + " integer," +
            COLUMN_TITULO + " text not null," +
            COLUMN_DESCRIPCION + " text not null," +
            COLUMN_FECHA + " text not null)";
}
