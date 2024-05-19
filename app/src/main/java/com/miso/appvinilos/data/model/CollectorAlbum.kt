package com.miso.appvinilos.data.model

data class CollectorAlbum (
    val id: Int,
    val price: Double,
    val status: String,
    val album: Album,
    val collector:Collector
)