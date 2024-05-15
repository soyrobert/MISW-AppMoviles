@file:Suppress("unused")

package com.miso.appvinilos.data.repositories

import android.content.Context
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.network.CollectorsApi

@Suppress("unused")
class CollectorRepository(context: Context) {
    private val collectorsApi=CollectorsApi(context)
    private val collectorService = collectorsApi.collectorsService

    suspend fun getCollectors(): List<Collector> {
        return collectorService.getCollectors()
    }

    suspend fun getCollector(id: Int): Collector {
        return collectorService.getCollector(id)
    }

    suspend fun getCollectorAlbums(id: Int): List<Album> {
        return collectorService.getCollectorAlbums(id)
    }
}