package com.miso.appvinilos.accesibiliad

import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.presentacion.ui.views.artistlist.ArtistListScreen
import com.miso.appvinilos.presentacion.viewmodels.ArtistViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistListAccessibilityTest {


    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            val viewModel: ArtistViewModel = viewModel()
            LaunchedEffect(key1 = true) {
                viewModel.fetchArtists(artistsTest = mockArtistsTest())
            }
            ArtistListScreen(rememberNavController(), mockArtistsTest())
        }
    }

    @Test
    fun test_artistList_es_desplegado() {
            composeTestRule.onNodeWithTag("ArtistList").assertIsDisplayed()
    }

    fun mockArtistsTest(): List<Artist> {
        val artistTest1 = Artist(
            id = 1,
            name = "Artist1",
            image = "image1",
            description = "description1",
            birthDate = "birthDate1",
            albums = emptyList()
        )
        val artistTest2 = Artist(
            id = 2,
            name = "Artist2",
            image = "image2",
            description = "description2",
            birthDate = "birthDate2",
            albums = emptyList()
        )
        val artistTest3 = Artist(
            id = 3,
            name = "Artist3",
            image = "image3",
            description = "description3",
            birthDate = "birthDate3",
            albums = emptyList()
        )
        val artistTest4 = Artist(
            id = 4,
            name = "Artist4",
            image = "image4",
            description = "description4",
            birthDate = "birthDate4",
            albums = emptyList()
        )
        val artistTest5 = Artist(
            id = 5,
            name = "Artist5",
            image = "image5",
            description = "description5",
            birthDate = "birthDate5",
            albums = emptyList()

        )
        val artistTest6 = Artist(
            id = 6,
            name = "Artist6",
            image = "image6",
            description = "description6",
            birthDate = "birthDate6",
            albums = emptyList()
        )
        val artistTest7 = Artist(
            id = 7,
            name = "Artist7",
            image = "image7",
            description = "description7",
            birthDate = "birthDate7",
            albums = emptyList()
        )
        val artistTest8 = Artist(
            id = 8,
            name = "Artist8",
            image = "image8",
            description = "description8",
            birthDate = "birthDate8",
            albums = emptyList()
        )

        return listOf(
            artistTest1,
            artistTest2,
            artistTest3,
            artistTest4,
            artistTest5,
            artistTest6,
            artistTest7,
            artistTest8
        )
    }

}