package com.giraffe.rickmortyapp.presentation.profile

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProfileScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    id: String,
    mViewModel: ProfileViewModel = hiltViewModel()
) {
    val state by mViewModel.state.collectAsState()
    mViewModel.getCharacterInfo(id)
    ProfileContent(id, animatedVisibilityScope, state)
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProfileContent(
    id: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: ProfileState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.2f))
    ) {
        if (state.isLoading) CircularProgressIndicator(color = Color.LightGray)
        if (state.characterInfoEntity != null) Column {
            AsyncImage(
                state.characterInfoEntity.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .sharedElement(
                        rememberSharedContentState(key = "image-$id"),
                        animatedVisibilityScope,
                    ),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Text(text = "name: ${state.characterInfoEntity.name}")
            Text(text = "gender: ${state.characterInfoEntity.gender}")
            if (!state.characterInfoEntity.type.isNullOrBlank()) {
                Text(text = "type: ${state.characterInfoEntity.type}")
            }
            Text(text = "status: ${state.characterInfoEntity.status}")
            Text(text = "location: ${state.characterInfoEntity.location}")
            Text(text = "species: ${state.characterInfoEntity.species}")
        }

    }
}