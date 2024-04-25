package com.miso.appvinilos.albums.repositories
import com.miso.appvinilos.albums.model.Album
import com.miso.appvinilos.albums.network.AlbumsApi
import retrofit2.Call
class AlbumRepository {
    private val albumService = AlbumsApi.albumsService

    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return albumService.getAlbum(albumId)
    }
}