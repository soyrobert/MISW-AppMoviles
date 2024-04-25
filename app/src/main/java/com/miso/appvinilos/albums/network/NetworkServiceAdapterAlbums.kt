package com.miso.appvinilos.albums.network
import com.miso.appvinilos.albums.model.Album
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private const val BASE_URL =
      "http://34.27.239.238:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NetworkServiceAdapterAlbums {
    @GET("albums")
    fun getAlbums(): Call<String>
}

object AlbumsApi {
    val albumsService : NetworkServiceAdapterAlbums by lazy {
        retrofit.create(NetworkServiceAdapterAlbums::class.java) }
}
