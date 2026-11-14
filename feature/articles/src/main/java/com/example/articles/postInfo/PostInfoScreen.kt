package com.example.articles.postInfo

import ai.breezelabs.breezetracking.presentation.info.postInfo.PostInfoState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.busol.habittracker.core.ui.components.BaseScreen
import com.busol.habittracker.core.ui.theme.AppTheme

private const val GET_POST = "get_post"

@Composable
fun PostInfoScreen(id: Long) {
    BaseScreen<PostInfoViewModel> { viewModel ->
        val state by viewModel.state.collectAsState()

        LaunchedEffect(GET_POST) {
            viewModel.initPost(id)
        }

        PostInfoContent(state = state, pop = viewModel::pop)
    }
}


@Composable
fun PostInfoContent(
    state: PostInfoState,
    pop: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ImageTopBar(
            image = state.image,
            pop = pop
        )
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
                .weight(1f)
        ) {
            item {
                Text(
                    text = state.title,
                    style = AppTheme.fonts.h2,
                    color = AppTheme.colors.contentColor,
                    modifier = Modifier.padding(8.dp)
                )
            }

            item {
                Text(
                    text = state.text,
                    style = AppTheme.fonts.textFieldTitle,
                    color = AppTheme.colors.contentColor,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}