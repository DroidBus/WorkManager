package com.example.workmanager.foregroundservices.service

import android.R
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.workmanager.MainActivity


class ForegroundService :Service() {

     val CHANNEL_ID:String= "ForegroundServiceChannel"


    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val input = intent!!.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText(input)
            .setSmallIcon(R.drawable.star_on)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        //do heavy work on a background thread
        //stopSelf();
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
       return null;
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}