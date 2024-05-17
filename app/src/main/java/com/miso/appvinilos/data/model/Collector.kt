package com.miso.appvinilos.data.model

data class Collector(
    val id: Int=0,
    val name: String="",
    val telephone: String="",
    val email: String="",
    val comments: List<Comment> = emptyList(),
    val favoritePerformers: List<Artist> = emptyList(),
    val collectorAlbums: List<Album> = emptyList()
)

data class Comment(
    val id: Int? = null,
    val description: String,
    val rating: Int,
    val collectorId: Int
)