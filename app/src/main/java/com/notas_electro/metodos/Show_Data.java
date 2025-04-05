package com.notas_electro.metodos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.notas_electro.SQLiteDatabase.SQLite_Constantes;
import com.notas_electro.SQLiteDatabase.SQLite_DataNotas;
import com.notas_electro.adapter.DatosView_Pojo;

import java.util.ArrayList;

public class Show_Data {
    public static void mostrarNotas(Context context, SQLite_DataNotas helper, DatosView_Pojo content, ArrayList<DatosView_Pojo> datos) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + SQLite_Constantes.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            content = new DatosView_Pojo();
            content.setId(cursor.getString(0));
            content.setTitleNota(cursor.getString(1));
            content.setDescripcionNota(cursor.getString(2));
            content.setFechaNota(cursor.getString(3));
            content.setHoraNota(cursor.getString(4));
            datos.add(content);
            Log.d("MainActivity", "ID: " + cursor.getString(0) + ", Title: " + cursor.getString(1) + ", Descripcion: " + cursor.getString(2) + ", Hora: " + cursor.getString(3) + ", Fecha: " + cursor.getString(4));
        }
        db.close();

    }

}
