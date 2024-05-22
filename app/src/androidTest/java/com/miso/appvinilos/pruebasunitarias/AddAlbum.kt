package com.miso.appvinilos.pruebasunitarias

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.CreateAlbumScreen
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AddAlbum {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setUp() {
        viewModel = AlbumViewModel(composeTestRule.activity.application)

        composeTestRule.setContent {
            val navController = rememberNavController()
            CreateAlbumScreen(navController = navController)
        }
    }

//    @Test
//    fun testAddAlbumSuccessfully() {
//        composeTestRule.onNodeWithText("Nombre").performTextInput("Test Album")
//        composeTestRule.onNodeWithText("URL").performTextInput("http://example.com/cover.jpg")
//        composeTestRule.onNodeWithText("Compañía Discografica").performTextInput("Sony Music")
//        composeTestRule.onNodeWithText("Release Date").performTextInput("2024-05-19")
//        composeTestRule.onNodeWithText("Descripción").performTextInput("This is a test album.")
//        composeTestRule.onNodeWithText("Genero").performTextInput("Rock")
//
//        // Click the "Create Album" button
//        composeTestRule.onNodeWithText("Create Album").performClick()
//        composeTestRule.onNodeWithText("Test Album").assertIsDisplayed()
//    }
    @Test
    fun testAddAlbumSuccessfully() {
        composeTestRule.onNodeWithText("Nombre").performTextInput("Test Album")
        composeTestRule.onNodeWithText("URL").performTextInput("http://example.com/cover.jpg")
        composeTestRule.onNodeWithText("Descripción").performTextInput("This is a test album.")
        composeTestRule.onNodeWithText("Create Album").performClick()
        composeTestRule.onNodeWithText("Test Album").assertIsDisplayed()
    }

    @Test
    fun add_empty_name() {
        composeTestRule.onNodeWithText("URL").performTextInput("http://example.com/cover.jpg")
        composeTestRule.onNodeWithText("Descripción").performTextInput("This is a test album.")
        composeTestRule.onNodeWithTag("SubmitAlbumButton").assertIsNotEnabled()
    }
}