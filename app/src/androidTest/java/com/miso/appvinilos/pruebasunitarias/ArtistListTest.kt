package com.miso.appvinilos.pruebasunitarias

import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.presentacion.ui.views.artistlist.ArtistList
import com.miso.appvinilos.presentacion.viewmodels.ArtistViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){
        val artistTest1= Artist(id = 1, name = "Artist1", image = "image1",description = "description1", birthDate = "birthDate1")
        val artistTest2= Artist(id = 2, name = "Artist2", image = "image2",description = "description2", birthDate = "birthDate2")
        val artistTest3= Artist(id = 3, name = "Artist3", image = "image3",description = "description3", birthDate = "birthDate3")
        val artistTest4= Artist(id = 4, name = "Artist4", image = "image4",description = "description4", birthDate = "birthDate4")
        val artistTest5= Artist(id = 5, name = "Artist5", image = "image5",description = "description5", birthDate = "birthDate5")
        val artistTest6= Artist(id = 6, name = "Artist6", image = "image6",description = "description6", birthDate = "birthDate6")
        val artistTest7= Artist(id = 7, name = "Artist7", image = "image7",description = "description7", birthDate = "birthDate7")
        val artistTest8= Artist(id = 8, name = "Artist8", image = "image8",description = "description8", birthDate = "birthDate8")

        val artistsTest = listOf(artistTest1, artistTest2, artistTest3, artistTest4, artistTest5, artistTest6, artistTest7, artistTest8)
        composeTestRule.setContent {
            val viewModel: ArtistViewModel = viewModel()
            LaunchedEffect(key1 = true) {
                viewModel.fetchArtists(artistsTest=artistsTest)
            }

            val navigationController = rememberNavController()
            ArtistList(viewModel,navigationController)


        }
    }

    @Test
    fun pantalla_albums_muestra_correctamente_6_albums(){
        composeTestRule.onNodeWithText("Artist1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist4").assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist5").assertIsDisplayed()
    }

    @Test
    fun funciona_scroll_catalogo_albums_abajo(){
        composeTestRule.onNodeWithTag("ArtistList").performScrollToIndex(7)
        composeTestRule.onNodeWithText("Artist8").assertIsDisplayed()
    }

}