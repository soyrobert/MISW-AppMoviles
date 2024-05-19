package com.miso.appvinilos.accesibiliad

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.ui.views.albumlist.AlbumItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumItemAccesibilityTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            AlbumItem(album = mockOneAlbum())
        }
    }

    @Test
    fun test_un_album_contiene_content_description_para_imagen() {
        composeTestRule.onNodeWithContentDescription("Imágen para el album Album1")
            .assertIsDisplayed()
    }

    @Test
    fun test_un_album_contiene_content_description_para_nombre() {
        composeTestRule.onNodeWithContentDescription("Nombre del album")
            .assertIsDisplayed()
    }

    @Test
    fun test_un_album_contiene_content_description_para_genero() {
        composeTestRule.onNodeWithContentDescription("Genero del album")
            .assertIsDisplayed()
    }

    private fun mockOneAlbum(): Album {
        return Album(
            id = 1,
            name = "Album1",
            cover = "cover1",
            releaseDate = "2021-01-01",
            description = "description1",
            genre = "genre1",
            recordLabel = "recordLabel1"
        )
    }

}