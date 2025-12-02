package com.example.p1proyectofinalandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class CredencialApp : AppCompatActivity() {

    // Contenedores principales (para alternar visibilidad: Formulario vs. Credencial)
    private lateinit var formContainer: ScrollView
    private lateinit var cardContainer: CardView

    // Elementos del Formulario (Inputs)
    private lateinit var etNombreCompleto: TextInputEditText
    private lateinit var etEdad: TextInputEditText
    private lateinit var etFechaNacimiento: TextInputEditText
    private lateinit var etTelefonoPersonal: TextInputEditText
    private lateinit var etTelefonoApoyo: TextInputEditText
    private lateinit var etAlergias: TextInputEditText
    private lateinit var etEnfermedades: TextInputEditText
    private lateinit var btnCrearCredencial: Button

    // Elementos de la Credencial (Outputs)
    private lateinit var tvCardNombre: TextView
    private lateinit var tvCardEdad: TextView
    private lateinit var tvCardFechaNacimiento: TextView
    private lateinit var tvCardTelPersonal: TextView
    private lateinit var tvCardTelApoyo: TextView
    private lateinit var tvCardAlergias: TextView
    private lateinit var tvCardEnfermedades: TextView
    private lateinit var btnEditarCredencial: Button

    // Botón de la barra superior
    private lateinit var btnAtrasInicio: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_credencial_app)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Inicialización de Vistas
        inicializarVistas()

        // 2. Configuración de Listeners
        btnCrearCredencial.setOnClickListener {
            crearCredencial()
        }

        btnEditarCredencial.setOnClickListener {
            mostrarFormulario() // Permite editar
        }

        btnAtrasInicio.setOnClickListener {
            // Si la credencial está visible, volvemos al formulario para editarla.
            if (cardContainer.visibility == View.VISIBLE) {
                mostrarFormulario()
            } else {
                // Si estamos en el formulario, salimos de la actividad (o implementamos navegación)
                finish()
            }
        }

        NavigationController.setupBottomNavigation(this, CredencialApp::class.java)
    }


    private fun inicializarVistas() {
        formContainer = findViewById(R.id.formContainer)
        cardContainer = findViewById(R.id.cardContainer)
        btnAtrasInicio = findViewById(R.id.btnAtrasInicio)

        etNombreCompleto = findViewById(R.id.etNombreCompleto)
        etEdad = findViewById(R.id.etEdad)
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento)
        etTelefonoPersonal = findViewById(R.id.etTelefonoPersonal)
        etTelefonoApoyo = findViewById(R.id.etTelefonoApoyo)
        etAlergias = findViewById(R.id.etAlergias)
        etEnfermedades = findViewById(R.id.etEnfermedades)
        btnCrearCredencial = findViewById(R.id.btnCrearCredencial)

        tvCardNombre = findViewById(R.id.tvCardNombre)
        tvCardEdad = findViewById(R.id.tvCardEdad)
        tvCardFechaNacimiento = findViewById(R.id.tvCardFechaNacimiento)
        tvCardTelPersonal = findViewById(R.id.tvCardTelPersonal)
        tvCardTelApoyo = findViewById(R.id.tvCardTelApoyo)
        tvCardAlergias = findViewById(R.id.tvCardAlergias)
        tvCardEnfermedades = findViewById(R.id.tvCardEnfermedades)
        btnEditarCredencial = findViewById(R.id.btnEditarCredencial)
    }

    private fun crearCredencial() {
        val nombre = etNombreCompleto.text.toString().trim()
        val edad = etEdad.text.toString().trim()
        val telPersonal = etTelefonoPersonal.text.toString().trim()
        val telApoyo = etTelefonoApoyo.text.toString().trim()

        if (nombre.isEmpty() || edad.isEmpty() || telPersonal.isEmpty() || telApoyo.isEmpty()) {
            Toast.makeText(this, "Por favor, completa Nombre, Edad, Teléfono Personal y Teléfono de Apoyo.", Toast.LENGTH_LONG).show()
            return
        }

        val fechaNacimiento = etFechaNacimiento.text.toString().trim().ifEmpty { "No especificado" }
        val alergias = etAlergias.text.toString().trim().ifEmpty { "Ninguna" }
        val enfermedades = etEnfermedades.text.toString().trim().ifEmpty { "Ninguna" }

        tvCardNombre.text = "Nombre: $nombre"
        tvCardEdad.text = "Edad: $edad años"
        tvCardFechaNacimiento.text = "F. Nacimiento: $fechaNacimiento"
        tvCardTelPersonal.text = "Tel. Personal: $telPersonal"
        tvCardTelApoyo.text = "Tel. Apoyo: $telApoyo"
        tvCardAlergias.text = "Alergias: $alergias"
        tvCardEnfermedades.text = "Enfermedades: $enfermedades"

        mostrarCredencial()
    }

    private fun mostrarCredencial() {
        formContainer.visibility = View.GONE
        cardContainer.visibility = View.VISIBLE
    }

    private fun mostrarFormulario() {
        cardContainer.visibility = View.GONE
        formContainer.visibility = View.VISIBLE
    }
}