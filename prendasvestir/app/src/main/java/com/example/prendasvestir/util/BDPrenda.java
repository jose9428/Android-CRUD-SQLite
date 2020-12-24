package com.example.prendasvestir.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDPrenda extends SQLiteOpenHelper {

    public BDPrenda(@Nullable Context context) {
        super(context, ConstantesBD.NOMBREBD, null, ConstantesBD.VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ConstantesBD.NOMBRETABLA+" "+
                "(id integer primary key autoincrement,"+
                "nombre text not null,"+
                "marca text not null," +
                "talla text not null," +
                "color text not null," +
                "precioUnitario real not null);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
