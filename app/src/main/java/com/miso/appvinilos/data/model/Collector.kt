package com.miso.appvinilos.data.model

data class Collector(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<Artist>,
    val collectorAlbums: List<Album>
)

data class Comment(
    val id: Int,
    val description: String,
    val rating: Int,
    val collectorId: Int
)