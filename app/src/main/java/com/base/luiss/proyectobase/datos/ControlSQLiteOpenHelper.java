package com.base.luiss.proyectobase.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.base.luiss.proyectobase.Util.*;

/**
 * Created by luiss on 22/07/2016.
 */
public class ControlSQLiteOpenHelper extends SQLiteOpenHelper {


    public ControlSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(constantes.CREAR_BASE_DATOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
