package com.miso.appvinilos

import org.junit.Test

import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
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
        val albumTest3=Album(albumId = 3, name="Album3", cover="cover3", releaseDate="2021-01-03", description="description3", genre="genre3", recordLabel="recordLabel3")
        val albumTest4=Album(albumId = 4, name="Album4", cover="cover4", releaseDate="2021-01-04", description="description4", genre="genre4", recordLabel="recordLabel4")
        val albumTest5=Album(albumId = 5, name="Album5", cover="cover5", releaseDate="2021-01-05", description="description5", genre="genre5", recordLabel="recordLabel5")
        val albumTest6=Album(albumId = 6, name="Album6", cover="cover6", releaseDate="2021-01-06", description="description6", genre="genre6", recordLabel="recordLabel6")
        val albumTest7=Album(albumId = 7, name="Album7", cover="cover7", releaseDate="2021-01-07", description="description7", genre="genre7", recordLabel="recordLabel7")
        val albumTest8=Album(albumId = 8, name="Album8", cover="cover8", releaseDate="2021-01-08", description="description8", genre="genre8", recordLabel="recordLabel8")

        val albumsTest = listOf(albumTest1, albumTest2, albumTest3, albumTest4, albumTest5, albumTest6, albumTest7, albumTest8)
        composeTestRule.setContent {
            val viewModel: AlbumViewModel = viewModel()
            LaunchedEffect(key1 = true) {
                viewModel.fetchAlbums(albumsTest=albumsTest)
            }

            AlbumList(viewModel)


        }
    }

    @Test
    fun pantalla_albums_muestra_correctamente_6_albums(){
        composeTestRule.onNodeWithText("Album1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album4").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album5").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album6").assertIsDisplayed()
    }

    @Test
    fun funciona_scroll_catalogo_albums(){
        composeTestRule.onNodeWithTag("AlbumList").performScrollToIndex(7)
        composeTestRule.onNodeWithText("Album7").assertIsDisplayed()
    }
}