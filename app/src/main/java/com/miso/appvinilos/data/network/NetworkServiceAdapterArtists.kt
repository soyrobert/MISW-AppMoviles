package com.miso.appvinilos.data.network
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.andretietz.retrofit.ResponseCache
import com.miso.appvinilos.data.model.Artist
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface NetworkServiceAdapterArtists {
    @GET("musicians")
    @ResponseCache(CachingConfig.ARTISTS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getArtists(): List<Artist>

    @GET("musicians/{id}")
    @ResponseCache(CachingConfig.ARTISTS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getArtist(@Path("id") id: Int): Artist
}

@RequiresApi(Build.VERSION_CODES.M)
class ArtistsApi(context : Context) {
    @RequiresApi(Build.VERSION_CODES.M)
    private val retrofit = RetrofitFactory.createRetrofitWithCache(context)

    val artistService : NetworkServiceAdapterArtists by lazy {
        retrofit.create(NetworkServiceAdapterArtists::class.java) }
}
