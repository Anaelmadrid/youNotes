package com.notas_electro.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.notas_electro.MainActivity;
import com.notas_electro.R;
import com.notas_electro.SQLiteDatabase.SQLite_Constantes;
import com.notas_electro.SQLiteDatabase.SQLite_DataNotas;

public class metodos {

    public static void showPopupMenu(final Context context, View view, final DatosView_Pojo datos, final AdapterView_Notas adapter) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.menu_adapter_info); // Reemplaza "your_menu_resource" con el ID de tu archivo de recursos de menú
        popupMenu.setGravity(Gravity.RIGHT);


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.delete_adapter) {
                    delete_adater_notaSQLite(datos);
                    return true;
                } else if (itemId == R.id.item_adapterinfo) {
                    info_Nota(context, adapter.getPosition(datos), adapter);
                    return true;
                } else {
                    return false;
                }
            }


            private void delete_adater_notaSQLite(DatosView_Pojo datos) {
                SQLite_DataNotas sqlHelper = new SQLite_DataNotas(context, "nota", null, 1);
                SQLiteDatabase db = sqlHelper.getWritableDatabase();
                db.delete(SQLite_Constantes.TABLE_NAME, "Id =?", new String[]{datos.getId()});
                db.close();
                MainActivity.ActualizarList(context);

            }
        });

        popupMenu.show();
    }

    public static void info_Nota(Context context, int position, AdapterView_Notas adapter) {
        //Nesito la posicion del ArrayAdapter<DatosView_Pojo>
        final DatosView_Pojo datos = adapter.getItem(position);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setView(R.layout.info_nota);
        dialog.setTitle(datos.getTitleNota());
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dia, int which) {

            }
        });
        dialog.setNegativeButton("Cancel", null);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        final TextView Fecha = alertDialog.findViewById(R.id.infonotaTV_Fecha);
        final TextView Hora = alertDialog.findViewById(R.id.infonotaTV_hora);


        Fecha.setText("Fecha de Creación :" + datos.getFechaNota() + " " + datos.getId());
        //Get Hora

        Hora.setText("Hora :" + datos.getHoraNota());
    }

}
