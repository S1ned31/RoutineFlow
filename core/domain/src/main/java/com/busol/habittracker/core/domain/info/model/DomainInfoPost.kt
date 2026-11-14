package com.busol.habittracker.core.domain.info.model

import com.busol.habittracker.core.data.db.entities.InfoEntity

data class DomainInfoPost(
    val id: Long,
    val image: String,
    val title: String,
    val text: String,
    val readingTime: String,
)

fun DomainInfoPost.toEntity(): InfoEntity {
    return InfoEntity(
        id = id,
        image = image,
        title = title,
        text = text,
        readingTime = readingTime
    )
}

fun InfoEntity.toDomain() : DomainInfoPost {
    return DomainInfoPost(
        id = id,
        image = image,
        title = title,
        text = text,
        readingTime = readingTime
    )
}