package com.fjz.androidlittlesamples.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.fjz.androidlittlesamples.LauncherActivity;
import com.fjz.androidlittlesamples.R;

public class NotificationDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);
    }

    public void textNotification(View view) {

        Bitmap largeB = BitmapFactory.decodeResource(getResources(), R.mipmap.feiji);

        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("content title")
                .setContentText("content text")
                .setAutoCancel(true)
                .setContentIntent(getPi())
                .setSmallIcon(R.mipmap.ic_launcher2)
                .setShowWhen(false)
                .setWhen(System.currentTimeMillis()-24*3600*1000)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                .setColor(0xff00ff00)
                .setProgress(100, 28, false)
                .setSubText("I am subtext") /**subtext and progress is confict*/
                .setTicker("I am ticker text")
                .setNumber(10)
                .setLargeIcon(largeB);

        Notification n = builder.build();

        nmc.notify(18111, n);
    }





    public void mediaStyleNotification(View view) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder b = new NotificationCompat.Builder(this);
        b.setTicker("I am ticker text");
        b.setContentTitle("I am content title");
        b.setContentText("I am content text");
        b.setContentIntent(getPi());
        b.setSmallIcon(R.mipmap.ic_launcher2);
        b.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.feiji));
        b.setOngoing(true);
        b.setAutoCancel(false);
        b.setShowWhen(false);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle(b);
        style.setShowCancelButton(true);
        b.addAction(R.mipmap.ic_launcher2, "1", null);
        b.addAction(R.mipmap.ic_launcher, "2", null);
        b.addAction(R.drawable.yuanfen_normal, "3", null);
        b.addAction(R.drawable.yuanfen_pressed, "4", null);
        style.setMediaSession(new MediaSessionCompat(this,"MediaSession",
                new ComponentName(NotificationDemoActivity.this,Intent.ACTION_MEDIA_BUTTON),null).getSessionToken());
        style.setShowActionsInCompactView(2, 3);
        b.setStyle(style);

        Notification n = b.build();

        nm.notify(18112, n);

    }

    public void taskStackNotification(View view) {

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder b = new NotificationCompat.Builder(this);

        b.setSmallIcon(R.mipmap.ic_launcher2);
        b.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.yuanfen_normal));
        b.setContentText("Task text");
        b.setContentTitle("Task title");
        b.setShowWhen(true);
        b.setWhen(System.currentTimeMillis());
        b.setAutoCancel(true);
        b.setTicker("Ticker");

        b.setContentIntent(getTaskBuilderPi());

        b.build();

        Notification n = b.build();

        nm.notify(18113, n);
    }


    private PendingIntent getPi() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, LauncherActivity.class), 0);
        return pi;
    }

    public PendingIntent getTaskBuilderPi() {

        Intent intent = new Intent(this, NotificationDemoActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationDemoActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pi = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        return pi;
    }
}
