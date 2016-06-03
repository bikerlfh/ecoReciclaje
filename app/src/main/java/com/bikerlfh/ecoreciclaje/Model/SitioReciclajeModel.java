package com.bikerlfh.ecoreciclaje.Model;

/**
 * Created by fercho on 5/22/2016.
 */
public class SitioReciclajeModel
{
    public static final String NAME_TABLE = "sitioreciclaje";
    public static final String COLUMN_ID = "idSitioReciclaje";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_ID_MUNICIPIO = "idMunicipio";
    public static final String COLUMN_LATITUD = "latitud";
    public static final String COLUMN_LONGITUD = "longitud";

    public static final String CREATE_TABLE_SITIORECICLAJE = "create table " + NAME_TABLE + "(" +
            COLUMN_ID +" integer primary key," +
            COLUMN_NOMBRE + " integer null," +
            COLUMN_DIRECCION + " text null," +
            COLUMN_TELEFONO + " text null," +
            COLUMN_ID_MUNICIPIO + " integer not null,"+
            COLUMN_LATITUD + " real null," +
            COLUMN_LONGITUD + " real not null)";
}
