package com.example.p1proyectofinalandroid // Asegúrate de usar tu paquete correcto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlatillosAdapter(private val listaPlatillos: List<Platillo>) :
    RecyclerView.Adapter<PlatillosAdapter.PlatilloViewHolder>() {

    class PlatilloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPlatillo: ImageView = itemView.findViewById(R.id.imgPlatillo)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombrePlatillo)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionPlatillo)
        val tvNutrimental: TextView = itemView.findViewById(R.id.tvNutrimentalPlatillo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_platillo, parent, false)
        return PlatilloViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatilloViewHolder, position: Int) {
        val platillo = listaPlatillos[position]
        holder.tvNombre.text = platillo.nombre
        holder.tvDescripcion.text = platillo.descripcion
        holder.tvNutrimental.text = platillo.infoNutrimental
        holder.imgPlatillo.setImageResource(platillo.imagenResId)
    }

    override fun getItemCount(): Int {
        return listaPlatillos.size
    }
}