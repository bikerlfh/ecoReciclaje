package com.bikerlfh.ecoreciclaje.Model;

/**
 * Created by fercho on 5/30/2016.
 */
public class PaisModel
{
    public static final String NAME_TABLE = "pais";
    public static final String COLUMN_ID = "idPais";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    public static final String CREATE_TABLE_PAIS = "create table " + NAME_TABLE + "(" +
            COLUMN_ID +" integer primary key," +
            COLUMN_CODIGO + " text not null,"+
            COLUMN_DESCRIPCION + " text not null)";
}
