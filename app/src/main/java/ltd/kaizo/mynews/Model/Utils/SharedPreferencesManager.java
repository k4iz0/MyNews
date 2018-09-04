package ltd.kaizo.mynews.Model.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static SharedPreferences sharedPreferences;
    public static final String KEY_SECTION = "home";
    public static final String Key_POSITION = "0";
    public static final String Key_SEARCHQUERY = "";

    private SharedPreferencesManager() {}
        public static void init(Context context){
            if (sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            }
        }

        public static String read (String key, String defValue){
            return sharedPreferences.getString(key, defValue);
        }

        public static void write (String key, String value){
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            prefsEditor.putString(key, value);
            prefsEditor.apply();
        }

        public static boolean read (String key,boolean defValue){
            return sharedPreferences.getBoolean(key, defValue);
        }

        public static void write (String key,boolean value){
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            prefsEditor.putBoolean(key, value);
            prefsEditor.apply();
        }

        public static Integer read (String key,int defValue){
            return sharedPreferences.getInt(key, defValue);
        }

        public static void write (String key, Integer value){
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            prefsEditor.putInt(key, value).apply();
        }

}
