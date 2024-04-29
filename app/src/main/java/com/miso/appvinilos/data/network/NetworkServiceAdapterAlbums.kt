package com.miso.appvinilos.data.network
import com.miso.appvinilos.data.model.Album
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val PORT = "3000"
private const val BASE_URL = "http://34.27.239.238:$PORT/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NetworkServiceAdapterAlbums {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{albumId}")
    suspend fun getAlbum(@Path("albumId") albumId: Int): Album
}

object AlbumsApi {
    val albumsService : NetworkServiceAdapterAlbums by lazy {
        retrofit.create(NetworkServiceAdapterAlbums::class.java) }
}
