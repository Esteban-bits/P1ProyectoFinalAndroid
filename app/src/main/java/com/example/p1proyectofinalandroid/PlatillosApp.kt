package com.example.p1proyectofinalandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager // Necesario
import androidx.recyclerview.widget.RecyclerView // Necesario

class PlatillosApp : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_platillos_app)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerViewPlatillos)

        val listaDePlatillos = generarPlatillosDeEjemplo()

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = PlatillosAdapter(listaDePlatillos)
        recyclerView.adapter = adapter

        NavigationController.setupBottomNavigation(this, PlatillosApp::class.java)
    }

    private fun generarPlatillosDeEjemplo(): List<Platillo> {
        val defaultImage = R.drawable.ic_launcher_background

        return listOf(
            Platillo(
                "Ensalada Quinoa y Pollo",
                "Una mezcla alta en fibra y proteína con verduras frescas.",
                "Calorías: 350 kcal | Proteína: 30g | Fibra: 8g",
                defaultImage
            ),
            Platillo(
                "Salmón al Horno con Espárragos",
                "Rico en Omega-3 y grasas saludables, ideal para el corazón.",
                "Calorías: 420 kcal | Proteína: 40g | Carbohidratos: 5g",
                defaultImage
            ),
            Platillo(
                "Tazón de Burrito Light",
                "Versión sin arroz, con frijoles, maíz, aguacate y aderezo bajo en grasa.",
                "Calorías: 400 kcal | Proteína: 28g | Grasas: 15g",
                defaultImage
            ),
            Platillo(
                "Pechuga de Pavo a la Parrilla",
                "Proteína magra acompañada de vegetales al vapor para una digestión ligera.",
                "Calorías: 300 kcal | Proteína: 45g | Grasas: 5g",
                defaultImage
            ),
            Platillo(
                "Sopa de Lentejas y Verduras",
                "Un clásico confortante, alto en hierro y bajo en calorías.",
                "Calorías: 250 kcal | Proteína: 15g | Fibra: 10g",
                defaultImage
            ),
            Platillo(
                "Tostadas de Atún Fresco",
                "Una opción fresca y rápida, usando tostadas horneadas en lugar de fritas.",
                "Calorías: 380 kcal | Proteína: 35g | Carbohidratos: 30g",
                defaultImage
            ),
            Platillo(
                "Omelette de Claras con Espinacas",
                "Desayuno o cena rica en proteína, bajo en yemas y colesterol.",
                "Calorías: 180 kcal | Proteína: 20g | Grasas: 5g",
                defaultImage
            ),
            Platillo(
                "Bowl de Avena con Frutas del Bosque",
                "Ideal para el desayuno, con avena integral y antioxidantes.",
                "Calorías: 320 kcal | Proteína: 10g | Fibra: 6g",
                defaultImage
            ),
            Platillo(
                "Pescado Blanco en Papillote",
                "Cocido al vapor en papel, conservando todos sus nutrientes y sabor.",
                "Calorías: 280 kcal | Proteína: 38g | Grasas: 4g",
                defaultImage
            ),
            Platillo(
                "Wrap de Garbanzos y Hummus",
                "Opción vegetariana alta en proteína y fibra para llevar.",
                "Calorías: 360 kcal | Proteína: 18g | Carbohidratos: 45g",
                defaultImage
            )
        )
    }
}