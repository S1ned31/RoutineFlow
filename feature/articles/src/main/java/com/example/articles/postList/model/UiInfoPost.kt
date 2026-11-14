package com.example.articles.postList.model

import com.busol.habittracker.core.domain.info.model.DomainInfoPost

data class UiInfoPost(
    val id: Long,
    val image: String,
    val title: String,
    val text: String,
    val readingTime: String,
)

fun DomainInfoPost.toUi(): UiInfoPost {
    return UiInfoPost(
        id = id,
        image = image,
        title = title,
        text = text,
        readingTime = readingTime,
    )
}