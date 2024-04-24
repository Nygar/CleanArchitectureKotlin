package cleancode.repository.source.disk

import cleancode.database.api.MessageCache
import cleancode.repository.source.MessageDataStore
import com.nygar.entity.MessageEntity

/**
 * Construct a [MessageDataStore] based file system data store.
 *
 * @param messageCache A [MessageCache] to cache data retrieved from the api.
 */
class DiskMessageDataStore(private val messageCache: MessageCache) : MessageDataStore {
    override suspend fun messageEntityList(): Result<List<MessageEntity>> {
        return messageCache.getList()
    }

    override suspend fun messageEntityDetails(messageId: Int): Result<MessageEntity> {
        return messageCache.get(messageId)
    }
}
