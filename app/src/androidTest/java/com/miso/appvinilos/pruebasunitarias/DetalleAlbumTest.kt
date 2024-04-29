package com.miso.appvinilos.pruebasunitarias

import org.junit.Test

import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.albums.ui.views.AlbumBasicDetail
import com.miso.appvinilos.albums.ui.views.AlbumList
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel
import org.junit.Before
import org.junit.Rule


import org.junit.Assert.*
class detalleAlbumTest {
    val albumTestNormal= Album(id = 1, name="Album1", cover="cover1", releaseDate="2021-01-01", description="description1", genre="genre1", recordLabel="recordLabel1")

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){



        composeTestRule.setContent {

            var navigationController = rememberNavController()

            AlbumBasicDetail(albumTestNormal,navigationController)


        }
    }

    @Test
    fun pantalla_album_detalle_muestra_album(){
        composeTestRule.onNodeWithText(albumTestNormal.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTestNormal.description).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTestNormal.genre).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTestNormal.recordLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTestNormal.releaseDate).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTestNormal.id.toString()).assertIsNotDisplayed()
    }

    }