package ltd.kaizo.mynews.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * Convert date string.
     *
     * @param str the string yyyy-mm-dd
     * @return the string dd/mm/yy
     */
    fun convertDate(str: String): String {
        val year = str.substring(2, 4)
        val month = str.substring(5, 7)
        val day = str.substring(8, 10)

        return "$day/$month/$year"
    }
    /**
     * Format date string.
     *
     * @param date the date
     * @return the string
     */
     fun formatDate(date: String): String {
        val dateSplit = date.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return dateSplit[2] + dateSplit[1] + dateSplit[0]
    }
}