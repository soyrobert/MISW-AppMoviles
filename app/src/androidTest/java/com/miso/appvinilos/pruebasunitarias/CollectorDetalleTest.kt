package com.miso.appvinilos.pruebasunitarias

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.presentacion.ui.views.collectordetail.CollectorBasicDetail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CollectorDetalleTest {

    val commentsTest = listOf(
        Comment(id = 1, description = "Great collection!"),
        Comment(id = 2, description = "Impressive variety of albums.")
    )

    val albumsTest = listOf(
        Album(id = 1, name = "Best of Ipsum", cover = "https://example.com/covers/ipsum.jpg", releaseDate = "2019-04-20", description = "The best hits of Ipsum.", genre = "Rock", recordLabel = "Universal"),
        Album(id = 2, name = "Smooth Jazz", cover = "https://example.com/covers/jazz.jpg", releaseDate = "2020-10-05", description = "Smooth jazz from the best.", genre = "Jazz", recordLabel = "Jazz Records")
    )

    val artistsTest = listOf(
        Artist(
            id = 1,
            name = "Lorem Ipsum",
            image = "https://example.com/images/lorem.jpg",
            description = "A popular figure in contemporary pop music.",
            birthDate = "1980-01-15",
            albums = albumsTest
        ),
        Artist(
            id = 2,
            name = "Ipsum",
            image = "https://example.com/images/ipsum.jpg",
            description = "Known for energetic rock performances.",
            birthDate = "1975-05-30",
            albums = albumsTest
        )
    )

    val collectorTest= Collector(
        id = 1,
        name = "Noemi Murillo",
        telephone = "123456789",
        email = "noemi@example.com",
        comments = commentsTest,
        favoritePerformers = artistsTest,
        collectorAlbums = albumsTest)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){

        composeTestRule.setContent {
            val navigationController = rememberNavController()
            CollectorBasicDetail(collectorTest,albumsTest,navigationController)


        }
    }

    @Test
    fun pantalla_coleccionista_detalle_muestra_correctamente(){
        composeTestRule.onNodeWithText(collectorTest.name).assertIsDisplayed()
        composeTestRule.onNodeWithText("Coleccionista").assertIsDisplayed()
        composeTestRule.onNodeWithText(albumsTest[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumsTest[1].name).assertIsDisplayed()
    }

    }