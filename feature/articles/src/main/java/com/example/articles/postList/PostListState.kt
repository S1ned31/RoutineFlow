package ai.breezelabs.breezetracking.presentation.info.postList

import com.example.articles.postList.model.UiInfoPost

data class PostListState(
    val postList: List<UiInfoPost>,
    val isLoading: Boolean = false
)
