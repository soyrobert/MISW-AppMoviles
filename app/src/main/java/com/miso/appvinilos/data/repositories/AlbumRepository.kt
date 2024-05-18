package com.miso.appvinilos.data.repositories
import android.content.Context
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.data.model.toAlbumPostDTO
import com.miso.appvinilos.data.network.AlbumsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AlbumRepository(context: Context) {

    private val albumsApi=AlbumsApi(context)
    private val albumService = albumsApi.albumsService

    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return albumService.getAlbum(albumId)
    }

    suspend fun postAlbum(album: Album): Response<Album> {
        val albumPostDTO = album.toAlbumPostDTO()
        return withContext(Dispatchers.IO) {
            albumService.postAlbum(albumPostDTO)
        }
    }

    suspend fun getComments(albumId: Int): List<Comment> {
        return albumService.getComments(albumId)
    }

    suspend fun postComment(albumId: Int, comment: Comment) {
        albumService.postComment(albumId, comment)
    }
}