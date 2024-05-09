package com.miso.appvinilos.presentacion.viewmodels
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.repositories.ArtistRepository
import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import android.util.Log

class ArtistViewModel(application: Application) :  AndroidViewModel(application) {
    private val artistRepository = ArtistRepository()
    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>>
        get() = _artists

    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist>
        get() = _artist

    fun fetchArtists(artistsTest:List<Artist> = emptyList()){
        viewModelScope.launch {
            try {
                if(artistsTest.isEmpty()){
                    val artists = artistRepository.getArtists()
                    Log.d("ArtistViewModel", "Fetched artists: ${artists.joinToString { it.name }}")
                    _artists.value = artists}
                else{
                    Log.d("ArtistViewModel", "Fetched test artists: ${artistsTest.joinToString { it.name }}")
                    _artists.value = artistsTest
                }

            } catch (e: Exception) {
                // Handle error
                Log.e("fetchArtistsError", "Error fetching artists details", e)
                e.printStackTrace()
            }
        }
    }


    fun fetchArtist(id: Int) {
        viewModelScope.launch {
            try {
                val foundArtist = artistRepository.getArtist(id)
                Log.d("fetchArtist", "fetchArtist: $foundArtist")
                _artist.value = foundArtist
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

}