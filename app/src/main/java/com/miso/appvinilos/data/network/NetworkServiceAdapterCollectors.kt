package com.miso.appvinilos.data.network

import com.miso.appvinilos.data.model.Collector
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.*


private const val PORT = "3000"
private const val BASE_URL = "http://34.27.239.238:$PORT/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NetworkServiceAdapterCollectors {
    @GET("collectors")
    suspend fun getCollectors(): List<Collector>

    @GET("collectors/{id}")
    suspend fun getCollector(@Path("id") id: Int): Collector
}

object CollectorsApi {
    val collectorsService : NetworkServiceAdapterCollectors by lazy {
        retrofit.create(NetworkServiceAdapterCollectors::class.java)
    }
}
