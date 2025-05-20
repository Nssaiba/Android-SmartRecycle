package com.smartrecycle

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val resultText = findViewById<TextView>(R.id.resultText)
        val conseilText = findViewById<TextView>(R.id.conseilText)
        val couleurPBL = findViewById<TextView>(R.id.couleurPbl)

        val imagePath = intent.getStringExtra("image_path")

        imagePath?.let { path ->
            val bitmap = BitmapFactory.decodeFile(path)
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)

                val classifier = WasteClassifier(this)
                val (label, confidence) = classifier.classify(bitmap)

                resultText.text = "Déchet : $label\nConfiance : ${(confidence * 100).toInt()}%"

                // Appel Retrofit correctement configuré
                val call = RetrofitClient.instance.getConsignesByType(label)
                call.enqueue(object : Callback<List<ConsigneTri>> {
                    override fun onResponse(call: Call<List<ConsigneTri>>, response: Response<List<ConsigneTri>>) {
                        if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                            val consigne = response.body()!![0]
                            couleurPBL.text="""Couleur Poubelle :  ${consigne.couleurPoubelle}"""
                            conseilText.text = """
   ✅ À faire : 
   ${consigne.aFaire}
   ❌ À éviter :
   ${consigne.aEviter}
                            """.trimIndent()
                        } else {
                            conseilText.text = "Aucune consigne trouvée pour '$label'."
                        }
                    }

                    override fun onFailure(call: Call<List<ConsigneTri>>, t: Throwable) {
                        conseilText.text = "Erreur réseau : ${t.message}"
                    }
                })

            } else {
                resultText.text = "Erreur lors du chargement de l'image."
            }
        } ?: run {
            resultText.text = "Aucune image reçue."
        }
    }
}
