package ltd.kaizo.mynews.Model.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import ltd.kaizo.mynews.Model.SearchQuery;

public class DataRecordManager {
    public static final String KEY_SECTION = "home";
    public static final String Key_POSITION = "0";
    public static final String Key_SEARCHQUERY = "";
    public static final String Key_SEARCHQUERY_NOTIFICATION = "";
    public static final String Key_TAG = "";
    private static SharedPreferences sharedPreferences;

    private DataRecordManager() {
    }

    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        }
    }

    public static String read(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static boolean read(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    public static Integer read(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value).apply();
    }

    public static void saveData(int position, String section, SearchQuery searchQuery) {
        write(KEY_SECTION, section);
        write(Key_POSITION, position);
        Gson gson = new Gson();
        write(Key_SEARCHQUERY, gson.toJson(searchQuery));
        Log.i("info", "data saved to sharedPreferences files");
    }

    public static SearchQuery getSearchQueryFromSharedPreferences(String KEY) {
        Gson gson = new Gson();
        String gsonStr="";
        return gson.fromJson(read(KEY, gsonStr), SearchQuery.class);
    }


}
