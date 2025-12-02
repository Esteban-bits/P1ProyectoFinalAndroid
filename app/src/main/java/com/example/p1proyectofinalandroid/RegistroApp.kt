package com.example.p1proyectofinalandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.p1proyectofinalandroid.databinding.ActivityRegistroAppBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegistroApp : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroAppBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.principal) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAtras.setOnClickListener {
            val intent = Intent(this, ActivitySeleccionRegistro::class.java)
            startActivity(intent)
            finish()
        }

        binding.enlaceLogin.setOnClickListener {
            val intent = Intent(this, LoginApp::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRegistrar.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val email = binding.cajaEntradaEmail.text.toString().trim()
        val password = binding.cajaEntradaContrasena.text.toString()
        val confirmPassword = binding.cajaEntradaConfirmarContrasena.text.toString()
        val terminosAceptados = binding.cajaTerminos.isChecked

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!terminosAceptados) {
            Toast.makeText(this, "Necesita aceptar términos y condiciones para crear una cuenta.", Toast.LENGTH_LONG).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Contraseña diferente. Las contraseñas deben coincidir.", Toast.LENGTH_LONG).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "¡Usuario creado exitosamente!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LoginApp::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, "El correo ya fue registrado.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Error al registrar: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}