package com.miso.appvinilos.data.model

data class Artist (
    val id: Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    val albums: List<Album>
)