package com.miso.appvinilos.data.network

import com.andretietz.retrofit.ResponseCache
import com.miso.appvinilos.MainActivity
import com.miso.appvinilos.data.model.Collector
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
}

object CollectorsApi {
    private val retrofit = RetrofitFactory.createRetrofitWithCache(MainActivity.getContext())

    val collectorsService : NetworkServiceAdapterCollectors by lazy {
        retrofit.create(NetworkServiceAdapterCollectors::class.java)
    }
}
