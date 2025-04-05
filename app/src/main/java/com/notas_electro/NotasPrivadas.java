package com.notas_electro;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.notas_electro.Authentication.Configure_key;
import com.notas_electro.Authentication.Verify_key;
import com.notas_electro.Authentication.key_data;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NotasPrivadas extends Activity {

    private static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 123;
    ConstraintLayout ln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nota_private);
        ln = findViewById(R.id.nota_privateLinearLayout);
        Verify_key();

	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_private, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void Verify_key() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager != null && keyguardManager.isKeyguardSecure()) {
            Intent intent = keyguardManager.createConfirmDeviceCredentialIntent("Autenticación requerida", "Por favor, introduce tu PIN para continuar");
			NotasPrivadas.this.setTitle("");
			ln.setVisibility(View.GONE);
            if (intent != null) {
                startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            if (resultCode == RESULT_OK) {
                String key = key_data.getKey(this);

                if (key == "") {
                    Log.d("Configuration the Key", "Iniciando La Configuaracion de una nueva Contraseña");
                    Configure_key.agregarContraseñaNew(this, this);
                } else {
                    Log.d("Configuration the Key", "Se encontro una contraseña porfavor Ingresela");
                    if (isMainActivity(this)) {
						Verify_key.verify_key(this, this, ln);
                    }
                }

            } else {
                finish();
                // El PIN no fue introducido correctamente
            }
        }
    }
    // Función para verificar si la actividad actual es la actividad principal (MainActivity)
    private static boolean isMainActivity(Activity activity) {
        // Verifica si la clase de la actividad es MainActivity
        return activity.getClass().getSimpleName().equals(NotasPrivadas.class.getSimpleName());
    }
}
