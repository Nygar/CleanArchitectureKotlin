package cleancode.repository.source.cloud

import cleancode.database.api.MessageCache
import cleancode.entity.MessageEntity
import cleancode.net.RestApi
import cleancode.repository.source.MessageDataStore

/**
 * Construct a [MessageDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [RestApi] implementation to use.
 * @param messageCache A [MessageCache] to cache data retrieved from the api.
 */
class CloudMessageDataStore(private val restApi: RestApi, private val messageCache: MessageCache) :
    MessageDataStore {
    override suspend fun messageEntityList(): Result<List<MessageEntity>> {
        return this.restApi.messageEntityList()
    }

    override suspend fun messageEntityDetails(messageId: Int): Result<MessageEntity> {
        return this.restApi.messageEntityById(messageId).onSuccess(messageCache::put)
    }
}
