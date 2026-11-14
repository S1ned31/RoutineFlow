package com.busol.habittracker.core.domain.info

import com.busol.habittracker.core.data.repository.InfoRepository
import com.busol.habittracker.core.domain.info.model.DomainInfoPost
import com.busol.habittracker.core.domain.info.model.toDomain

class GetPostByIdUseCase(
    private val repository: InfoRepository,
) {
    fun execute(id: Long) : DomainInfoPost {
        return repository.getInfoPost(id).toDomain()
    }
}