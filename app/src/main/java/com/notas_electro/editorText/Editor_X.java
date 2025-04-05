package com.notas_electro.editorText;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import com.material.stylesK.R;
import com.notas_electro.MainActivity;

public class Editor_X extends androidx.appcompat.widget.AppCompatEditText {
    private boolean isInitialText = true;
    private boolean activetxt = true;

    public Editor_X(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.customEditTextStyle);
        init(context, attrs);
    }
	
    private void init(Context context, AttributeSet att) {
        // Inicializar atributos personalizados o configuración
        setHint("Escribe aquí");
        setEnabled(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
		
        addTextChangedListener(new TextWatcher(){

                @Override
                public void beforeTextChanged(CharSequence s, int p2, int p3, int p4) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int Count) {
					// boolean activetxt = false;
					/* if(activetxt&&s.length()>0){
					 MainActivity.preva("HOLA");

					 }else{
					 MainActivity.preva("Bloque else");

					 }
					 */
                    if (isInitialText && s.length() > 0) {
                        if (activetxt && s.length() > 0) {
                            MainActivity.preva("hola");
                            activetxt = false;
                        } else {
                            activetxt = true;
                            MainActivity.preva("else");
                        }
                        isInitialText = false;
                    }
                }

                @Override
                public void afterTextChanged(Editable p1) {
                }
            });

    }

    // Método personalizado
    public void setCustomText(String text) {
        setText(text);

    }
}
