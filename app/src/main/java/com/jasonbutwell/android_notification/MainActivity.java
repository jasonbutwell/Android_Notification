package com.jasonbutwell.android_notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

// https://developer.android.com/guide/topics/ui/notifiers/notifications.html

public class MainActivity extends Activity {

    private int mId = 1;    // notification message ID

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Build our initial Notification using NotificationCompat.Builder
        // This ensures compatibility with earlier API Levels from Android 4 upwards

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)

        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack

        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.sym_action_chat)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setContentIntent(resultPendingIntent)                                          // the content intent - define that here
                        .addAction(android.R.drawable.sym_action_chat, "Chat",resultPendingIntent)      // action 1 - set action and the intent to call
                        .addAction(android.R.drawable.sym_action_email, "Email",resultPendingIntent)    // action 2 - set action and the intent to call
                        .addAction(android.R.drawable.sym_action_call, "Call",resultPendingIntent);     // action 2 - set action and the intent to call

        // Creates an explicit intent for an Activity in your app

        // The stack builder object will contain an artificial back stack for the
        // started Activity.

        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.


        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.

        mNotificationManager.notify( mId, mBuilder.build() );

    }
}
