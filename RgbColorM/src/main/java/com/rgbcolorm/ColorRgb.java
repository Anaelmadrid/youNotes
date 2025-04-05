package com.rgbcolorm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorRgb {
    ColorSelectedListener colorSelectedListener;
    public void ColorSelectedListener(ColorSelectedListener onColorSelected) {
        this.colorSelectedListener = onColorSelected;
    }
    public interface ColorSelectedListener {
        void onColorSelected(int color);
    }

    public static void colorRgb(Context context, final ColorSelectedListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Select Color");
        View dialogView = LayoutInflater.from(context).inflate(R.layout.color_rgb, null);
        dialog.setView(dialogView);

        final SeekBar redSeekBar = dialogView.findViewById(R.id.redSeekBar);
        final SeekBar greenSeekBar = dialogView.findViewById(R.id.greenSeekBar);
        final SeekBar blueSeekBar = dialogView.findViewById(R.id.blueSeekBar);
        final SeekBar alphaSeekBar = dialogView.findViewById(R.id.activitymainSeekBar_alpa);
        final View colorPreview = dialogView.findViewById(R.id.colorPreview);
        final TextView colorCode = dialogView.findViewById(R.id.colorCode);

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateColorPreview(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress(), alphaSeekBar.getProgress(), colorPreview, colorCode, listener);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateColorPreview(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress(), alphaSeekBar.getProgress(), colorPreview, colorCode, listener);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateColorPreview(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress(), alphaSeekBar.getProgress(), colorPreview, colorCode, listener);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

        alphaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateColorPreview(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress(), alphaSeekBar.getProgress(), colorPreview, colorCode, listener);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Aquí puedes realizar alguna acción cuando se hace clic en Ok
                }
            });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private static void updateColorPreview(int red, int green, int blue, int alpha, View colorPreview, TextView colorCode, ColorSelectedListener listener) {
        float[] hsv = new float[3];
        Color.RGBToHSV(red, green, blue, hsv);

        float luminosityFactor = 1.0f;
        hsv[2] *= luminosityFactor;

        int modifiedColor = Color.HSVToColor(alpha, hsv);

        colorPreview.setBackgroundColor(modifiedColor);
        colorCode.setText(String.format("#%02X%02X%02X%02X", alpha, red, green, blue));

        if (listener != null) {
            listener.onColorSelected(modifiedColor);
        }
    }
}
