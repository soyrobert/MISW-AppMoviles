package com.miso.appvinilos.data.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private var retrofit: Retrofit? = null
     fun createRetrofitWithCache(context: Context): Retrofit {
        if (retrofit == null) {
            val httpClient = OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, CachingConfig.CACHE_SIZE))
                .build()

            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConfig.BASE_URL)
                .client(httpClient)
                .build()
        }
        return retrofit!!
    }

}