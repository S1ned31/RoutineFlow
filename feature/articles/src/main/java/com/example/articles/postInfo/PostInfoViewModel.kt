package com.example.articles.postInfo

import ai.breezelabs.breezetracking.presentation.info.postInfo.PostInfoState
import com.busol.habittracker.core.domain.info.GetPostByIdUseCase
import com.busol.habittracker.core.ui.components.BaseViewModel
import com.busol.habittracker.core.ui.components.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostInfoViewModel(
    private val getPostByIdUseCase: GetPostByIdUseCase,
    private val router: Navigator
) : BaseViewModel<PostInfoState>() {
    override fun createInitialState(): PostInfoState {
        return PostInfoState(
            image = "",
            title = "",
            readingTime = "",
            text = ""
        )
    }

    fun initPost(id: Long) {
        launch(Dispatchers.IO) {
            val item = getPostByIdUseCase.execute(id)
            withContext(Dispatchers.Main) {
                updateState {
                    it.copy(
                        image = item.image,
                        text = item.text,
                        readingTime = item.readingTime,
                        title = item.title
                    )
                }
            }
        }
    }

    fun pop() {
        router.pop()
    }
}