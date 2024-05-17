package com.miso.appvinilos.presentacion.ui.views.albumdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel

@Composable
fun AddCommentScreen(albumId: Int, navigationController: NavHostController) {
    val viewModel: AlbumViewModel = viewModel()
    var commentContent by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }

    Column {
        TextField(
            value = commentContent,
            onValueChange = { commentContent = it },
            label = { Text("Comment") }
        )
        TextField(
            value = rating.toString(),
            onValueChange = { rating = it.toIntOrNull() ?: 0 },
            label = { Text("Rating") }
        )
        Button(onClick = {
            val newComment = Comment(description = commentContent, rating = rating, collectorId = 1)
            viewModel.postComment(albumId, newComment)
            navigationController.popBackStack()
        }) {
            Text("Submit")
        }
    }
}
