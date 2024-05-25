package com.miso.appvinilos.data.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.network.CollectorsApi

class CollectorRepository(context: Context) {
    @RequiresApi(Build.VERSION_CODES.M)
    private val collectorsApi=CollectorsApi(context)
    @RequiresApi(Build.VERSION_CODES.M)
    private val collectorService = collectorsApi.collectorsService

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getCollectors(): List<Collector> {
        return collectorService.getCollectors()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getCollector(id: Int): Collector {
        return collectorService.getCollector(id)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getCollectorAlbums(id: Int): List<Album> {
        return collectorService.getCollectorAlbums(id).map { it.album }
    }
}