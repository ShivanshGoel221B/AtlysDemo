package com.shivansh.atlysdemo.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.shivansh.atlysdemo.R
import com.shivansh.atlysdemo.domain.model.MovieModel
import com.shivansh.atlysdemo.ui.components.DetailsContainer

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    movie: MovieModel,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    DetailsContainer(
        modifier = modifier,
        poster = { sizeModifier ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                GlideImage(
                    modifier = sizeModifier
                        .aspectRatio(2 / 3f)
                        .background(color = Color.Transparent, shape = MaterialTheme.shapes.large)
                        .sharedElement(
                            state = rememberSharedContentState(key = "poster/${movie.id}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 800)
                            }
                        ),
                    model = movie.posterUrl,
                    loading = placeholder(R.drawable.ic_photo),
                    contentDescription = null
                )
            }
        },
        title = {
            Text(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "title/${movie.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 800)
                        }
                    ),
                text = movie.title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold)
            )
        },
        description = {
            Text(
                text = movie.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
    )
}