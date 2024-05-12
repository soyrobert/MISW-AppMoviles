package com.miso.appvinilos.pruebasunitarias

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.presentacion.ui.views.artistdetail.ArtistBasicDetail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistDetalleTest {

    private val artistTest= Artist(
        id = 1,
        name = "Artist1",
        image = "image1",
        description = "description1",
        birthDate = "birthDate1"
    )

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){



        composeTestRule.setContent {

            val navigationController = rememberNavController()

            ArtistBasicDetail(artist=artistTest,navigationController=navigationController)


        }
    }

    @Test
    fun pantalla_artista_detalle_muestra_artista(){
        composeTestRule.onNodeWithText("Artista").assertIsDisplayed()
        composeTestRule.onNodeWithText(artistTest.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(artistTest.description).assertIsDisplayed()
    }

    }