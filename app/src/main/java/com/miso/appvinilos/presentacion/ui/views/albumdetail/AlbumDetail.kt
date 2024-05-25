package com.miso.appvinilos.presentacion.ui.views.albumdetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.presentacion.ui.views.artistdetail.DarkText
import com.miso.appvinilos.presentacion.ui.views.artistdetail.LightText
import com.miso.appvinilos.presentacion.ui.views.utils.Header
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel
import com.skydoves.landscapist.glide.GlideImage
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AlbumCompleteDetail(albumId: Int, navigationController: NavHostController,albumsTest:List<Album> = emptyList()) {
    val viewModel: AlbumViewModel = viewModel()

    val initialAlbum = Album(0, albumId.toString(), "cover", "releaseDate",
        "descr","genre","record lab", emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.fetchAlbum(albumId)
        viewModel.fetchComments(albumId)
    }


    val albumToShow by viewModel.album.observeAsState(initial = initialAlbum)
    val comments by viewModel.comments.observeAsState(initial = emptyList())

    if(albumsTest.isNotEmpty()){
        val albumTest = albumsTest[albumId-1]
        AlbumBasicDetail(albumTest, navigationController)
    }
    else{

        Column(modifier = Modifier.padding(16.dp) ) {
            AlbumBasicDetail(albumToShow, navigationController)
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 7.dp, end = 7.dp),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Comentarios",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF45483C),
                        letterSpacing = 0.4.sp,
                    )
                )
                Button(
                    onClick = { navigationController.navigate("AddComment/$albumId") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5352ED),
                        contentColor = Color.White
                    ),
                ) {
                    Text("+ Agregar")
                }
            }
            CommentList(comments)
        }
    }
}

@Composable
fun CommentList(comments: List<Comment>) {
    Column(modifier = Modifier.padding(6.dp)) {
        comments.forEach { comment ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = comment.description,
                    modifier = Modifier.semantics {
                        contentDescription = "Texto del comentario"
                    })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = comment.rating.toString(),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 4.dp)
                            .semantics {
                                contentDescription = "Rating del comentario"
                            }
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating Star",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                            .semantics {
                                contentDescription = "Icono de estrella del rating"
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun AlbumBasicDetail(album: Album, navigationController: NavHostController){
    Column(modifier = Modifier.semantics(mergeDescendants = true){}) {
        Header(text="Álbum",navigationController = navigationController)
        AlbumDetail(album)
        AlbumDescription(album)
    }
}


@Composable
fun AlbumPhotoScreen(cover: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .padding(1.dp)
    ) {

        GlideImage(
            imageModel = { cover },
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .semantics {
                    contentDescription = "Album photo"
                }
        )
    }
}


@Composable
fun AlbumBasicDescription(album: Album){
    GenreScreenField(album)
    DiscographyScreenField(album)
    PublicationDateScreenField(album)
}

@Composable
fun DiscographyScreenField(album: Album) {
    Column(modifier = Modifier.padding(5.dp)) {
        LightText(text = "Discografía")
        DarkText(text = album.recordLabel)
    }
}

@Composable
fun PublicationDateScreenField(album: Album) {
    Column(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        val formattedDate = try {
            formatDate(album.releaseDate)
        } catch (e: ParseException) {
            album.releaseDate
        }
        Column(modifier = Modifier.padding(5.dp)) {
            LightText(text = "Fecha publicación")
            DarkText(text = formattedDate)
        }
    }
}

@Composable
fun CustomParagraph(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight(400),
            color = Color(0xFF45483C),
            letterSpacing = 0.4.sp,
        )
    )
}

@Composable
fun GenreScreenField(album: Album){
    Column(modifier = Modifier.padding(5.dp)) {
        LightText(text = "Género")
        DarkText(text = album.genre)
    }
}


@Composable
fun AlbumDetail(album: Album) {
    Surface(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth()
                    .semantics(mergeDescendants = true){}
            ) {
                AlbumPhotoScreen(album.cover)
            }
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .semantics(mergeDescendants = true){}
            ) {
                AlbumBasicDescription(album)
            }

        }

    }
}

@Composable
fun AlbumDescription(album: Album) {
    Surface(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Column (modifier = Modifier.semantics(mergeDescendants = true){}){
            Row {
                DarkText(text = album.name)
            }
            CustomWhiteSpace()
            Row {
                CustomParagraph(text = album.description)
            }
        }
    }

}

@Composable
fun CustomWhiteSpace(){
    Surface (
        Modifier
            .height(20.dp)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 96.dp)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.semantics(mergeDescendants = true){}
        ) {}
    }
}

data class Album(
    val releaseDate: String
)
fun formatDate(dateString: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date: Date = inputFormatter.parse(dateString)
    return outputFormatter.format(date)
}

data class Comment(
    val description: String,
    val rating: Int
)


