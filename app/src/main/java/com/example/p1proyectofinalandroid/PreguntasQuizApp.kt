package com.example.p1proyectofinalandroid

// =======================================================
// ESTRUCTURA DE DATOS PARA EL QUIZ DE HÁBITOS DE SALUD
// =======================================================

data class Option(
    val text: String,
    val score: Int
)

data class Question(
    val id: Int,
    val text: String,
    val options: List<Option>
)

data class ResultRange(
    val minScore: Int,
    val maxScore: Int,
    val title: String,
    val quality: String,
    val recommendations: String
)

object HealthQuizData {
    const val MAX_POSSIBLE_SCORE = 24

    val questions = listOf(
        Question(
            id = R.id.q1,
            text = "¿Cuántas horas duermes en promedio por noche?",
            options = listOf(
                Option("Menos de 6 horas", 1),
                Option("Entre 6 y 7 horas", 2),
                Option("8 horas o más", 3)
            )
        ),
        Question(
            id = R.id.q2,
            text = "¿Con qué frecuencia consumes frutas y verduras?",
            options = listOf(
                Option("Raramente (menos de 2 veces a la semana)", 1),
                Option("Algunos días (3 a 5 veces a la semana)", 2),
                Option("Todos los días (al menos una porción de cada uno)", 3)
            )
        ),
        Question(
            id = R.id.q3,
            text = "¿Cuántas veces a la semana realizas actividad física moderada o intensa?",
            options = listOf(
                Option("0-1 vez por semana", 1),
                Option("2-3 veces por semana", 2),
                Option("4 o más veces por semana", 3)
            )
        ),
        Question(
            id = R.id.q4,
            text = "¿Con qué frecuencia bebes agua pura (sin contar otras bebidas)?",
            options = listOf(
                Option("Menos de 1 litro al día", 1),
                Option("Entre 1 y 2 litros al día", 2),
                Option("Más de 2 litros al día", 3)
            )
        ),
        Question(
            id = R.id.q5,
            text = "¿Cuánto tiempo pasas usando pantallas (móvil, PC, TV) fuera del trabajo/estudio?",
            options = listOf(
                Option("Más de 4 horas", 1),
                Option("Entre 2 y 4 horas", 2),
                Option("Menos de 2 horas", 3)
            )
        ),
        Question(
            id = R.id.q6,
            text = "¿Con qué frecuencia consumes comida rápida o procesada?",
            options = listOf(
                Option("Más de 3 veces por semana", 1),
                Option("1-2 veces por semana", 2),
                Option("Raramente o nunca", 3)
            )
        ),
        Question(
            id = R.id.q7,
            text = "¿Cómo manejas el estrés y la ansiedad?",
            options = listOf(
                Option("Casi nunca hago pausas o técnicas de relajación", 1),
                Option("Ocasionalmente practico alguna técnica o hobby", 2),
                Option("Uso técnicas de relajación o meditación a diario", 3)
            )
        ),
        Question(
            id = R.id.q8,
            text = "¿Con qué frecuencia te tomas un tiempo para ti (hobbies, lectura, etc.)?",
            options = listOf(
                Option("Raramente o nunca", 1),
                Option("Una o dos veces a la semana", 2),
                Option("Varias veces a la semana o a diario", 3)
            )
        )
    )

    val resultRanges = listOf(
        ResultRange(
            minScore = 8, maxScore = 14,
            title = "¡Atención a tus Hábitos!",
            quality = "Hábitos necesitan mejoras significativas.",
            recommendations = "Necesitas enfocarte en 3 áreas clave: dormir 8h, beber más agua y reducir el consumo de procesados. Considera establecer una rutina semanal de ejercicios."
        ),
        ResultRange(
            minScore = 15, maxScore = 20,
            title = "¡Buenos Hábitos!",
            quality = "Tienes buenos hábitos, con áreas de oportunidad.",
            recommendations = "Sigue manteniendo tu nivel de actividad física y tu dieta balanceada. Puedes mejorar tu puntaje enfocándote en una mejor gestión del estrés o reduciendo el tiempo de pantalla."
        ),
        ResultRange(
            minScore = 21, maxScore = 24,
            title = "¡Súper Estrella de la Salud!",
            quality = "Excelentes hábitos y estilo de vida envidiable.",
            recommendations = "¡Felicidades! Mantén estos hábitos consistentes. Tu enfoque debe ser la sostenibilidad a largo plazo. Inspira a otros con tu disciplina y bienestar."
        )
    )
}