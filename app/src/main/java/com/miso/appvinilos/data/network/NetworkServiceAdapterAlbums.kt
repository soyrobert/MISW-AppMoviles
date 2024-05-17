package com.miso.appvinilos.data.network
import android.content.Context
import com.andretietz.retrofit.ResponseCache
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface NetworkServiceAdapterAlbums {
    @GET("albums")
    @ResponseCache(CachingConfig.ALBUMS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getAlbums(): List<Album>

    @GET("albums/{albumId}")
    @ResponseCache(CachingConfig.ALBUMS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getAlbum(@Path("albumId") albumId: Int): Album

    @POST("albums")
    @ResponseCache(CachingConfig.ALBUMS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun postAlbum(@Body album: Album): Call<Album>

    @GET("albums/{albumId}/comments")
    suspend fun getComments(@Path("albumId") albumId: Int): List<Comment>

    @POST("albums/{albumId}/comments")
    suspend fun postComment(@Path("albumId") albumId: Int, @Body comment: Comment): Call<Comment>
}

class AlbumsApi(context: Context) {

    //private val retrofit = RetrofitFactory.createRetrofitWithCache(MainActivity.getContext())
    private val retrofit = RetrofitFactory.createRetrofitWithCache(context)

    val albumsService: NetworkServiceAdapterAlbums by lazy {
        retrofit.create(NetworkServiceAdapterAlbums::class.java)
    }
}
