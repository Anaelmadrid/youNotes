package com.notas_electro.SQLiteDatabase;

public class SQLite_Constantes {

    public final static String TABLE_NAME = "name";
    public final static String ID = "ID";
    public final static String TITLE_NOTA = "title_name";
    public final static String DESCRIPCION_NOTA = "descripcion_nota";
    public final static String FECHA_NOTA = "fecha_nota";
    public final static String HORA_NOTA = "hora_nota";

    public final static String DATABASE_NAME = "CREATE TABLE " + TABLE_NAME + " ( "
    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    + TITLE_NOTA + " TEXT, "
    + DESCRIPCION_NOTA + " TEXT, "
    + FECHA_NOTA + " TEXT, "
    + HORA_NOTA + " TEXT)";
}
