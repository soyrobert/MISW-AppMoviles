package com.miso.appvinilos.presentacion.viewmodels
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.data.repositories.AlbumRepository
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {
    private val albumRepository = AlbumRepository(application.applicationContext)
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    fun fetchAlbums(albumsTest:List<Album> = emptyList()){
        viewModelScope.launch {
            try {
                if(albumsTest.isEmpty()){
                    val albums = albumRepository.getAlbums()
                    Log.d("AlbumViewModel", "Fetched albums: ${albums.joinToString { it.name }}")
                    _albums.value = albums}
                else{
                    Log.d("AlbumViewModel", "Fetched test albums: ${albumsTest.joinToString { it.name }}")
                    _albums.value = albumsTest
                }

            } catch (e: Exception) {
                // Handle error
                Log.e("fetchAlbumsError", "Error fetching album details", e)
                e.printStackTrace()
            }
        }
    }


    fun fetchAlbum(albumId: Int) {
        viewModelScope.launch {
            try {
                val foundAlbum = albumRepository.getAlbum(albumId)
                Log.d("fetchAlbum", "fetchAlbum: $foundAlbum")
                _album.value = foundAlbum
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

//    fun createAlbum(album: Album) {
//        viewModelScope.launch {
//            try {
//                albumRepository.postAlbum(album)
//                Log.d("create Album", "CreateAlbum: $album")
//            } catch (e: Exception) {
//                // Handle error
//                e.printStackTrace()
//            }
//        }
//    }
    fun createAlbum(album: Album): Boolean {
        var isError: Boolean = false
        viewModelScope.launch {
            try {
                albumRepository.postAlbum(album)
                Log.d("create Album", "CreateAlbum: $album")
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
                isError = true
            }
        }
        return isError
    }

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    fun fetchComments(albumId: Int) {
        viewModelScope.launch {
            try {
                val comments = albumRepository.getComments(albumId)
                _comments.value = comments
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postComment(albumId: Int, comment: Comment) {
        viewModelScope.launch {
            try {
                albumRepository.postComment(albumId, comment)
                fetchComments(albumId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}