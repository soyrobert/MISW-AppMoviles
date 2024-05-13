package com.miso.appvinilos.data.network
import com.andretietz.retrofit.ResponseCache
import com.miso.appvinilos.data.model.Album
import android.content.Context
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface NetworkServiceAdapterAlbums {
    @GET("albums")
    @ResponseCache(CachingConfig.ALBUMS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getAlbums(): List<Album>

    @GET("albums/{albumId}")
    @ResponseCache(CachingConfig.ALBUMS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getAlbum(@Path("albumId") albumId: Int): Album
}

class AlbumsApi(context: Context) {

    //private val retrofit = RetrofitFactory.createRetrofitWithCache(MainActivity.getContext())
    private val retrofit = RetrofitFactory.createRetrofitWithCache(context)

    val albumsService: NetworkServiceAdapterAlbums by lazy {
        retrofit.create(NetworkServiceAdapterAlbums::class.java)
    }
}
