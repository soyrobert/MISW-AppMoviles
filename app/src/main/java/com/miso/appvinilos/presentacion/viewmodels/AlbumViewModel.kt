package com.miso.appvinilos.presentacion.viewmodels
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.data.model.CommentRequest
import com.miso.appvinilos.data.repositories.AlbumRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {
    private val albumRepository = AlbumRepository(application.applicationContext)
    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    private val _postCommentResponse = MutableLiveData<Response<Comment>>()
    val postCommentResponse: LiveData<Response<Comment>> get() = _postCommentResponse


    @RequiresApi(Build.VERSION_CODES.M)
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


    @RequiresApi(Build.VERSION_CODES.M)
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

    private val _postAlbumResponse = MutableLiveData<Response<Album>>()
    val postAlbumResponse: LiveData<Response<Album>> get() = _postAlbumResponse
    @RequiresApi(Build.VERSION_CODES.M)
    fun createAlbum(album: Album) {
        viewModelScope.launch {
            try {
                val response: Response<Album> = albumRepository.postAlbum(album)
                _postAlbumResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("AlbumViewModel", "Error posting comment", e)
            }
        }
    }

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    @RequiresApi(Build.VERSION_CODES.M)
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

    @RequiresApi(Build.VERSION_CODES.M)
    fun postComment(albumId: Int, commentRequest: CommentRequest) {
        viewModelScope.launch {
            try {
                Log.d("AlbumViewModel", "Posting comment: $commentRequest to albumId: $albumId")
                val response = albumRepository.postComment(albumId, commentRequest)
                _postCommentResponse.value = response
                fetchComments(albumId) // Refresh comments after posting a new one
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("AlbumViewModel", "Error posting comment", e)
            }
        }
    }


}