package com.mykotlindemo.ui.activity.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mykotlindemo.R


class NotificationHelper(private val context: Context,private val position: Int,private val bitmapImage: Bitmap?) {
    private val CHANNEL_ID = "BodyRx" // The id of the channel.
    private val NOTIFICATION_ID = 1

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminder Channel Description"
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun Notification() {
        createNotificationChannel()
        val notificationIntent = Intent(context, NotificationActivity::class.java) //on tap this activity will open
        notificationIntent.putExtra("moviePosition",position )
        val bundle = Bundle()
        notificationIntent.putExtras(bundle)
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(NotificationActivity::class.java)
        stackBuilder.addNextIntent(notificationIntent)
        val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE) //getting the pendingIntent

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_home)
            .setLargeIcon(bitmapImage)
            .setAutoCancel(true)
            .setContentTitle("Movie Description")
            .setContentText("This is the Movie Description Notification")
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(bitmapImage).bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

    }
}