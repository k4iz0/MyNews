package ltd.kaizo.mynews.Model.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import ltd.kaizo.mynews.Model.SearchQuery;

/**
 * The type Data record manager.
 */
public class DataRecordManager {
    /**
     * The constant KEY_SECTION.
     */
    public static final String KEY_SECTION = "home";
    /**
     * The constant KEY_SECTION_CUSTOM
     */
    public static final String KEY_SECTION_CUSTOM = "SCIENCE";
    /**
     * The constant Key_POSITION.
     */
    public static final String Key_POSITION = "0";
    /**
     * The constant Key_SEARCHQUERY.
     */
    public static final String Key_SEARCHQUERY = "";
    /**
     * The constant Key_SEARCHQUERY_NOTIFICATION.
     */
    public static final String Key_SEARCHQUERY_NOTIFICATION = "";
    /**
     * The constant Key_TAG.
     */
    public static final String Key_TAG = "";
    /**
     * The constant sharedPreferences.
     */
    private static SharedPreferences sharedPreferences;

    /**
     * Instantiates a new Data record manager.
     */
    private DataRecordManager() {
    }

    /**
     * Init.
     *
     * @param context the context
     */
    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        }
    }

    /**
     * Read string.
     *
     * @param key      the key
     * @param defValue the def value
     * @return the string
     */
    public static String read(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    /**
     * Write String value
     *
     * @param key   the key
     * @param value the value
     */
    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    /**
     * Read boolean.
     *
     * @param key      the key
     * @param defValue the def value
     * @return the boolean
     */
    public static boolean read(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * Write boolean value
     *
     * @param key   the key
     * @param value the value
     */
    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    /**
     * Read integer.
     *
     * @param key      the key
     * @param defValue the def value
     * @return the integer
     */
    public static Integer read(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * Write int value
     *
     * @param key   the key
     * @param value the value
     */
    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value).apply();
    }

    /**
     * Save data.
     *
     * @param position    the position
     * @param section     the section
     */
    public static void saveData(int position, String section) {
        write(KEY_SECTION, section);
        write(Key_POSITION, position);
        Log.i("info", "data saved to sharedPreferences files");
    }

    /**
     * Gets searchQuery from sharedPreferences.
     *
     * @param KEY the key
     * @return the searchQuery object from sharedPreferences
     */
    public static SearchQuery getSearchQueryFromSharedPreferences(String KEY) {
        Gson gson = new Gson();
        String gsonStr="";
        return gson.fromJson(read(KEY, gsonStr), SearchQuery.class);
    }


}
