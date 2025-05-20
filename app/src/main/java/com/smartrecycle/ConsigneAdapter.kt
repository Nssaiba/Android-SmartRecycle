package com.smartrecycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConsigneAdapter(
    private var consignes: List<ConsigneTri>,
    private val onModifierClick: (ConsigneTri) -> Unit,
    private val onSupprimerClick: (ConsigneTri) -> Unit
) : RecyclerView.Adapter<ConsigneAdapter.ConsigneViewHolder>() {

    class ConsigneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val type = view.findViewById<TextView>(R.id.txtType)
        val couleur = view.findViewById<TextView>(R.id.txtCouleur)
        val faire = view.findViewById<TextView>(R.id.txtAFaire)
        val eviter = view.findViewById<TextView>(R.id.txtAEviter)
        val btnModifier = view.findViewById<Button>(R.id.btnModifier)
        val btnSupprimer = view.findViewById<Button>(R.id.btnSupprimer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsigneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consigne, parent, false)
        return ConsigneViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsigneViewHolder, position: Int) {
        val consigne = consignes[position]
        holder.type.text = consigne.typeDeDechet
        holder.couleur.text = consigne.couleurPoubelle
        holder.faire.text = consigne.aFaire
        holder.eviter.text = consigne.aEviter

        holder.btnModifier.setOnClickListener { onModifierClick(consigne) }
        holder.btnSupprimer.setOnClickListener { onSupprimerClick(consigne) }
    }

    override fun getItemCount() = consignes.size

    fun updateData(newData: List<ConsigneTri>) {
        consignes = newData
        notifyDataSetChanged()
    }
}
