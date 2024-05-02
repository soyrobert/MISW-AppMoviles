package com.miso.appvinilos.data.network
import com.miso.appvinilos.data.model.Artist
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val PORT = "3000"
private const val BASE_URL = "http://34.27.239.238:$PORT/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NetworkServiceAdapterArtists {
    @GET("musicians")
    suspend fun getArtists(): List<Artist>

    @GET("musicians/{id}")
    suspend fun getArtist(@Path("id") id: Int): Artist
}

object ArtistsApi {
    val artistService : NetworkServiceAdapterArtists by lazy {
        retrofit.create(NetworkServiceAdapterArtists::class.java) }
}
