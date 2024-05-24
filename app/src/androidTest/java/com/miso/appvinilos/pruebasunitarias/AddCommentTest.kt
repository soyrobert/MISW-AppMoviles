package com.miso.appvinilos.pruebasunitarias

import com.miso.appvinilos.presentacion.ui.views.albumdetail.AddCommentScreen
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddCommentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setUp() {
        viewModel = AlbumViewModel(composeTestRule.activity.application)

        composeTestRule.setContent {
            val navController = rememberNavController()
            AddCommentScreen(albumId = 1, navigationController = navController)
        }
    }

    @Test
    fun add_comment_successfully() {
        val commentText = "This is a test comment"
        val rating = 4
        composeTestRule.onNodeWithTag("CommentInput").performTextInput(commentText)
        (1..rating).forEach { index ->
            composeTestRule.onNodeWithTag("RatingButton$index").performClick()
        }
        composeTestRule.onNodeWithTag("SubmitCommentButton").performClick()
        composeTestRule.onNodeWithText("This is a test comment").assertIsDisplayed()
    }

    @Test
    fun add_comment_with_empty_text() {
        val rating = 4
        (1..rating).forEach { index ->
            composeTestRule.onNodeWithTag("RatingButton$index").performClick()
        }
        composeTestRule.onNodeWithTag("SubmitCommentButton").assertIsNotEnabled()
    }
}
