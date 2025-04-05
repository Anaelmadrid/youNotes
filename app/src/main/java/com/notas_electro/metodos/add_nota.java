package com.notas_electro.metodos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.notas_electro.SQLiteDatabase.SQLite_Constantes;
import com.notas_electro.SQLiteDatabase.SQLite_DataNotas;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import jp.wasabeef.richeditor.RichEditor;

public class add_nota {
    //iniciar metodo de guardado
    public static void initSave(final Activity activity, final Context context, final RichEditor editorTxt) {
        String txt = editorTxt.getHtml();
        if (TextUtils.isEmpty(txt)) {
            Toast.makeText(context, "La Nota no puede estar vacia ", Toast.LENGTH_SHORT).show();
        } else {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setCancelable(false);
            dialog.setTitle("Añadir Nota");
            dialog.setMessage("Desea Guardar Esta Nota");

            final EditText edtTitulo = new EditText(context);
            dialog.setView(edtTitulo);

            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dia, int which) {
                        //getFecha
                        Calendar calendar = Calendar.getInstance();
                        Date date = calendar.getTime();
                        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaActual = fecha.format(date);

                        SimpleDateFormat hora = new SimpleDateFormat("hh:mm:aa");
                        String HoraActual = hora.format(date);

                        guardarNota(context, edtTitulo.getText().toString(), editorTxt.getHtml(), fechaActual, HoraActual);
                        activity.finish();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
            dialog.show();
        }
    }
    //Insertar los datos en la DataBae
    public static void guardarNota(Context context, String name, String descripcionNota, String fecha, String hora) {
        SQLite_DataNotas sqlData = new SQLite_DataNotas(context, "nota", null, 1);

        try {
            SQLiteDatabase db = sqlData.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(SQLite_Constantes.TITLE_NOTA, name);
            values.put(SQLite_Constantes.DESCRIPCION_NOTA, descripcionNota);
            values.put(SQLite_Constantes.FECHA_NOTA, fecha);
            values.put(SQLite_Constantes.HORA_NOTA, hora);

            long idResult = db.insert(SQLite_Constantes.TABLE_NAME, null, values);

            Toast.makeText(context, "Nota guardada con ID: " + idResult, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al guardar la nota", Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Manejar la excepción según tus necesidades
        } finally {
            sqlData.close(); // Cerrar la base de datos en el bloque finally
        }
    }
    //Metodo para iniciador el modo Guardar al Retroseder con backpress
    public static void crearNotaAnonima(final Activity activity, final Context context, final RichEditor editorText) {

        AlertDialog.Builder dialog2 = new AlertDialog.Builder(context);
        dialog2.setCancelable(false);
        dialog2.setTitle("Nota");
        dialog2.setMessage("Desea Guardar en Borradores esta nota");
        dialog2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dia, int which) {
                    guardarNotaNone(context, editorText);
                    activity.finish();
                }
            });
        Log.d("AddNota", "Medio Dialog");
        dialog2.setNegativeButton("Cancelar", new AlertDialog.OnClickListener(){

                @Override
                public void onClick(DialogInterface dg, int which) {
                    activity.finish();
                }
            });

        AlertDialog alertDialog = dialog2.create();
        alertDialog.show();

    }
    //Metodo para insertar los datos en la dataBase
    public static void guardarNotaNone(Context Context, RichEditor editorTxt) {
        long idResult = 0;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        //fecha
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = formatFecha.format(date);
        //hora
        SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:aa");
        String HoraActual = formatHora.format(date);

        String name ="Nota Anonimo";

        SQLite_DataNotas sqlData = new SQLite_DataNotas(Context, "nota", null, 1);

        try {
            SQLiteDatabase db = sqlData.getWritableDatabase();
            ContentValues values = new ContentValues();
            String editor = editorTxt.getHtml();
            if (TextUtils.isEmpty(editor)) {
                String descripcionNotaNone = "None";
                values.put(SQLite_Constantes.TITLE_NOTA, name);
                values.put(SQLite_Constantes.DESCRIPCION_NOTA, descripcionNotaNone);
                values.put(SQLite_Constantes.FECHA_NOTA, fechaActual);
                values.put(SQLite_Constantes.HORA_NOTA, HoraActual);

                Log.d("MyApp", "Name: " + name);
                Log.d("MyApp", "Descripcion Nota: " + descripcionNotaNone);
                Log.d("MyApp", "Fecha Actual: " + fechaActual);
                Log.d("MyApp", "Hora Actual: " + HoraActual);
                Log.d("MyApp", "ID Result: " + idResult);
                Toast.makeText(Context, "Nota Agregada", Toast.LENGTH_SHORT).show();
            } else {
                String descripcionS = editorTxt.getHtml();
                Log.d("MyApp", "Buscando text");
                String notChatp = "<div";

                String descripcionNota = descripcionS.replace(notChatp, "");
                Log.d("MyApp", " Texto replaced" + descripcionNota);
                values.put(SQLite_Constantes.TITLE_NOTA, name);
                values.put(SQLite_Constantes.DESCRIPCION_NOTA, descripcionNota);
                values.put(SQLite_Constantes.FECHA_NOTA, fechaActual);
                values.put(SQLite_Constantes.HORA_NOTA, HoraActual);

                Log.d("MyApp", "Name: " + name);
                Log.d("MyApp", "Descripcion Nota: " + descripcionNota);
                Log.d("MyApp", "Fecha Actual: " + fechaActual);
                Log.d("MyApp", "Hora Actual: " + HoraActual);
                Log.d("MyApp", "ID Result: " + idResult);

            }

            idResult = db.insert(SQLite_Constantes.TABLE_NAME, null, values);
            Toast.makeText(Context, "Nota guardada con ID: " + idResult, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Context, "Error al guardar la nota", Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Manejar la excepción según tus necesidades
        } finally {
            sqlData.close(); // Cerrar la base de datos en el bloque finally
        }
    }
}
