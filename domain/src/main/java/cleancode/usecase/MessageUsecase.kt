package cleancode.usecase

import cleancode.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessageUsecase {

    suspend fun getMessageListUsecase(category: Int): Flow<Result<List<MessageModel>>>

    suspend fun getMesageUsecase(messageId: Int): Flow<Result<MessageModel>>
}