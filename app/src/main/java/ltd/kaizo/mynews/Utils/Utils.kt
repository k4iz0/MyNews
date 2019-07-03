package ltd.kaizo.mynews.Utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

}