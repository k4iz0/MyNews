package ltd.kaizo.mynews.Model.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import ltd.kaizo.mynews.R;

public class NotificationHelper extends ContextWrapper {
    public static final String NYT_CHANNEL_ID = "nytChannelID";
    public static final String NYT_CHANNEL_NAME = "NYT channel";
    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        this.createNotification();
    }

    private void createNotification() {
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


    public NotificationCompat.Builder notificationBuilder() {
        return new NotificationCompat.Builder(this,NYT_CHANNEL_ID)
                .setSmallIcon(R.mipmap.mynews_round)
                .setContentTitle("MyNews notification !")
                .setContentText("new article found for you ! ")
                .setStyle(new NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

}
