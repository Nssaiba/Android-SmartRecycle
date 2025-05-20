package com.smartrecycle

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AjouterConsigneActivity : AppCompatActivity() {


    private lateinit var etCouleurPoubelle: EditText
    private lateinit var etAFaire: EditText
    private lateinit var etAEviter: EditText
    private lateinit var btnAjouter: Button
    private lateinit var btnAnnuler: Button
    private lateinit var spinnerTypeDechet: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_consigne)

        spinnerTypeDechet = findViewById(R.id.spinnerTypeDechet)

        val typesDechet = listOf("Métal", "Papier", "Carton", "Plastique", "Verre")
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, typesDechet)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTypeDechet.adapter = adapterSpinner

        etCouleurPoubelle = findViewById(R.id.etCouleurPoubelle)
        etAFaire = findViewById(R.id.etAFaire)
        etAEviter = findViewById(R.id.etAEviter)
        btnAjouter = findViewById(R.id.btnAjouterConsigne)
        btnAnnuler = findViewById(R.id.btnAnnuler)

        btnAjouter.setOnClickListener {
            val typeDechet = spinnerTypeDechet.selectedItem.toString()
            val couleurPoubelle = etCouleurPoubelle.text.toString().trim()
            val aFaire = etAFaire.text.toString().trim()
            val aEviter = etAEviter.text.toString().trim()

            if (typeDechet.isEmpty() || couleurPoubelle.isEmpty() || aFaire.isEmpty() || aEviter.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nouvelleConsigne = ConsigneTri(
                typeDeDechet = typeDechet,
                couleurPoubelle = couleurPoubelle,
                aFaire = aFaire,
                aEviter = aEviter
            )

            RetrofitClient.instance.createConsigne(nouvelleConsigne)
                .enqueue(object : Callback<ConsigneTri> {
                    override fun onResponse(call: Call<ConsigneTri>, response: Response<ConsigneTri>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AjouterConsigneActivity, "Consigne ajoutée avec succès", Toast.LENGTH_SHORT).show()
                            finish() // revient à la liste des consignes
                        } else {
                            Toast.makeText(this@AjouterConsigneActivity, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ConsigneTri>, t: Throwable) {
                        Toast.makeText(this@AjouterConsigneActivity, "Erreur réseau : ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })




        }
        btnAnnuler.setOnClickListener {
            val intent = Intent(this, ConsigneActivity::class.java)
            startActivity(intent)
            finish() // Facultatif : pour fermer Activité2 si tu ne veux pas revenir en arrière avec le bouton retour
        }
    }
}
