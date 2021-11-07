package com.example.week7_sec4_notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var msg: EditText
    lateinit var show: Button

    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var builder: Notification.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        msg = findViewById(R.id.note_ED)
        show = findViewById(R.id.show_btn)

        show.setOnClickListener {
            var note = msg.text.toString()

            if (note.isNotEmpty()) {
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                val intent = Intent(this, MainActivity::class.java)
                val pendingIntent =
                    PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    var notificationChannel = NotificationChannel(
                        channelId, description,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationManager.createNotificationChannel(notificationChannel)
                    builder = Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("My Notification")
                        .setContentText(note)

                } else {
                    builder = Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("My Notification")
                        .setContentText(note)
                    print("yes vvvvvv")
                }
                notificationManager.notify(1234, builder.build())
            } else Toast.makeText(this, "Please Enter your notifications msg ", Toast.LENGTH_SHORT).show()

        }
    }
}
