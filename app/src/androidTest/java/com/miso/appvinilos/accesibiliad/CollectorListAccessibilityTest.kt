package com.miso.appvinilos.accesibiliad

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.presentacion.ui.views.collectorlist.CollectorList
import com.miso.appvinilos.presentacion.viewmodels.CollectorViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CollectorListAccessibilityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            val viewModel: CollectorViewModel = viewModel()
            CollectorList(viewModel = viewModel, navigationController = rememberNavController())
        }
    }

    @Test
    fun collectorList_accessibility() {
        composeTestRule.onNodeWithTag("CollectorList").assertIsDisplayed()
    }

}