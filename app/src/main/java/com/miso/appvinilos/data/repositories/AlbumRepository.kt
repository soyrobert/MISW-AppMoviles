package com.miso.appvinilos.data.repositories
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.network.AlbumsApi

class AlbumRepository {
    private val albumService = AlbumsApi.albumsService

    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return albumService.getAlbum(albumId)
    }
}