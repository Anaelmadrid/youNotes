package com.rgbcolorm;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ColorRgbX extends Activity {
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar, alphaSeekBar;
    private Button btnGetColor;
    private View colorPreview;
    private EditText colorCode;
    private ColorRgbOptions color = new ColorRgbOptions();
    String colordata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorRgb colorRgb = new ColorRgb(); // Crear una instancia de ColorRgb
        btnGetColor = findViewById(R.id.activitymainButtonGetcolor);
        color.setColorsdata(colordata);
        btnGetColor.setText(color.getColorsdata());
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        alphaSeekBar = findViewById(R.id.activitymainSeekBar_alpa);
        colorCode = findViewById(R.id.colorCode);
        colorPreview = findViewById(R.id.colorPreview);
        try {

            colorRgb.ColorSelectedListener(new ColorRgb.ColorSelectedListener() {
                @Override
                public void onColorSelected(int color) {
                    // Manejar el color seleccionado aquí

                }
            });

            // Llamar al método colorRgb para mostrar el diálogo de selección de color
            colorRgb.colorRgb(getApplicationContext(), colorRgb.colorSelectedListener);
        } catch (Exception e) {

        }
        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        alphaSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        btnGetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                colorRgb.colorRgb(getApplicationContext(), colorRgb.colorSelectedListener);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("error",e.toString());
                }
            }
        });
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // Obtener los valores de los SeekBars
            int red = redSeekBar.getProgress();
            int green = greenSeekBar.getProgress();
            int blue = blueSeekBar.getProgress();
            int alpha = alphaSeekBar.getProgress(); // Corregí el nombre de la variable de 'alpa' a 'alpha'

// Convertir el color RGB a HSV
            float[] hsv = new float[3];
            Color.RGBToHSV(red, green, blue, hsv);

// Ajustar la luminosidad (valor) según sea necesario
            float luminosityFactor = 1.0f; // Factor de luminosidad (0.0f a 1.0f)
            hsv[2] *= luminosityFactor; // Multiplicar el valor por el factor de luminosidad

// Convertir el color HSV modificado a RGB
            int modifiedColor = Color.HSVToColor(alpha, hsv);

// Actualizar la vista previa del color y el código de color
            colorPreview.setBackgroundColor(modifiedColor);
            colorCode.setText(String.format("#%02X%02X%02X%02X", alpha, red, green, blue));
            String sc = String.format("#%02X%02X%02X%02X", alpha, red, green, blue);
            colordata += sc;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

}

