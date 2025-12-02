package com.example.p1proyectofinalandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
class BienvenidaApp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida_app)

        auth = Firebase.auth

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCerrarSesion = findViewById<android.widget.ImageView>(R.id.btnAtrasInicio)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val etNombreUsuario = findViewById<EditText>(R.id.NombreUsuario)

        btnCerrarSesion.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Sesión cerrada. Redirigiendo a Login.", Toast.LENGTH_SHORT).show()

            // Redirige a LoginApp y limpia la pila de actividades
            val intent = Intent(this, LoginApp::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        btnContinuar.setOnClickListener {

            val nombre = etNombreUsuario.text.toString().trim()

            if (nombre.isNotEmpty()) {
                val intent = Intent(this, InicioApp::class.java)

                intent.putExtra("NOMBRE_USUARIO", nombre)

                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre para continuar.", Toast.LENGTH_LONG).show()
            }
        }
    }
}