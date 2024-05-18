package com.miso.appvinilos.presentacion.ui.views.albumlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.appvinilos.data.model.Album
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun AlbumItem(album: Album) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(176.dp)
            .clipToBounds()
            .semantics(mergeDescendants = true){}
    ) {
        GlideImage(
            imageModel = { album.cover },

            modifier = Modifier
                .height(185.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .semantics {
                    contentDescription = "Imágen para el album " + album.name
                }
        )
        Text(
            text = album.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.semantics {
                contentDescription = "Nombre del album"
            }
        )
        Text(
            text = album.genre,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.semantics {
                contentDescription = "Genero del album"
            }
        )
    }
}