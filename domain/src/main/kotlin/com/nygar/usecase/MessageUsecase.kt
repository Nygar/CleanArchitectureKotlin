package com.nygar.usecase

import com.nygar.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessageUsecase {
    suspend fun getMessageListUsecase(category: Int): Flow<Result<List<MessageModel>>>

    suspend fun getMesageUsecase(messageId: Int): Flow<Result<MessageModel>>
}
