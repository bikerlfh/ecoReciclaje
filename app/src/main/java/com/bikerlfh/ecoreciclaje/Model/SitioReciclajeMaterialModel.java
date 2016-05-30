package com.bikerlfh.ecoreciclaje.Model;

/**
 * Created by fercho on 5/22/2016.
 */
public class SitioReciclajeMaterialModel
{
    public static final String NAME_TABLE = "sitioreciclajematerial";
    public static final String COLUMN_ID = "idSitioReciclajeMaterial";
    public static final String COLUMN_ID_SITIO_RECICLAJE = "idSitioReciclaje";
    public static final String COLUMN_ID_MATERIAL = "idMaterial";

    public static final String CREATE_TABLE_SITIORECICLAJE = "create table " + NAME_TABLE + "(" +
            COLUMN_ID +" integer primary key," +
            COLUMN_ID_SITIO_RECICLAJE + " integer not null," +
            COLUMN_ID_MATERIAL + " integer not null)";
}
