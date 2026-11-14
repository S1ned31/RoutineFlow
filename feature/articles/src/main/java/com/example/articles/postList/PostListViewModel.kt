package com.example.articles.postList

import ai.breezelabs.breezetracking.presentation.info.postList.PostListState
import com.example.articles.postList.model.toUi
import com.busol.habittracker.core.domain.info.GetPostListUseCase
import com.busol.habittracker.core.ui.components.BaseViewModel
import com.busol.habittracker.core.ui.components.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PostListViewModel(
    private val getPostListUseCase: GetPostListUseCase,
    private val router: Navigator,
) : BaseViewModel<PostListState>() {
    override fun createInitialState(): PostListState {
        return PostListState(
            postList = listOf()
        )

    }

    fun fetchPosts() {
        launch(Dispatchers.IO) {
            val posts = getPostListUseCase.execute().map { it.toUi() }
            withContext(Dispatchers.Main) {
                updateState { it.copy(postList = posts) }
            }
        }
    }

    fun navigateToPost(id: Long) {
        router.navigate("ARTICLE_ROUTE/$id")
    }

    fun navigate(screen: String) {
        router.navigateAndClearBackStack(screen)
    }
}