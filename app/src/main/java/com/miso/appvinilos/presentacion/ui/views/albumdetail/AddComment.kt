package com.miso.appvinilos.presentacion.ui.views.albumdetail
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.data.model.CollectorId
import com.miso.appvinilos.data.model.CommentRequest
import com.miso.appvinilos.presentacion.ui.views.utils.Header
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AddCommentScreen(albumId: Int, navigationController: NavHostController) {
    val viewModel: AlbumViewModel = viewModel()
    var commentContent by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }
    val postCommentResponse by viewModel.postCommentResponse.observeAsState()

    Column(modifier = Modifier.padding(10.dp)) {
        Header(text="Escribir comentario", navigationController = navigationController)

        Column(modifier = Modifier.padding(28.dp, 0.dp, 28.dp, 8.dp)) {

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Calificación",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        (1..5).forEach { index ->
                            IconButton(
                                onClick = { rating = index },
                                modifier = Modifier
                                    .testTag("RatingButton$index")
                                    .padding(0.dp)
                                    .size(30.dp)
                            ) {
                                Icon(
                                    imageVector = if (index <= rating) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    tint = if (index <= rating) Color.Red else Color.Gray,
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .size(28.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                BasicTextField(
                    value = commentContent,
                    onValueChange = {
                        if (it.length <= 200) commentContent = it
                    },
                    modifier = Modifier
                        .testTag("CommentInput")
                        .fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(8.dp)
                        ) {
                            if (commentContent.isEmpty()) {
                                Text(text = "Escribe tu comentario aquí...", color = Color.DarkGray)
                            }
                            innerTextField()
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${commentContent.length} / 200",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp, bottom = 8.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (commentContent.isNotBlank() && rating > 0) {
                        val newCommentRequest = CommentRequest(
                            description = commentContent,
                            rating = rating,
                            collector = CollectorId(id = 1)
                        )
                        viewModel.postComment(albumId, newCommentRequest)
                    }
                },
                enabled = commentContent.isNotBlank() && rating > 0,
                colors = ButtonDefaults.buttonColors(
                    if (commentContent.isNotBlank() && rating > 0) MaterialTheme.colorScheme.primary else Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("SubmitCommentButton")
            ) {
                Text("Comentar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            postCommentResponse?.let { response ->
                if (response.isSuccessful) {
                    Text("Comment posted successfully!")
                    LaunchedEffect(Unit) {
                        navigationController.popBackStack()
                    }
                } else {
                    Text("Failed to post comment: ${response.message()}")
                }
            }
        }
    }
}
