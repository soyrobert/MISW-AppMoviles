package com.miso.appvinilos.data.model

data class CommentRequest(
    val description: String,
    val rating: Int,
    val collector: CollectorId
)

data class CollectorId(
    val id: Int
)