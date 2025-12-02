package com.example.p1proyectofinalandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p1proyectofinalandroid.R

object NavigationController {

    private val destinationMap = mapOf(
        R.id.nav_quiz to InicioApp::class.java,
        R.id.nav_comidas to PlatillosApp::class.java,
        R.id.nav_credencial to CredencialApp::class.java,
        R.id.nav_lista to Retrofit::class.java,
        R.id.nav_imc to CalculadoraIMC::class.java
    )

    fun setupBottomNavigation(activity: Activity, currentActivityClass: Class<*>) {

        val navBarView = activity.findViewById<android.view.View>(R.id.bottom_nav_include)

        destinationMap.forEach { (viewId, destinationClass) ->
            val iconView = navBarView.findViewById<android.widget.ImageView>(viewId)

            iconView?.setOnClickListener {
                if (destinationClass == currentActivityClass) {
                    Toast.makeText(activity, "Ya estás aquí.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val intent = Intent(activity, destinationClass)

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                activity.startActivity(intent)

                activity.finish()
            }
        }
    }
}