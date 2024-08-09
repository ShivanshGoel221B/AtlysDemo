package com.shivansh.atlysdemo.ui.components

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shivansh.atlysdemo.R

@Preview
@Composable
fun MovieLoader() {
    val transition = rememberInfiniteTransition(label = "movieLoader")
    val size by transition.animateFloat(
        initialValue = 40f, targetValue = 56f, animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = "movieLoader"
    )

    Box(modifier = Modifier.size(96.dp), contentAlignment = Alignment.Center) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_movie),
            contentDescription = null,
            modifier = Modifier.size(size.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        CircularProgressIndicator(
            modifier = Modifier.size(84.dp)
        )
    }
}