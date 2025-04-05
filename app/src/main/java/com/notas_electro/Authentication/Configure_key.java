package com.notas_electro.Authentication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.notas_electro.R;

public class Configure_key {

    public static void agregarContraseñaNew(final Activity activity , final Context context) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setView(R.layout.new_password);
        dialogBuilder.setNegativeButton("Crear mas Tarde", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface p1, int p2) {
                    activity.finish();
                }
            });
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final TextView Tv = alertDialog.findViewById(R.id.newpasswordTV);

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

        ok.setText("Next");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onSetCaracteresIniciales(view, Tv, activity, context, alertDialog);
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
        del.setOnClickListener(onClickListener);
    }

    public static void onSetCaracteresIniciales(View v, TextView Tv, Activity activity, Context context, AlertDialog AlertDialog) {
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
            Log.d("Configuration the Key", "Digitos Actuales :'" + code + "'");
            if (code.equals("")) {
                Toast.makeText(context, "Ingresa 4 digitos", Toast.LENGTH_SHORT).show();
            } else {
                Confirmar_Contraseña_new(activity, context, code);
                Log.d("Configuration the Key", "Se Enviaron los siguientes dijitos " + code + "Para Confirmar el Pin");
                Tv.setText("");
                AlertDialog.dismiss();
            }
        } else if (v.getId() == R.id.new_passwordBtn_del) {
            eliminarUltimoCaracter(Tv);
        }
    }


    public static void Confirmar_Contraseña_new(final Activity activity , final Context context, final String a) {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setView(R.layout.new_password);

        dialogBuilder.setNegativeButton("Terminar", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface p1, int p2) {
                    activity.finish();
                }
            });
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final TextView Tv = alertDialog.findViewById(R.id.newpasswordTV);
        TextView txt = alertDialog.findViewById(R.id.new_passwordTextView);
        txt.setText("Confirmar Contraseña");

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

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetCaracteresForConfirmacion(view, Tv, context, activity, a, alertDialog);
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
        del.setOnClickListener(onClickListener);
    }
    public static void onSetCaracteresForConfirmacion(View v, TextView Tv, Context context, Activity activity, String a, AlertDialog Alert) {
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
            String code1 = a;
            String codeActual = Tv.getText().toString();
            Log.d("Configuration the Key", "Code Actual Ingresado " + codeActual + " para hacer la Confirmacion");

            if (code1.equals(codeActual)) {

                comparandoCaracteresForConfirmation(activity, codeActual, Alert);

            } else {
                Log.d("Configuration the Key", "El Usuario no ingreso ningun Caracter");
                Toast.makeText(context, "Confirma los Caracteres Anteriores", Toast.LENGTH_SHORT).show();
            }
        }else if (v.getId() == R.id.new_passwordBtn_del) {

                eliminarUltimoCaracter(Tv);

        }
    }

    public static void eliminarUltimoCaracter(TextView textView) {
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
    public static void comparandoCaracteresForConfirmation(Activity Activity, String codeActual, AlertDialog Alert) {

        String code = stringCifrado(codeActual);
        key_data.setKey(Activity, code);  
        String key = key_data.getKey(Activity);
        Log.d("Configuration the Key", "Se guardo el siguiente Code " + key + " en las preferencias");
        Alert.dismiss();

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
}
