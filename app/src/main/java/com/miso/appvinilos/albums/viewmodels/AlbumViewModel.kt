package com.miso.appvinilos.albums.viewmodels
import com.miso.appvinilos.albums.model.Album
import com.miso.appvinilos.albums.repositories.AlbumRepository
import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {
    private val albumRepository = AlbumRepository()
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val albums = albumRepository.getAlbums()
                _albums.value = albums
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

    fun fetchAlbum(albumId: Int) {
        // Launch a coroutine in the IO context
        viewModelScope.launch {
            try {
                val result = albumRepository.getAlbum(albumId)
                _album.value = result
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

}