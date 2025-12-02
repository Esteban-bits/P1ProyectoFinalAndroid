package com.example.p1proyectofinalandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.math.pow

class CalculadoraIMC : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var tvLabelPeso: TextView
    private lateinit var seekBarPeso: SeekBar
    private lateinit var tvLabelEstatura: TextView
    private lateinit var seekBarEstatura: SeekBar
    private lateinit var btnCalcular: Button

    private val MIN_PESO_PROGRESS = 200
    private val MIN_ESTATURA_PROGRESS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora_imc)

        auth = Firebase.auth

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvLabelPeso = findViewById(R.id.tvLabelPeso)
        seekBarPeso = findViewById(R.id.seekBarPeso)
        tvLabelEstatura = findViewById(R.id.tvLabelEstatura)
        seekBarEstatura = findViewById(R.id.seekBarEstatura)
        btnCalcular = findViewById(R.id.btnCalcularIMC)

        configurarSelectorPeso()
        configurarSelectorEstatura()

        val btnAtrasInicio = findViewById<View>(R.id.btnAtrasInicio)

        btnAtrasInicio.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Sesión cerrada exitosamente.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginApp::class.java)

            // Limpiar la pila de actividades para que el usuario no pueda volver a esta pantalla
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        btnCalcular.setOnClickListener {
            calcularIMC()
        }

        NavigationController.setupBottomNavigation(this, CalculadoraIMC::class.java)
    }


    private fun configurarSelectorPeso() {
        seekBarPeso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val peso = (progress + MIN_PESO_PROGRESS) / 10.0
                tvLabelPeso.text = "Peso: %.1f kg".format(peso)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        val pesoInicial = (seekBarPeso.progress + MIN_PESO_PROGRESS) / 10.0
        tvLabelPeso.text = "Peso: %.1f kg".format(pesoInicial)
    }

    private fun configurarSelectorEstatura() {
        seekBarEstatura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val estatura = (progress + MIN_ESTATURA_PROGRESS) / 100.0
                tvLabelEstatura.text = "Estatura: %.2f m".format(estatura)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        val estaturaInicial = (seekBarEstatura.progress + MIN_ESTATURA_PROGRESS) / 100.0
        tvLabelEstatura.text = "Estatura: %.2f m".format(estaturaInicial)
    }

    private fun calcularIMC() {
        val pesoProgress = seekBarPeso.progress
        val estaturaProgress = seekBarEstatura.progress

        val peso = (pesoProgress + MIN_PESO_PROGRESS) / 10.0
        val estatura = (estaturaProgress + MIN_ESTATURA_PROGRESS) / 100.0

        val imc = peso / estatura.pow(2.0)

        val fragmento = ResultadoIMCFragment.newInstance(imc, peso, estatura)
        fragmento.show(supportFragmentManager, "ResultadoIMC")
    }
}