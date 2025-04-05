package com.notas_electro.Authentication;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class key_data {

    public static void setKey(Activity activity, String keyTextCode) {
        SharedPreferences keyPref = activity.getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = keyPref.edit();
        editor.putString("KeyA", keyTextCode);
        editor.apply();
    }
    public static String getKey(Activity activity) {
        SharedPreferences keyPref = activity.getSharedPreferences("key", Context.MODE_PRIVATE);
        return keyPref.getString("KeyA", "");
    }
}
