@file:Suppress("unused")

package com.miso.appvinilos.presentacion.viewmodels
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.repositories.CollectorRepository
import kotlinx.coroutines.launch

@Suppress("unused")
class CollectorViewModel(application: Application) :  AndroidViewModel(application) {
    private val collectorRepository = CollectorRepository(application)
    private val _collectors = MutableLiveData<List<Collector>>()
    private val _collectorAlbums = MutableLiveData<List<Album>>()
    val collectors: LiveData<List<Collector>>
        get() = _collectors

    private val _collector = MutableLiveData<Collector>()

    val collectorAlbums: LiveData<List<Album>>
        get() = _collectorAlbums

    fun fetchCollectors(collectorsTest:List<Collector> = emptyList()){
        viewModelScope.launch {
            try {
                if(collectorsTest.isEmpty()){
                    val collectors = collectorRepository.getCollectors()
                    Log.d("CollectorViewModel", "Fetched collectors: ${collectors.joinToString { it.name }}")
                    _collectors.value = collectors}
                else{
                    Log.d("CollectorViewModel", "Fetched test collectors: ${collectorsTest.joinToString { it.name }}")
                    _collectors.value = collectorsTest
                }

            } catch (e: Exception) {
                // Handle error
                Log.e("fetchCollectorsError", "Error fetching collector details", e)
                e.printStackTrace()
            }
        }
    }

    fun fetchCollectorAlbums(collectorId: Int) {
        viewModelScope.launch {
            try {
                val foundCollectorAlbums = collectorRepository.getCollectorAlbums(collectorId)
                Log.d("foundCollectorAlbums", "foundCollectorAlbums: $foundCollectorAlbums")
                _collectorAlbums.value = foundCollectorAlbums
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }


}