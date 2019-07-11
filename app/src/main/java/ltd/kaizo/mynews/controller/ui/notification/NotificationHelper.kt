package ltd.kaizo.mynews.controller.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import ltd.kaizo.mynews.R
import ltd.kaizo.mynews.utils.DataRecordManager.getSearchQueryFromSharedPreferences
import ltd.kaizo.mynews.utils.DataRecordManager.read
import ltd.kaizo.mynews.utils.KEY_SEARCHQUERY_NOTIFICATION
import ltd.kaizo.mynews.utils.NYT_CHANNEL_ID
import ltd.kaizo.mynews.utils.NYT_CHANNEL_NAME

/**
 * The type Notification helper.
 */
class NotificationHelper
/**
 * Instantiates a new Notification helper.
 *
 * @param base the base
 */
(base: Context) : ContextWrapper(base) {
    /**
     * The Manager.
     */
    private var manager: NotificationManager? = null

    /**
     * Create an Intent for the NotificationActivity and pass the search specification
     * and key tag via extra
     * create the TaskStackBuilder
     *
     * @return the pending intent
     */
    private val pendingIntent: PendingIntent?
        get() {
            val notificationIntent = Intent(this, NotificationActivity::class.java)
            notificationIntent.putExtra(getString(R.string.notificationSearchExtra), read(KEY_SEARCHQUERY_NOTIFICATION, ""))
            notificationIntent.putExtra(getString(R.string.Key_TAGExtra), 30)
            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addNextIntentWithParentStack(notificationIntent)
            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

    /**
     * get the term of the research from sharedPreferences
     * and add it to the notification text
     * @return String notification text
     */
    private val notificationText: String
        get() {
            val searchQuery = getSearchQueryFromSharedPreferences(KEY_SEARCHQUERY_NOTIFICATION)
            return getString(R.string.article_found) + " " + searchQuery.queryTerms
        }

    init {
        this.createNotificationChannel()
    }

    /**
     * Create notification channel for Device with API >= 26
     */
    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val nytChannel = NotificationChannel(NYT_CHANNEL_ID, NYT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            nytChannel.enableLights(true)
            nytChannel.enableVibration(true)
            nytChannel.lightColor = Color.GREEN
            getManager()!!.createNotificationChannel(nytChannel)
        }
    }


    /**
     * Getter for the notification manager.
     *
     * @return the manager
     */
    fun getManager(): NotificationManager? {
        if (manager == null) {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return manager
    }

    /**
     * Define the Notification builder with text, style and pending intent
     *
     * @return the notification compat . builder
     */
    fun notificationBuilder(): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, NYT_CHANNEL_ID)
                .setSmallIcon(R.drawable.news_icon_transparent)
                .setContentTitle(getString(R.string.notification))
                .setContentText(notificationText)
                .setContentIntent(pendingIntent)
                .setStyle(NotificationCompat.BigTextStyle())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    }

}
