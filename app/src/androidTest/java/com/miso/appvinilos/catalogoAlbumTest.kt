package com.miso.appvinilos

import org.junit.Test

import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miso.appvinilos.albums.model.Album
import com.miso.appvinilos.albums.ui.views.AlbumList
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel
import org.junit.Before
import org.junit.Rule


import org.junit.Assert.*
class catalogoAlbumTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){
        val albumTest1=Album(albumId = 1, name="Album1", cover="cover1", releaseDate="2021-01-01", description="description1", genre="genre1", recordLabel="recordLabel1")
        val albumTest2=Album(albumId = 2, name="Album2", cover="cover2", releaseDate="2021-01-02", description="description2", genre="genre2", recordLabel="recordLabel2")
        val albumsTest = listOf(albumTest1, albumTest2)
        composeTestRule.setContent {
            val viewModel: AlbumViewModel = viewModel()
            LaunchedEffect(key1 = true) {
                viewModel.fetchAlbums(albumsTest=albumsTest)
            }

            AlbumList(viewModel)


        }
    }

    @Test
    fun pantalla_albums_muestra_correctamente(){
        composeTestRule.onNodeWithText("Album1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album2").assertIsDisplayed()
    }
}