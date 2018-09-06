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

public class NotificationHelper extends ContextWrapper {
    public static final String NYT_CHANNEL_ID = "nytChannelID";
    public static final String NYT_CHANNEL_NAME = "NYT channel";
    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        this.createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nytChannel = new NotificationChannel(NYT_CHANNEL_ID, NYT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            nytChannel.enableLights(true);
            nytChannel.enableVibration(true);
            nytChannel.setLightColor(Color.GREEN);
            getManager().createNotificationChannel(nytChannel);
        }
    }


    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    private PendingIntent getPendingIntent() {
        // Create an Intent for the activity you want to start
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        notificationIntent.putExtra("notificationSearch", read(Key_SEARCHQUERY_NOTIFICATION, ""));
        notificationIntent.putExtra("Key_TAG", 30);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(notificationIntent);
        // Get the PendingIntent containing the entire back stack
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public NotificationCompat.Builder notificationBuilder() {
        return new NotificationCompat.Builder(this, NYT_CHANNEL_ID)
                .setSmallIcon(R.mipmap.mynews_round)
                .setContentTitle("MyNews notification !")
                .setContentText("new article found for you ! ")
                .setContentIntent(getPendingIntent())
                .setStyle(new NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

}
