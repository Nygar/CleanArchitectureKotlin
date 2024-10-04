package cleancode.repository.source

import cleancode.entity.MessageEntity

/**
 * Interface that represents a data store from where data is retrieved.
 */
interface MessageDataStore {
    /**
     * Get an [Result] which will emit a List of [MessageEntity].
     */
    suspend fun messageEntityList(): Result<List<MessageEntity>>

    /**
     * Get an [Result] which will emit a [MessageEntity] by its id.
     *
     * @param messageId The id to retrieve message data.
     */
    suspend fun messageEntityDetails(messageId: Int): Result<MessageEntity>
}
