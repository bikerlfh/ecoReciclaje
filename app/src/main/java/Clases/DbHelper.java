package Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.DepartamentoModel;
import Model.InformacionModel;
import Model.MaterialModel;
import Model.MunicipioModel;
import Model.PaisModel;
import Model.SitioReciclajeMaterialModel;
import Model.SitioReciclajeModel;
import Model.TipoInformacionModel;
import Model.TipoMaterialModel;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ecoreciclaje.sqlite";
    private static final int DB_SCHEMA_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VERSION);
    }


    public void onCreate (SQLiteDatabase db){
        db.execSQL(PaisModel.CREATE_TABLE_PAIS);
        db.execSQL(DepartamentoModel.CREATE_TABLE_DEPARTAMENTO);
        db.execSQL(MunicipioModel.CREATE_TABLE_MUNICIPIO);
        db.execSQL(TipoInformacionModel.CREATE_TABLE_TIPOINFORMACION);
        db.execSQL(TipoMaterialModel.CREATE_TABLE_TIPOMATERIAL);
        db.execSQL(InformacionModel.CREATE_TABLE_INFORMACION);
        db.execSQL(MaterialModel.CREATE_TABLE_MATERIAL);
        db.execSQL(SitioReciclajeModel.CREATE_TABLE_SITIORECICLAJE);
        db.execSQL(SitioReciclajeMaterialModel.CREATE_TABLE_SITIORECICLAJE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion)
        {

        }
    }
}


