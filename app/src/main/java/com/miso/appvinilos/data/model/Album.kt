package com.miso.appvinilos.data.model

data class Album(
    val id: Int? = null,
    val name:String,
    val cover:String,
    val releaseDate: String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val comments: List<Comment>
)
