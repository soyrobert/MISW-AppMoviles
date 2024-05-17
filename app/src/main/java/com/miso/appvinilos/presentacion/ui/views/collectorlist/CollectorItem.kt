package com.miso.appvinilos.presentacion.ui.views.collectorlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miso.appvinilos.data.model.Collector

@Composable
fun CollectorItem(collector: Collector) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp).semantics(mergeDescendants = true){}) {
            Text(
                text = collector.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), fontSize = 20.sp,
            )
            Text(
                text = "Artistas favoritos:",
                style = MaterialTheme.typography.titleMedium, fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = collector.favoritePerformers.joinToString { it.name },
                style = MaterialTheme.typography.bodySmall, fontSize = 11.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}