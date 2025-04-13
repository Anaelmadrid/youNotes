package com.notas_electro;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.notas_electro.SQLiteDatabase.SQLite_DataNotas;
import com.notas_electro.adapter.AdapterView_Notas;
import com.notas_electro.adapter.DatosView_Pojo;
import com.notas_electro.metodos.Show_Data;
import com.rgbcolorm.ColorRgbX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends Activity {

    private static ListView listDatos;
    private static AdapterView_Notas adapter;
    private static ArrayList<DatosView_Pojo> datos;
    private static SQLite_DataNotas helper;
    private static String idPosition;
    private static DatosView_Pojo content;

    private Button btn_notas_privates, mainButton;
    private TextView txtResult, textR;
    private static Activity activity;

    final private int PERMISSION_REQUEST_CODE = 1;
    private androidx.appcompat.widget.SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textR = findViewById(R.id.mainTextView1);
        listDatos = findViewById(R.id.main_List_Datos);
        activity = MainActivity.this;
        Log.d("main", "hola");
        //Llamando a la instancia de la data basehelper y Creando La tabla
        helper = new SQLite_DataNotas(this, "nota", null, 1);

        //IDS
        btn_notas_privates = findViewById(R.id.mainbtnNotas_privates);
        mainButton = findViewById(R.id.mainButton);
        //Botton para la Activity De Paleta de Colores
        mainButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(getApplicationContext(), ColorRgbX.class);
                            startActivity(intent);

                            Toast.makeText(getApplication(), "Init AcTV", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            textR.setText(R.string.erro_al_lanzar_activity + "\n" + e);

                            Log.d("MainActivity", e.toString());
                        }
                    }
                });
//Lanzarse a las notas privadas
        btn_notas_privates.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        try {
                            startActivity(new Intent(MainActivity.this, NotasPrivadas.class));
                        } catch (Exception e) {
                            txtResult.setText(R.string.erro_al_lanzar_activity + "\n" + e);
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Configurar el SearchView
        MenuItem searchItem = menu.findItem(R.id.app_bar_search); // Asegúrate que coincide con el XML

        if (searchItem != null) {
            searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

            if (searchView != null) {
                // Configuración del SearchView
                searchView.setQueryHint("Buscar notas...");
                searchView.setMaxWidth(Integer.MAX_VALUE);

                searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filtrarNotas(newText);
                        return true;
                    }
                });

                // Esto asegura que el SearchView se expanda correctamente
                searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        filtrarNotas(""); // Resetear el filtro al colapsar
                        return true;
                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        //Item menu para lanzarse la Activity de Añadir una nueva Nota
        if (item.getItemId() == R.id.menu_mainAdd_nota) {
            startActivity(new Intent(this, AddNota.class));

        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        pedirpermiso();
        if (datos != null) {
            ordenarLista(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (datos != null && !datos.isEmpty()) {
            ordenarLista(true);
        } else {
            cargarDatos(); // Vuelve a cargar si no hay datos
        }
    }

    @Override
    public void onBackPressed() {
        if (searchView != null && !searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    public static void ActualizarList(Context context) {
        if (datos != null) datos.clear();
        if (adapter != null) adapter.clear();
        Show_Data.mostrarNotas(context, helper, content, datos);
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    public static void preva(String saludo) {
        Toast.makeText(activity, saludo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                cargarDatos();
            } else {
                Toast.makeText(this, "Permiso denegado. No se pueden cargar los datos.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void pedirpermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // Android 11 o superior
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            } else {
                cargarDatos(); // Si ya tiene el permiso, carga los datos
            }
        } else { // Android 10 o inferior
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    cargarDatos(); // Si ya tiene el permiso, carga los datos
                }
            } else {
                cargarDatos(); // Android 5 o inferior, no necesita permisos en tiempo de ejecución
            }
        }
    }

    public void cargarDatos() {
        if (datos == null) {
            datos = new ArrayList<>();
        } else {
            datos.clear();
            adapter.resetearFiltro();
        }

        if (adapter == null) {
            adapter = new AdapterView_Notas(this, datos);
            listDatos.setAdapter(adapter);
        }

        Show_Data.mostrarNotas(this, helper, content, datos);

        if (datos.isEmpty()) {
            Toast.makeText(this, "No hay notas para mostrar", Toast.LENGTH_SHORT).show();
            listDatos.setBackgroundColor(getResources().getColor(R.color.colorLight));
        } else {
            ordenarLista(true); // Ordena después de cargar
        }
    }

    public void ordenarLista(boolean ascendente) {
        if (datos == null || datos.isEmpty()) return;

        Collections.sort(datos, new Comparator<DatosView_Pojo>() {
            @Override
            public int compare(DatosView_Pojo o1, DatosView_Pojo o2) {
                return ascendente
                        ? o1.getTitleNota().compareToIgnoreCase(o2.getTitleNota())
                        : o2.getTitleNota().compareToIgnoreCase(o1.getTitleNota());
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void filtrarNotas(String textoBusqueda) {  // Quitado el static
        if (datos == null) return;

        ArrayList<DatosView_Pojo> notasFiltradas = new ArrayList<>();

        textoBusqueda = textoBusqueda.toLowerCase();

        for (DatosView_Pojo nota : datos) {
            if (nota.getTitleNota().toLowerCase().contains(textoBusqueda)) {
                notasFiltradas.add(nota);
            }
        }

        if (adapter != null) {  // Verificación de null añadida
            adapter.filtrar(notasFiltradas);
        }
    }

}
