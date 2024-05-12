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
import com.miso.appvinilos.presentacion.ui.views.collectorlist.CollectorList
import com.miso.appvinilos.presentacion.viewmodels.CollectorViewModel
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.model.Comment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CollectorListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){
        val commentsTest = listOf(
            Comment(id = 1, content = "Great collection!"),
            Comment(id = 2, content = "Impressive variety of albums.")
        )

        val artistsTest = listOf(
            Artist(
                id = 1,
                name = "Lorem Ipsum",
                image = "https://example.com/images/lorem.jpg",
                description = "A popular figure in contemporary pop music.",
                birthDate = "1980-01-15"
            ),
            Artist(
                id = 2,
                name = "Ipsum",
                image = "https://example.com/images/ipsum.jpg",
                description = "Known for energetic rock performances.",
                birthDate = "1975-05-30"
            )
        )

// Asumimos que también tienes un modelo de 'Album' adecuadamente definido.
        val albumsTest = listOf(
            Album(id = 1, name = "Best of Ipsum", cover = "https://example.com/covers/ipsum.jpg", releaseDate = "2019-04-20", description = "The best hits of Ipsum.", genre = "Rock", recordLabel = "Universal"),
            Album(id = 2, name = "Smooth Jazz", cover = "https://example.com/covers/jazz.jpg", releaseDate = "2020-10-05", description = "Smooth jazz from the best.", genre = "Jazz", recordLabel = "Jazz Records")
        )

        val collectorsTest = listOf(
            Collector(
                id = 1,
                name = "Noemi Murillo",
                telephone = "123456789",
                email = "noemi@example.com",
                comments = commentsTest,
                favoritePerformers = artistsTest,
                collectorAlbums = albumsTest
            ),
            Collector(
                id = 2,
                name = "Francisco Bustamante",
                telephone = "987654321",
                email = "francisco@example.com",
                comments = commentsTest,
                favoritePerformers = artistsTest,
                collectorAlbums = albumsTest
            ),
            Collector(
                id = 2,
                name = "Francisco Javier",
                telephone = "987654321",
                email = "francisco@example.com",
                comments = commentsTest,
                favoritePerformers = artistsTest,
                collectorAlbums = albumsTest
            ),
            Collector(
                id = 2,
                name = "Javier Bustamante",
                telephone = "987654321",
                email = "francisco@example.com",
                comments = commentsTest,
                favoritePerformers = artistsTest,
                collectorAlbums = albumsTest
            ),
            Collector(
                id = 2,
                name = "Murillo Francisco Javier",
                telephone = "987654321",
                email = "francisco@example.com",
                comments = commentsTest,
                favoritePerformers = artistsTest,
                collectorAlbums = albumsTest
            ),
            Collector(
                id = 2,
                name = "Francisco Murillo Bustamante",
                telephone = "987654321",
                email = "francisco@example.com",
                comments = commentsTest,
                favoritePerformers = artistsTest,
                collectorAlbums = albumsTest
            )
        )

        composeTestRule.setContent {
            val viewModel: CollectorViewModel = viewModel()
            LaunchedEffect(key1 = true) {
                viewModel.fetchCollectors(collectorsTest = collectorsTest)
            }
            val navigationController = rememberNavController()
            CollectorList(viewModel, navigationController)
        }
    }

    @Test
    fun pantalla_collectors_muestra_correctamente_los_coleccionistas(){
        composeTestRule.onNodeWithText("Noemi Murillo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Francisco Bustamante").assertIsDisplayed()
    }

    @Test
    fun funciona_scroll_lista_coleccionistas(){
        composeTestRule.onNodeWithTag("CollectorList").performScrollToIndex(4)
        composeTestRule.onNodeWithText("Noemi Murillo").assertIsDisplayed()
    }
}
