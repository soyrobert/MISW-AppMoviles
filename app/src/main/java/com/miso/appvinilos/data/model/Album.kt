package com.miso.appvinilos.data.model

data class Album(
    val id: Int? = null,
    val name:String,
    val cover:String,
    val releaseDate: String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val comments: List<Comment>? = null
)

data class AlbumPostDTO(
    val id: Int? = null,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)

fun Album.toAlbumPostDTO(): AlbumPostDTO {
    return AlbumPostDTO(
        id = this.id,
        name = this.name,
        cover = this.cover,
        releaseDate = this.releaseDate,
        description = this.description,
        genre = this.genre,
        recordLabel = this.recordLabel
    )
}
