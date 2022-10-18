package com.example.notificaciones

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val CanalID = "canal1"  //Valor unico para diferenciarse entre notificaciones
    val CanalNombre = "Notificacion1"
    val NotificacionID = 0
    lateinit var Boton_Notificaciones: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Boton_Notificaciones = findViewById(R.id.button_notification)

        crearCanalDeNotificacion()

        /////////////   Comandos para crear una notificacion    /////////////////////
        val notificacion = NotificationCompat.Builder(this, CanalID) //Se requiere el canal para API 26 o superior pero se ignora para versiones anteriores
                          .setSmallIcon(R.drawable.notification_icon)   //Se coloca un icono a la notificación
                          .setContentTitle("Notificacion de prueba")    //Se coloca el título a la notificación
                          .setContentText("Este es el texto para la notificacion de prueba")    //Se coloca el texto que contendrá la notificación
                          .setPriority(NotificationCompat.PRIORITY_DEFAULT)     //Se coloca la prioridad de la notificación
                          .build()      //Se construye la notificación para que se pueda visualizar

        val notificationManager = NotificationManagerCompat.from(this)

        Boton_Notificaciones.setOnClickListener {
            notificationManager.notify(NotificacionID, notificacion)
        }
    }

    /////////////////  Se crea un canal para las notificaciones porque es necesario para API 26 en adelante     ////////////////////////
    fun crearCanalDeNotificacion() {
        //Se checa si la app está corriendo en API 26 o ANDROID OREO
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val canal = NotificationChannel(CanalID, CanalNombre, NotificationManager.IMPORTANCE_DEFAULT).apply {
                //Configuracion para prender el led con la notificacion y en color verde
                //lightColor = Color.GREEN
                //enableLights(true)
            }

            //Registra el canal en el sistema
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }

    }
}