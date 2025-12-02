package com.example.p1proyectofinalandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.p1proyectofinalandroid.databinding.ActivityLoginAppBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginApp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityLoginAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        ViewCompat.setOnApplyWindowInsetsListener(binding.principal) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnInicioSesion.setOnClickListener {
            iniciarSesion()
        }

        binding.enlaceRegistro.setOnClickListener {
            val intent = Intent(this, RegistroApp::class.java)
            startActivity(intent)
        }

        binding.btnAtras.setOnLongClickListener {
            auth.signOut()
            Toast.makeText(this, "SESIÓN CERRADA. Ejecuta la app de nuevo.", Toast.LENGTH_LONG).show()

            finish()
            true
        }
    }

    private fun iniciarSesion() {
        val email = binding.cajaEntradaEmail.text.toString().trim()
        val password = binding.cajaEntradaContrasena.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Bienvenido, ${user?.email}", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, BienvenidaApp::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        baseContext,
                        "El correo o la contraseña son incorrectos. Inténtalo de nuevo.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(this, "Usuario ya autenticado, redirigiendo a InicioApp...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, BienvenidaApp::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}