package com.shivansh.atlysdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shivansh.atlysdemo.domain.model.Routes
import com.shivansh.atlysdemo.ui.event.Event
import com.shivansh.atlysdemo.ui.screens.DetailsScreen
import com.shivansh.atlysdemo.ui.screens.MoviesScreen
import com.shivansh.atlysdemo.ui.theme.AtlysDemoTheme
import com.shivansh.atlysdemo.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LaunchedEffect(Unit) {
                viewModel.eventFlow.collect { event ->
                    when (event) {
                        is Event.NavigateTo -> navController.navigate(event.route)
                        Event.NavigateUp -> navController.navigateUp()
                    }
                }
            }
            val moviesUiState by viewModel.moviesUiState.collectAsState()
            val searchQuery by viewModel.searchQuery.collectAsState()

            AtlysDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SharedTransitionLayout {
                        NavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            navController = navController,
                            startDestination = Routes.HOME.name
                        ) {
                            composable(
                                route = Routes.HOME.name
                            ) {
                                MoviesScreen(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    moviesUiState = moviesUiState,
                                    searchQuery = searchQuery,
                                    animatedVisibilityScope = this,
                                    onUiEvent = viewModel::onUiEvent
                                )
                            }

                            val detailsRoute = buildString {
                                append(Routes.DETAILS.name)
                                append("/{movieId}")
                            }

                            composable(
                                route = detailsRoute,
                                arguments = listOf(
                                    navArgument(name = "movieId") {
                                        type = NavType.IntType
                                    }
                                )
                            ) { backStack ->
                                val movieId = backStack.arguments?.getInt("movieId")
                                val movie = moviesUiState.movies.firstOrNull { it.id == movieId }
                                movie?.let {
                                    DetailsScreen(
                                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                                        movie = it,
                                        animatedVisibilityScope = this
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}