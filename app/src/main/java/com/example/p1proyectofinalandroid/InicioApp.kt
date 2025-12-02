package com.example.p1proyectofinalandroid

import android.content.Intent // Importación necesaria para manejar la navegación
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class InicioApp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    // Variables de estado del Quiz
    private val vistasQuiz: MutableList<View> = mutableListOf()
    private lateinit var botonCalcular: Button
    private lateinit var layoutResultados: LinearLayout
    private lateinit var tvPuntajeTotal: TextView
    private lateinit var tvCalidadHabitos: TextView
    private lateinit var tvRecomendaciones: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio_app)

        auth = Firebase.auth

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenidaNombre)

        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO")


        val btnAtrasInicio = findViewById<View>(R.id.btnAtrasInicio)

        btnAtrasInicio.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Sesión cerrada exitosamente.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginApp::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        if (nombreUsuario != null && nombreUsuario.isNotEmpty()) {

            tvBienvenida.text = "¡Hola, $nombreUsuario! ¡A conocernos!"
        } else {
            tvBienvenida.text = "¡Bienvenido a la aplicación!"
        }

        configurarInterfazQuiz()

        NavigationController.setupBottomNavigation(this, InicioApp::class.java)
    }


    private fun configurarInterfazQuiz() {
        botonCalcular = findViewById(R.id.btnCalcularHabitos)
        layoutResultados = findViewById(R.id.layoutResultados)
        tvPuntajeTotal = findViewById(R.id.tvPuntajeTotal)
        tvCalidadHabitos = findViewById(R.id.tvCalidadHabitos)
        tvRecomendaciones = findViewById(R.id.tvRecomendaciones)

        vistasQuiz.add(findViewById(R.id.q1))
        vistasQuiz.add(findViewById(R.id.q2))
        vistasQuiz.add(findViewById(R.id.q3))
        vistasQuiz.add(findViewById(R.id.q4))
        vistasQuiz.add(findViewById(R.id.q5))
        vistasQuiz.add(findViewById(R.id.q6))
        vistasQuiz.add(findViewById(R.id.q7))
        vistasQuiz.add(findViewById(R.id.q8))

        HealthQuizData.questions.forEachIndexed { indice, pregunta ->
            val vistaPregunta = vistasQuiz[indice]
            val tvPregunta = vistaPregunta.findViewById<TextView>(R.id.tvQuestionText)
            val grupoOpciones = vistaPregunta.findViewById<RadioGroup>(R.id.rgPregunta)

            tvPregunta.text = "${indice + 1}. ${pregunta.text}"

            pregunta.options.forEachIndexed { indiceOpcion, opcion ->
                val idBotonRadio = resources.getIdentifier("rbOption${indiceOpcion + 1}", "id", packageName)
                val botonRadio = grupoOpciones.findViewById<RadioButton>(idBotonRadio)

                botonRadio.text = opcion.text
                botonRadio.tag = opcion.score
            }
        }

        botonCalcular.setOnClickListener {
            calcularPuntuacion()
        }
    }

    private fun calcularPuntuacion() {
        var puntuacionTotal = 0
        var todoRespondido = true

        HealthQuizData.questions.forEachIndexed { indice, pregunta ->
            val vistaPregunta = vistasQuiz[indice]
            val grupoOpciones = vistaPregunta.findViewById<RadioGroup>(R.id.rgPregunta)
            val idSeleccionado = grupoOpciones.checkedRadioButtonId

            if (idSeleccionado == -1) {
                todoRespondido = false
                Toast.makeText(this, "Por favor, responde la pregunta ${indice + 1} para calcular.", Toast.LENGTH_LONG).show()
                return
            }

            val botonRadioSeleccionado = vistaPregunta.findViewById<RadioButton>(idSeleccionado)

            val puntuacion = (botonRadioSeleccionado.tag as? Int) ?: 0
            puntuacionTotal += puntuacion
        }

        if (todoRespondido) {
            mostrarResultados(puntuacionTotal)
        }
    }


    private fun mostrarResultados(puntuacion: Int) {
        val puntuacionMaxima = HealthQuizData.MAX_POSSIBLE_SCORE

        val resultado = HealthQuizData.resultRanges.find { puntuacion in it.minScore..it.maxScore }

        if (resultado != null) {
            tvPuntajeTotal.text = "Puntaje Total: $puntuacion/$puntuacionMaxima"
            tvCalidadHabitos.text = "Calidad de Hábitos: ${resultado.title} (${resultado.quality})"
            tvRecomendaciones.text = resultado.recommendations

            layoutResultados.visibility = View.VISIBLE
            botonCalcular.visibility = View.GONE

            vistasQuiz.forEach { vista ->
                val grupo = vista.findViewById<RadioGroup>(R.id.rgPregunta)
                for (i in 0 until grupo.childCount) {
                    val rb = grupo.getChildAt(i) as RadioButton
                    rb.isEnabled = false
                }
            }
        } else {
            Toast.makeText(this, "Error: Puntaje no clasificado. Contacta al soporte.", Toast.LENGTH_LONG).show()
        }
    }
}