package ltd.kaizo.mynews.Model.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import ltd.kaizo.mynews.Controller.Activities.NotificationActivity;
import ltd.kaizo.mynews.R;

import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_SEARCHQUERY_NOTIFICATION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.read;

/**
 * The type Notification helper.
 */
public class NotificationHelper extends ContextWrapper {
    /**
     * The constant NYT_CHANNEL_ID.
     */
    public static final String NYT_CHANNEL_ID = "nytChannelID";
    /**
     * The constant NYT_CHANNEL_NAME.
     */
    public static final String NYT_CHANNEL_NAME = "NYT channel";
    /**
     * The Manager.
     */
    private NotificationManager manager;

    /**
     * Instantiates a new Notification helper.
     *
     * @param base the base
     */
    public NotificationHelper(Context base) {
        super(base);
        this.createNotificationChannel();
    }

    /**
     * Create notification channel for Device with API >= 26
     */
    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nytChannel = new NotificationChannel(NYT_CHANNEL_ID, NYT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            nytChannel.enableLights(true);
            nytChannel.enableVibration(true);
            nytChannel.setLightColor(Color.GREEN);
            getManager().createNotificationChannel(nytChannel);
        }
    }


    /**
     * Getter for the notification manager.
     *
     * @return the manager
     */
    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    /**
     * Create an Intent for the NotificationActivity and pass the search specification
     * and key tag via extra
     * create the TaskStackBuilder
     *
     * @return the pending intent
     */
    private PendingIntent getPendingIntent() {
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        notificationIntent.putExtra(getString(R.string.notificationSearchExtra), read(Key_SEARCHQUERY_NOTIFICATION, ""));
        notificationIntent.putExtra(getString(R.string.Key_TAGExtra), 30);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(notificationIntent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     * Define the Notification builder with text, style and pending intent
     *
     * @return the notification compat . builder
     */
    public NotificationCompat.Builder notificationBuilder() {
        return new NotificationCompat.Builder(this, NYT_CHANNEL_ID)
                .setSmallIcon(R.mipmap.mynews_round)
                .setContentTitle(getString(R.string.notification))
                .setContentText(getString(R.string.article_found))
                .setContentIntent(getPendingIntent())
                .setStyle(new NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

}
