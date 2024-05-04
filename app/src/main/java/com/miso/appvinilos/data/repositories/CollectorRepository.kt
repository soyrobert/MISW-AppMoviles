package com.miso.appvinilos.data.repositories

import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.network.CollectorsApi

class CollectorRepository {
    private val collectorService = CollectorsApi.collectorsService

    suspend fun getCollectors(): List<Collector> {
        return collectorService.getCollectors()
    }

    suspend fun getCollector(id: Int): Collector {
        return collectorService.getCollector(id)
    }
}