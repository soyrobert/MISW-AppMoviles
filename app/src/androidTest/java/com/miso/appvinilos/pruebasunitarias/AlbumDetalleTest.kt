package com.miso.appvinilos.pruebasunitarias

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.ui.views.albumdetail.AlbumBasicDetail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetalleAlbumTest {
    private val albumTestNormal= Album(id = 1, name="Album1", cover="cover1", releaseDate="2021-01-01", description="description1", genre="genre1", recordLabel="recordLabel1", comments= emptyList())

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){



        composeTestRule.setContent {

            val navigationController = rememberNavController()

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