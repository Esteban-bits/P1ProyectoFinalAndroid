package com.example.p1proyectofinalandroid // Asegúrate de usar tu paquete

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlin.math.roundToInt

class ResultadoIMCFragment : DialogFragment() {

    companion object {
        private const val ARG_IMC = "imc"
        private const val ARG_PESO = "peso"
        private const val ARG_ESTATURA = "estatura"

        fun newInstance(imc: Double, peso: Double, estatura: Double): ResultadoIMCFragment {
            val fragment = ResultadoIMCFragment()
            val args = Bundle()
            args.putDouble(ARG_IMC, imc)
            args.putDouble(ARG_PESO, peso)
            args.putDouble(ARG_ESTATURA, estatura)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val imc = arguments?.getDouble(ARG_IMC) ?: 0.0
        val peso = arguments?.getDouble(ARG_PESO) ?: 0.0
        val estatura = arguments?.getDouble(ARG_ESTATURA) ?: 0.0

        val imcRedondeado = (imc * 100).roundToInt() / 100.0

        val (categoria, recomendacion) = obtenerRecomendacion(imc)

        val mensaje = "Tu IMC es: $imcRedondeado\n" +
                "Categoría: $categoria\n\n" +
                "Recomendaciones:\n$recomendacion"

        return AlertDialog.Builder(requireContext())
            .setTitle("Resultado de IMC")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    private fun obtenerRecomendacion(imc: Double): Pair<String, String> {
        return when {
            imc < 18.5 -> Pair("Bajo Peso", "Busca la guía de un nutricionista para asegurar una ingesta calórica adecuada y balanceada. Prioriza alimentos ricos en nutrientes.")
            imc in 18.5..24.9 -> Pair("Peso Normal", "¡Felicidades! Mantén un estilo de vida saludable con ejercicio regular y una dieta balanceada para conservar tu peso.")
            imc in 25.0..29.9 -> Pair("Sobrepeso", "Considera aumentar tu actividad física diaria y reducir el consumo de azúcares y grasas saturadas. Pequeños cambios hacen una gran diferencia.")
            imc >= 30.0 -> Pair("Obesidad", "Es fundamental buscar apoyo profesional (médico y nutricionista) para establecer un plan seguro y efectivo para la pérdida de peso.")
            else -> Pair("Error de Cálculo", "No se pudo clasificar. Revisa los datos ingresados.")
        }
    }
}