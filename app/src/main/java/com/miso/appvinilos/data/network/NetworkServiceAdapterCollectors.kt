package com.miso.appvinilos.data.network

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.andretietz.retrofit.ResponseCache
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.model.CollectorAlbum
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface NetworkServiceAdapterCollectors {
    @GET("collectors")
    @ResponseCache(CachingConfig.COLLECTORS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getCollectors(): List<Collector>

    @GET("collectors/{id}")
    @ResponseCache(CachingConfig.COLLECTORS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getCollector(@Path("id") id: Int): Collector

    @GET("collectors/{id}/albums")
    @ResponseCache(CachingConfig.COLLECTORS_CACHE_TIME, unit = TimeUnit.HOURS)
    suspend fun getCollectorAlbums(@Path("id") id: Int):List<CollectorAlbum>


}

@RequiresApi(Build.VERSION_CODES.M)
class CollectorsApi(context: Context) {
    @RequiresApi(Build.VERSION_CODES.M)
    private val retrofit = RetrofitFactory.createRetrofitWithCache(context)

    val collectorsService : NetworkServiceAdapterCollectors by lazy {
        retrofit.create(NetworkServiceAdapterCollectors::class.java)
    }
}
