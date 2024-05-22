package com.miso.appvinilos.data.network
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object RetrofitFactory {
    private const val BASE_URL = NetworkConfig.BASE_URL
    private const val CACHE_SIZE = 50L * 1024L * 1024L // 50 MB
    private const val MAX_STALE = 60 * 60 * 24 * 7 // 1 week

    @Volatile
    private var retrofit: Retrofit? = null
     fun createRetrofitWithCache(context: Context): Retrofit {
         return retrofit ?: synchronized(this) {
             retrofit ?: buildRetrofit(context).also { retrofit = it }
         }
    }

    private fun buildRetrofit(context: Context): Retrofit {
        // Directorio y tamaño de caché
        val cacheDir = File(context.cacheDir, "http-cache")
        val cache = Cache(cacheDir, CACHE_SIZE)

        // Interceptor para manejar el caché
        val cacheInterceptor = Interceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=$MAX_STALE").build()
            chain.proceed(request)
        }



        // Cliente OkHttp
        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(cacheInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}