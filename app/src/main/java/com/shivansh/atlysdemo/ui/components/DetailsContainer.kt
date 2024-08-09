package com.shivansh.atlysdemo.ui.components

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun DetailsContainer(
    modifier: Modifier = Modifier,
    poster: @Composable (sizeModifier: Modifier) -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit
) {
    val orientation = LocalConfiguration.current.orientation

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        if (orientation == ORIENTATION_PORTRAIT) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    poster(Modifier.fillMaxWidth(0.7f))
                    Spacer(modifier = Modifier.size(16.dp))
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        title()
                        Spacer(modifier = Modifier.size(16.dp))
                        description()
                    }
                }
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    poster(Modifier.fillMaxHeight())
                    Column(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 32.dp)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        title()
                        Spacer(modifier = Modifier.size(16.dp))
                        description()
                    }
                }
            )
        }
    }
}