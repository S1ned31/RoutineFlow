package com.example.articles.postList

import ai.breezelabs.breezetracking.presentation.info.postList.PostListState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.articles.postInfo.PostList
import com.busol.habittracker.core.ui.components.BaseScreen
import com.busol.habittracker.core.ui.components.bottomBar.HabitBottomBar
import com.busol.habittracker.core.ui.components.bottomBar.HabitBottomBarItems
import com.busol.habittracker.core.ui.theme.AppTheme

@Composable
fun PostListScreen() {
    BaseScreen<PostListViewModel> { viewModel ->
        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.fetchPosts()
        }
        PostListContent(
            state = state,
            navigateToPost = viewModel::navigateToPost,
            navigate = viewModel::navigate
        )
    }
}

@Composable
fun PostListContent(
    state: PostListState,
    navigateToPost: (Long) -> Unit,
    navigate: (String) -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            HabitBottomBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = AppTheme.colors.containerColor,
                bottomBarItems = HabitBottomBarItems(
                    homeTitle = "Home",
                    feedTitle = "Feed",
                    profileTitle = "Profile",
                ).items,
                currentEntry = navController.currentBackStackEntry,
                navigate = navigate,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AppTheme.colors.background),
        ) {
            BreezeTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                title = "Articles",
            )

            PostList(
                list = state.postList,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .weight(1f),
                onClickPost = navigateToPost,
            )
        }
    }
}

@Composable
fun BreezeTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showDivider: Boolean = true,
    titleTextStyle: TextStyle = AppTheme.fonts.h3,
    navigationIcon: @Composable (BoxScope.() -> Unit) = {},
    leadingIcon: @Composable (BoxScope.() -> Unit) = {},
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .height(80.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            navigationIcon()
            Text(
                text = title,
                style = titleTextStyle,
                color = AppTheme.colors.contentColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center),
            )
            leadingIcon()
        }
        if (showDivider) {
            HorizontalDivider(
                color = AppTheme.colors.divider,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}