package com.notas_electro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.notas_electro.AddNota;
import com.notas_electro.metodos.add_nota;
import jp.wasabeef.richeditor.RichEditor;
import com.notas_electro.editorText.ListenerText;

public class AddNota extends Activity {

    private RichEditor editorTxt; 
    private boolean dialogVisible = false;
	//private ImageButton text_italic,text_bold,setlink;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nota);

        editorTxt = findViewById(R.id.RET_add_nota);
        editorTxt.setEditorFontColor(getResources().getColor(R.color.colorLight));
        editorTxt.setEditorBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkDay));
        editorTxt.setEditorWidth(300);
        editorTxt.setEditorHeight(300);
		editorTxt.setPadding(30, 30, 30, 30);
		editorTxt.setSuperscript();
		editorTxt.setSubscript();
//
//		text_italic = findViewById(R.id.btn_setItalic);
//		text_bold = findViewById(R.id.btn_setBold);
//		setlink = findViewById(R.id.btn_setlink);
editorTxt.getSettings().setJavaScriptEnabled(true);
        
//		text_bold.setOnClickListener(new ImageButton.OnClickListener(){
//
//				@Override
//				public void onClick(View p1) {
//					editorTxt.setBold();
//					editorTxt.setBullets();
//
//				}
//			});
//		text_italic.setOnClickListener(new ImageButton.OnClickListener() {
//
//				@Override
//				public void onClick(View view) {
//					editorTxt.setItalic();
//					editorTxt.setNumbers();
//				}
//			});
/*
		ImageButton undo =findViewById(R.id.btn_undo);
		undo.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1) {
					editorTxt.undo();
				}
			});
		ImageButton redo =findViewById(R.id.btn_redo);
		redo.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1) {
					editorTxt.redo();
				}
			});
            */
//		setlink.setOnClickListener(new View.OnClickListener() {
//
//				@Override
//				public void onClick(View view) {
//					putlink(view, "https://www.facebook.com/share/r/1RjkGFqZwAzk2MMh/?mibextid=D5vuiz", "facebook");
//				}
//			});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_nota, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int itemId = item.getItemId();
            if (itemId == R.id.menu_save_nota) {

                add_nota.initSave(AddNota.this, this, editorTxt);
                return true;

            }else{
                return false;
            }
    }
    @Override
    public void onBackPressed() {
        if (!dialogVisible) {
            // Si el diálogo no está visible, muestra el diálogo
            add_nota.crearNotaAnonima(AddNota.this, this, editorTxt);
        } else {
            // Si el diálogo está visible, simplemente cierra la actividad
            super.onBackPressed();
        }
    }
	public void putlink(View v, String link , String titulo) {
		editorTxt.insertLink(link, titulo);
		

	}
}
