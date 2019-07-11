package ltd.kaizo.mynews.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import com.google.gson.Gson

import ltd.kaizo.mynews.model.SearchQuery

/**
 * Class to record and read data from the SharedPreferences
 */
object DataRecordManager {

    private var sharedPreferences: SharedPreferences? = null

    /**
     * Init.
     *
     * @param context the context
     */
    fun init(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        }
    }

    /**
     * Read string.
     *
     * @param key      the key
     * @param defValue the def value
     * @return the string
     */
    fun read(key: String, defValue: String): String {
        return sharedPreferences!!.getString(key, defValue)
    }

    /**
     * Write String value
     *
     * @param key   the key
     * @param value the value
     */
    fun write(key: String, value: String) {
        val prefsEditor = sharedPreferences!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    /**
     * Read boolean.
     *
     * @param key      the key
     * @param defValue the def value
     * @return the boolean
     */
    fun read(key: String, defValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key, defValue)
    }

    /**
     * Write boolean value
     *
     * @param key   the key
     * @param value the value
     */
    fun write(key: String, value: Boolean) {
        val prefsEditor = sharedPreferences!!.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    /**
     * Read integer.
     *
     * @param key      the key
     * @param defValue the def value
     * @return the integer
     */
    fun read(key: String, defValue: Int): Int {
        return sharedPreferences!!.getInt(key, defValue)
    }

    /**
     * Write int value
     *
     * @param key   the key
     * @param value the value
     */
    fun write(key: String, value: Int?) {
        val prefsEditor = sharedPreferences!!.edit()
        prefsEditor.putInt(key, value!!).apply()
    }

    /**
     * Save data.
     *
     * @param position the position
     * @param section  the section
     */
    fun saveData(position: Int, section: String) {
        write(KEY_SECTION, section)
        write(KEY_POSITION, position)
        Log.i("info", "data saved to sharedPreferences files")
    }

    /**
     * Gets searchQuery from sharedPreferences.
     *
     * @param KEY the key
     * @return the searchQuery object from sharedPreferences
     */
    fun getSearchQueryFromSharedPreferences(KEY: String): SearchQuery {
        val gson = Gson()
        val gsonStr = ""
        return gson.fromJson(read(KEY, gsonStr), SearchQuery::class.java)
    }


}
/**
 * Instantiates a new Data record manager.
 */
