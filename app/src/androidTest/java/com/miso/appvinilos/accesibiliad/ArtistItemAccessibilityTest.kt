package com.miso.appvinilos.accesibiliad

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.presentacion.ui.views.artistlist.ArtistItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistItemAccessibilityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            ArtistItem(artist = mockArtistTest())
        }
    }

    @Test
    fun test_elemento_artista_es_accesible_con_content_description() {
        val content_description = "Imagen del artista llamado Artist1"

        composeTestRule.onNodeWithContentDescription(content_description)
            .assertExists()
            .assert(hasContentDescription(content_description))
    }

    private fun mockArtistTest(): Artist {
        return Artist(
            id = 1,
            name = "Artist1",
            image = "image1",
            description = "description1",
            birthDate = "birthDate1",
            albums = emptyList()
        )
    }

}