package com.busol.habittracker.core.domain.info

import com.busol.habittracker.core.data.repository.InfoRepository
import com.busol.habittracker.core.domain.info.model.DomainInfoPost
import com.busol.habittracker.core.domain.info.model.toDomain

class GetPostListUseCase(
    private val repository: InfoRepository,
) {
    suspend fun execute(): List<DomainInfoPost> {
        repository.getInfoFromRemote().forEach { repository.insert(it) }
        return repository.getInfoList().map { it.toDomain() }
    }
}