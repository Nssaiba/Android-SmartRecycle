package com.smartrecycle

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifierConsigneActivity : AppCompatActivity() {

    private lateinit var etTypeDechet: EditText
    private lateinit var etCouleurPoubelle: EditText
    private lateinit var etAFaire: EditText
    private lateinit var etAEviter: EditText
    private lateinit var btnModifier: Button

    private var consigneId: Long = 0L
    private lateinit var consigneActuelle: ConsigneTri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifier_consigne)

        // Récupérer les vues
        etTypeDechet = findViewById(R.id.etTypeDechet)
        etCouleurPoubelle = findViewById(R.id.etCouleurPoubelle)
        etAFaire = findViewById(R.id.etAFaire)
        etAEviter = findViewById(R.id.etAEviter)
        btnModifier = findViewById(R.id.btnModifierConsigne)

        // Récupérer l’ID passé depuis l’activité précédente
        consigneId = intent.getLongExtra("consigne_id", 0L)

        if (consigneId == 0L) {
            Toast.makeText(this, "ID de consigne invalide", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        chargerConsigne()

        btnModifier.setOnClickListener {
            modifierConsigne()
        }
    }

    private fun chargerConsigne() {
        RetrofitClient.instance.getConsigneById(consigneId)
            .enqueue(object : Callback<ConsigneTri> {
                override fun onResponse(call: Call<ConsigneTri>, response: Response<ConsigneTri>) {
                    if (response.isSuccessful) {
                        consigneActuelle = response.body()!!
                        afficherConsigne()
                    } else {
                        Toast.makeText(this@ModifierConsigneActivity, "Erreur chargement", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<ConsigneTri>, t: Throwable) {
                    Toast.makeText(this@ModifierConsigneActivity, "Erreur réseau", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
    }

    private fun afficherConsigne() {
        etTypeDechet.setText(consigneActuelle.typeDeDechet)
        etCouleurPoubelle.setText(consigneActuelle.couleurPoubelle)
        etAFaire.setText(consigneActuelle.aFaire)
        etAEviter.setText(consigneActuelle.aEviter)
    }

    private fun modifierConsigne() {
        val type = etTypeDechet.text.toString().trim()
        val couleur = etCouleurPoubelle.text.toString().trim()
        val aFaire = etAFaire.text.toString().trim()
        val aEviter = etAEviter.text.toString().trim()

        if (type.isEmpty() || couleur.isEmpty() || aFaire.isEmpty() || aEviter.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }

        val consigneModifiee = ConsigneTri(
            id = consigneId,
            typeDeDechet = type,
            couleurPoubelle = couleur,
            aFaire = aFaire,
            aEviter = aEviter
        )

        RetrofitClient.instance.updateConsigne(consigneId, consigneModifiee)
            .enqueue(object : Callback<ConsigneTri> {
                override fun onResponse(call: Call<ConsigneTri>, response: Response<ConsigneTri>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ModifierConsigneActivity, "Consigne modifiée avec succès", Toast.LENGTH_SHORT).show()
                        finish() // Retour à la liste après modification
                    } else {
                        Toast.makeText(this@ModifierConsigneActivity, "Erreur de modification", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ConsigneTri>, t: Throwable) {
                    Toast.makeText(this@ModifierConsigneActivity, "Erreur réseau", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
