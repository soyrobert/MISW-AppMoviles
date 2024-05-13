package com.miso.appvinilos.data.network

/**
 * Clase que proporciona configuraciones de caché para la aplicación.
 */
class CachingConfig {
    companion object {
        const val CACHE_SIZE = 50L * 1024L * 1024L
        const val ALBUMS_CACHE_TIME = 1
        const val ARTISTS_CACHE_TIME = 1
        const val COLLECTORS_CACHE_TIME = 1
    }
}