package com.notas_electro.editorText;
import android.app.Activity;
import com.notas_electro.MainActivity;
import jp.wasabeef.richeditor.RichEditor;

/**
 * @Author Anael Reyes
 * @Date 2024/09/15 11:33
 * @Describe Escuchador del Texto del Editor
 */
public class ListenerText {
    private static boolean isInitialText = true;
    private static boolean activetxt = true;
    public static void textListener(RichEditor editotText){
        editotText.setOnTextChangeListener(new RichEditor.OnTextChangeListener(){

                @Override
                public void onTextChange(String text) {
                    if (isInitialText && text.length() > 0) {
                        if (activetxt && text.length() > 0) {
                            MainActivity.preva("hola");
                            activetxt = false;
                        } else {
                            activetxt = true;
                            MainActivity.preva("else");
                        }
                        isInitialText = false;
                    }
                }
            });
        
    }
    
}
