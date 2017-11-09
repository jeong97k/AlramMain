package com.example.jkh.myapplication;


import android.app.*;

import android.content.*;

import android.graphics.*;

import android.net.*;

import android.os.*;

import android.provider.MediaStore.*;

import android.util.*;

import android.widget.*;

import android.widget.Toast;


        class AlarmReceiver extends BroadcastReceiver {




            private int YOURAPP_NOTIFICATION_ID;



            @Override

                     public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();



        showNotification(context, R.drawable.icon,

                "알람", "알람이 켜졋습니다.");

    }



    private void showNotification(Context context, int statusBarIconID,

                                  String statusBarTextID, String detailedTextID) {

        Intent contentIntent = new Intent(context, test.class);

        PendingIntent theappIntent =

                PendingIntent.getActivity(context, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);





        CharSequence from = "알람";

        CharSequence message = "어떻게 알람종료?";



        Notification notif = new Notification(statusBarIconID, null, System.currentTimeMillis());

        notif.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");//ringURI;

        notif.flags = Notification.FLAG_INSISTENT;

        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(theappIntent)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("SM 알리D미")
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setTicker("DSM 알리미 - 소식 왔어요!");

        notif.ledARGB = Color.GREEN;

        NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);



             nm.notify(1234, notif);



    }


}
