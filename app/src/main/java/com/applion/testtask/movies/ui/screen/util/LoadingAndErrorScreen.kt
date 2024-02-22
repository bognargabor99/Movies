package com.applion.testtask.movies.ui.screen.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.applion.testtask.movies.R

@Composable
private fun ScreenContent(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        contentAlignment = Alignment.TopStart
    ) {
        content()
    }
}

@Composable
fun ErrorScreen() {
    ScreenContent {
        Text(text = stringResource(R.string.something_went_wrong))
    }
}

@Composable
fun LoadingScreen() {
    ScreenContent {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfiniteCircularProgressBar()
            Text(text = stringResource(R.string.loading))
        }
    }
}