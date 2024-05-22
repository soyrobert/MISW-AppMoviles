package com.miso.appvinilos.accesibiliad
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.presentacion.ui.views.collectorlist.CollectorItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CollectorItemAccessibilityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            CollectorItem(collector = collector())
        }
    }

    @Test
    fun collectorItem_accessibility() {
        composeTestRule.onNodeWithContentDescription(collector().name).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Artistas favoritos").assertIsDisplayed()
    }

    private fun collector(): Collector {
        return Collector(
            id = 1,
            name = "John Doe",
            telephone = "123456789",
            email = "johndoe@example.com",
            comments = listOf(
                Comment(id = 1, description = "Great collector!"),
                Comment(id = 2, description = "Always prompt and professional.")
            ),
            favoritePerformers = listOf(
                Artist(
                    id = 1,
                    name = "Artist1",
                    image = "image1",
                    description = "description1",
                    birthDate = "birthDate1",
                    albums = emptyList()
                ),
                Artist(
                    id = 1,
                    name = "Artist2",
                    image = "image2",
                    description = "description2",
                    birthDate = "birthDate2",
                    albums = emptyList()
                )
            ),
            collectorAlbums = listOf(
                Album(
                    id = 1,
                    name = "Album1",
                    cover = "cover1",
                    releaseDate = "2021-01-01",
                    description = "description1",
                    genre = "genre1",
                    recordLabel = "recordLabel1"
                ),
                Album(
                    id = 1,
                    name = "Album2",
                    cover = "cover2",
                    releaseDate = "2021-01-01",
                    description = "description2",
                    genre = "genre2",
                    recordLabel = "recordLabel2"
                )
            )
        )
    }

}