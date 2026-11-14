package com.example.articles.di

import com.example.articles.postInfo.PostInfoViewModel
import com.example.articles.postList.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articlesModule = module {
    viewModel {
        PostListViewModel(
            getPostListUseCase = get(),
            router = get()
        )
    }

    viewModel {
        PostInfoViewModel(
            getPostByIdUseCase = get(),
            router = get()
        )
    }
}
