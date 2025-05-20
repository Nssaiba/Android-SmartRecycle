package com.smartrecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsigneActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ConsigneAdapter
    private val consignes = mutableListOf<ConsigneTri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consigne)

        recyclerView = findViewById(R.id.recyclerViewConsignes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ConsigneAdapter(consignes, this::onEdit, this::onDelete)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btnAjouter).setOnClickListener {
            startActivity(Intent(this, AjouterConsigneActivity::class.java))
        }

        chargerConsignes()
    }

    private fun chargerConsignes() {
        RetrofitClient.instance.getAllConsignes().enqueue(object : Callback<List<ConsigneTri>> {
            override fun onResponse(call: Call<List<ConsigneTri>>, response: Response<List<ConsigneTri>>) {
                if (response.isSuccessful) {
                    consignes.clear()
                    response.body()?.let { consignes.addAll(it) }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<ConsigneTri>>, t: Throwable) {
                Toast.makeText(this@ConsigneActivity, "Erreur chargement", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onEdit(consigne: ConsigneTri) {
        val intent = Intent(this, ModifierConsigneActivity::class.java)
        intent.putExtra("consigne_id", consigne.id)
        startActivity(intent)
    }

    private fun onDelete(consigne: ConsigneTri) {
        RetrofitClient.instance.deleteConsigne(consigne.id!!).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                chargerConsignes()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ConsigneActivity, "Erreur suppression", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        chargerConsignes()
    }

}
