package com.nygar.usecase

import cleancode.repository.DataRepository
import com.nygar.model.MessageModel
import com.nygar.model.mappers.MapperMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MessageUsecaseImpl
    @Inject
    constructor(
        private val repository: DataRepository,
    ) : MessageUsecase {
        override suspend fun getMessageListUsecase(category: Int): Flow<Result<List<MessageModel>>> =
            flow {
                emit(repository.messages().map { MapperMessage.transform(it) })
            }

        override suspend fun getMesageUsecase(messageId: Int): Flow<Result<MessageModel>> =
            flow {
                emit(repository.message(messageId).map { MapperMessage.transform(it) })
            }
    }
