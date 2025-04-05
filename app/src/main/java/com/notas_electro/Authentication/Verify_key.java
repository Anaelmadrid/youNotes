package com.notas_electro.Authentication;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.notas_electro.R;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Verify_key {

    public static void verify_key(final Activity activity, final Context context, final ConstraintLayout ln) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setView(R.layout.new_password);

        dialogBuilder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    activity.finish();
                }
            });
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        final TextView Tv = alertDialog.findViewById(R.id.newpasswordTV);
        TextView txt = alertDialog.findViewById(R.id.new_passwordTextView);

        txt.setText("Ingresa tu Contraseña");
        Button one = alertDialog.findViewById(R.id.newpasswordBtn_One);
        Button two = alertDialog.findViewById(R.id.newpasswordBtn_two);
        Button three = alertDialog.findViewById(R.id.newpasswordBtn_three);
        Button four = alertDialog.findViewById(R.id.newpasswordBtn_four);
        Button five = alertDialog.findViewById(R.id.newpasswordBtn_five);
        Button six = alertDialog.findViewById(R.id.newpasswordBtn_six);
        Button seven = alertDialog.findViewById(R.id.newpasswordBtn_seven);
        Button height = alertDialog.findViewById(R.id.newpasswordBtn_height);
        Button nine = alertDialog.findViewById(R.id.newpasswordBtn_nine);
        Button sero = alertDialog.findViewById(R.id.newpasswordBtn_sero);
        Button ok = alertDialog.findViewById(R.id.newpasswordBtn_ok);
        Button del = alertDialog.findViewById(R.id.new_passwordBtn_del);

        ok.setText("Ok");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetCaracteres(view, Tv, activity, context, alertDialog, ln);
            }
        };
        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        four.setOnClickListener(onClickListener);
        five.setOnClickListener(onClickListener);
        six.setOnClickListener(onClickListener);
        seven.setOnClickListener(onClickListener);
        height.setOnClickListener(onClickListener);
        nine.setOnClickListener(onClickListener);
        sero.setOnClickListener(onClickListener);
        ok.setOnClickListener(onClickListener);
        del.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_UP:
                            eliminarUltimoCaracter(Tv, v);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            // Detener el temporizador cuando se suelta el botón o se cancela el evento

                            break;
                    }
                    return true;
                }});

    }

    public static void onSetCaracteres(View v, TextView Tv, Activity activity, Context context, AlertDialog alertDialog, final ConstraintLayout ln) {
        if (v.getId() == R.id.newpasswordBtn_One) {
            Tv.append("1");
        } else if (v.getId() == R.id.newpasswordBtn_two) {
            Tv.append("2");
        } else if (v.getId() == R.id.newpasswordBtn_three) {
            Tv.append("3");
        } else if (v.getId() == R.id.newpasswordBtn_four) {
            Tv.append("4");
        } else if (v.getId() == R.id.newpasswordBtn_five) {
            Tv.append("5");
        } else if (v.getId() == R.id.newpasswordBtn_six) {
            Tv.append("6");
        } else if (v.getId() == R.id.newpasswordBtn_seven) {
            Tv.append("7");
        } else if (v.getId() == R.id.newpasswordBtn_height) {
            Tv.append("8");
        } else if (v.getId() == R.id.newpasswordBtn_nine) {
            Tv.append("9");
        } else if (v.getId() == R.id.newpasswordBtn_sero) {
            Tv.append("0");
        } else if (v.getId() == R.id.newpasswordBtn_ok) {
                String code = Tv.getText().toString();
                if (code.equals("")) {
                    Log.d("Configuration the Key", "Contraseña vacía");
                    Toast.makeText(context, "Ingresa tu contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    passwordSuccessful(activity, context, code, alertDialog, ln);
                }

//            case R.id.new_passwordBtn_del:
//                eliminarUltimoCaracter(Tv);
//                break;
        }
    }

    public static void eliminarUltimoCaracter(TextView textView, View view) {

        // Obtener el texto actual del TextView
        String textoActual = textView.getText().toString();

        // Verificar si hay caracteres para eliminar
        if (!textoActual.isEmpty()) {
            // Eliminar el último carácter
            String nuevoTexto = textoActual.substring(0, textoActual.length() - 1);

            // Establecer el nuevo texto en el TextView
            textView.setText(nuevoTexto);
        }

    }

    public static void passwordSuccessful(Activity activity, Context context, String Code, AlertDialog Dialog, ConstraintLayout ln) {
        String key = key_data.getKey(activity).toString();
        String codeCifrado = stringCifrado(Code);
        if (key.equals(codeCifrado)) {
            Log.d("Configuration the Key", "Contraseña correcta");
            Dialog.dismiss();
            activity.setTitle("Notas Privadas");
            ln.setVisibility(View.VISIBLE);
        } else {
            Log.d("Configuration the Key", "Contraseña incorrecta");
            Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    public static String stringCifrado(String Code) {
        String codeCifrado = Code.replaceAll("0", "@")
            .replaceAll("1", "+")
            .replaceAll("2", "×")
            .replaceAll("3", "÷")
            .replaceAll("4", "=")
            .replaceAll("5", "/")
            .replaceAll("6", "_")
            .replaceAll("7", "<")
            .replaceAll("8", ">")
            .replaceAll("9", "[");
        return codeCifrado;
    }


    // Función para verificar si hay múltiples pantallas activas
    private static boolean isMultipleDisplays(Activity activity) {
        DisplayManager displayManager = (DisplayManager) activity.getSystemService(Context.DISPLAY_SERVICE);
        if (displayManager != null) {
            Display[] displays = displayManager.getDisplays();
            return displays.length > 1;
        }
        return false; // No se puede determinar si hay múltiples pantallas
    }
}
