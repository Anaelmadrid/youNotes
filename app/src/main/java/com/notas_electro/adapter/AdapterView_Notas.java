package com.notas_electro.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.notas_electro.R;
import com.notas_electro.ViewNota;

import java.util.ArrayList;

public class AdapterView_Notas extends ArrayAdapter<DatosView_Pojo> {
    private int position;
    private ArrayList<DatosView_Pojo> listaOriginal;

    public AdapterView_Notas(Context context, ArrayList<DatosView_Pojo> datos) {
        super(context, 0, datos);
        this.listaOriginal = new ArrayList<>(datos);
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        this.position = position;
        final DatosView_Pojo datos = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view_nota, parent, false);
        }

        TextView Title = view.findViewById(R.id.adapter_TitleNota);
        /*
		 if (datos != null) {
		 Title.setText(datos.getTitleNota());
		 }
		 */
        if (datos != null) {
            Title.setText(datos.getTitleNota());
        }


        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Setdatos Extras
                onClickView(position);
            }

            //Enviar datos a la Class EditView
            private void onClickView(int position) {
                Intent viewDatos = new Intent(getContext(), ViewNota.class);
                viewDatos.putExtra("id", datos.getId());
                viewDatos.putExtra("nombre", datos.getTitleNota());
                viewDatos.putExtra("datos", datos.getDescripcionNota());
                getContext().startActivity(viewDatos);

            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                metodos.showPopupMenu(getContext(), view, datos, AdapterView_Notas.this);
                return false;
            }
        });
        return view;
    }

    public void filtrar(ArrayList<DatosView_Pojo> notasFiltradas) {
        clear();
        if (notasFiltradas != null) {
            addAll(notasFiltradas);
        }
        notifyDataSetChanged();
    }

    public void resetearFiltro() {
        clear();
        addAll(listaOriginal);
        notifyDataSetChanged();
    }
}
