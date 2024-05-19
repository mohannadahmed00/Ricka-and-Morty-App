package com.giraffe.rickmortyapp.presentation.profile

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProfileScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    id: String,
    mViewModel: ProfileViewModel = hiltViewModel()
) {
    val state by mViewModel.state.collectAsState()
    mViewModel.getDetailedCharacter(id)
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
        modifier = Modifier.background(Color.White)
    ) {
        if (state.isLoading) CircularProgressIndicator(color = Color.LightGray)
        if (state.detailedCharacterEntity != null) Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = state.detailedCharacterEntity.image.asImageBitmap(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .sharedElement(
                        rememberSharedContentState(key = "image-$id"),
                        animatedVisibilityScope,
                    )

                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = state.detailedCharacterEntity.name,
                style = TextStyle(fontSize = 22.sp)
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = "About",
                style = TextStyle(fontSize = 22.sp)
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = state.detailedCharacterEntity.about
            )
        }

    }
}

