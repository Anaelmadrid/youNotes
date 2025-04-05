package com.notas_electro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.notas_electro.editorText.ListenerText;
import com.notas_electro.metodos.view_data;
import jp.wasabeef.richeditor.RichEditor;

public class ViewNota extends Activity {
    private RichEditor editorText;
    private String datos;

	private ImageButton text_italic;

	private ImageButton text_bold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_nota);
        Intent data = getIntent();
        datos = data.getStringExtra("datos");
        editorText = findViewById(R.id.RET_view_nota);
        ListenerText.textListener(editorText);
        editorText.getSettings().setJavaScriptEnabled(true);
        editorText.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
       
        editorText.setHtml(datos);
        editorText.setEnabled(false);
        editorText.setEditorWidth(300);
        editorText.setEditorHeight(300);
		editorText.setPadding(30, 30, 30, 30);
        editorText.setEditorFontColor(getResources().getColor(R.color.colorLight));
        editorText.setEditorBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkDay));
/*
		text_italic = findViewById(R.id.btn_setItalic);
		text_bold = findViewById(R.id.btn_setBold);

        text_bold.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1) {
					editorText.setBold();
				}
			});
		text_italic.setOnClickListener(new Button.OnClickListener() {

				@Override
				public void onClick(View view) {
					editorText.setItalic();
				}
			});*/
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_nota, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

            if (item.getItemId() == R.id.item_editar_nota){
                view_data.editarNota(this, editorText);
                updateNota();

        }
        return false;
    }
    public void updateNota() {
        int d = datos.length();
        editorText.setHtml(String.valueOf(d));
        Log.d("UpdateNota", String.valueOf(d));
    }
}
