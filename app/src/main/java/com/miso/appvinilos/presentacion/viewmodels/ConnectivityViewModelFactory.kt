package com.miso.appvinilos.presentacion.viewmodels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miso.appvinilos.ConnectivityViewModel

class ConnectivityViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ConnectivityViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
