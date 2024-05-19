package com.giraffe.rickmortyapp.presentation.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.giraffe.rickmortyapp.navigateToProfile

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    mViewModel: HomeViewModel = hiltViewModel()
) {
    val state by mViewModel.state.collectAsState()
    HomeContent(animatedVisibilityScope, state, navController)
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: HomeState,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.2f))
    ) {
        if (state.isLoading) CircularProgressIndicator(color = Color.LightGray)
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.characters, key = { it.id ?: 0 }) {
                Card(
                    colors = CardDefaults.cardColors().copy(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier.clickable {
                            navController.navigateToProfile(it.id ?: "")
                        },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            it.image,
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .sharedElement(
                                    state = rememberSharedContentState(
                                        key = "image-${it.id}"
                                    ),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                ),
                            contentScale = ContentScale.Crop,
                            contentDescription = ""
                        )
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = it.name ?: "no name",
                            style = TextStyle(fontSize = 22.sp)
                        )
                    }
                }

            }
        }
    }
}