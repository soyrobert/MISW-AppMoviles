package com.miso.appvinilos.albums.ui.views

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.appvinilos.albums.model.Album
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun AlbumItem(album: Album) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(176.dp)
            .clipToBounds()
    ) {
        GlideImage(
            imageModel = { album.cover },

            modifier = Modifier
                .height(185.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = album.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = album.genre,
            style = MaterialTheme.typography.bodySmall
        )
    }
}