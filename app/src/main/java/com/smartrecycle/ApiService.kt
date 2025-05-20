package com.smartrecycle

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/consignes")
    fun getAllConsignes(): Call<List<ConsigneTri>>

    @GET("api/consignes/{id}")
    fun getConsigneById(@Path("id") id: Long): Call<ConsigneTri>

    @POST("api/consignes")
    fun createConsigne(@Body consigne: ConsigneTri): Call<ConsigneTri>

    @PUT("api/consignes/{id}")
    fun updateConsigne(@Path("id") id: Long, @Body consigne: ConsigneTri): Call<ConsigneTri>

    @DELETE("api/consignes/{id}")
    fun deleteConsigne(@Path("id") id: Long): Call<Void>

    // 👇 AJOUTE CETTE MÉTHODE DIRECTEMENT ICI
    @GET("api/consignes/search")
    fun getConsignesByType(@Query("type") type: String): Call<List<ConsigneTri>>
}
