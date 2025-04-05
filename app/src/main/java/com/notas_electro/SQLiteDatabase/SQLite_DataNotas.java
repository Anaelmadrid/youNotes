package com.notas_electro.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import java.io.File;

public class SQLite_DataNotas extends SQLiteOpenHelper {

    public SQLite_DataNotas(Context context, String name_db, SQLiteDatabase.CursorFactory factory, int version_db) {
        super(context, getExternalDatabasePath(context, name_db).getAbsolutePath() , factory, version_db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLite_Constantes.DATABASE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNew) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLite_Constantes.TABLE_NAME);
        onCreate(db);
    }

    private static File getExternalDatabasePath(Context context, String name) {
        File dataDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "TuDirectorioDeBaseDeDatos");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        return new File(dataDir, name);
    }
}
