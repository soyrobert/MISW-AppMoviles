package com.miso.appvinilos.accesibiliad

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasStateDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.ui.views.albumdetail.AlbumBasicDetail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumDetailAccessibilityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            val navigationController = rememberNavController()
            AlbumBasicDetail(oneAlbum(), navigationController)
        }
    }

    @Test
    fun test_accesibilidad_topbar() {
        composeTestRule.onNodeWithTag("backButton")
            .assertExists()
            .assertIsDisplayed()
            .assertHasClickAction()
            .assert(hasStateDescription("Este boton permite ir al home cuando es cliqueado"))
            .assert(hasContentDescription("Boton para volver al listado de albums"))
    }

    @Test
    fun test_accesibilidad_foto_del_album() {
        composeTestRule.onNodeWithContentDescription("Album photo")
            .assertExists()
            .assertIsDisplayed()
    }

    private fun oneAlbum() : Album {
        return Album(
            id = 1,
            name="Album1",
            cover="cover1",
            releaseDate="2021-01-01",
            description="description1",
            genre="genre1",
            recordLabel="recordLabel1"
        )
    }

}