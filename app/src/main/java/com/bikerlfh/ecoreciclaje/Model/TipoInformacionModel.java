package com.bikerlfh.ecoreciclaje.Model;

/**
 * Created by fercho on 5/22/2016.
 */
public class TipoInformacionModel
{
    public static final String NAME_TABLE = "tipoinformacion";
    public static final String COLUMN_ID = "idTipoInformacion";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    public static final String CREATE_TABLE_TIPOINFORMACION = "create table " + NAME_TABLE + "(" +
            COLUMN_ID +" integer primary key," +
            COLUMN_CODIGO + " text not null,"+
            COLUMN_DESCRIPCION + " text not null)";
}
