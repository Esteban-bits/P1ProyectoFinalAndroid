package com.example.p1proyectofinalandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.p1proyectofinalandroid.databinding.ActivitySeleccionRegistroBinding

class ActivitySeleccionRegistro : AppCompatActivity() {
    private lateinit var binding: ActivitySeleccionRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_P1ProyectoFinalAndroid)

        binding = ActivitySeleccionRegistroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, LoginApp::class.java)
            startActivity(intent)
        }

        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroApp::class.java)
            startActivity(intent)
        }
    }
}