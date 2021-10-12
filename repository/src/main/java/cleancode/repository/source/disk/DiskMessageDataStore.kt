package cleancode.repository.source.disk

import cleancode.entity.MessageEntity
import cleancode.database.api.MessageCache
import cleancode.repository.source.MessageDataStore
import io.reactivex.Observable

/**
 * Construct a [MessageDataStore] based file system data store.
 *
 * @param messageCache A [MessageCache] to cache data retrieved from the api.
 */
class DiskMessageDataStore(private val messageCache: MessageCache) : MessageDataStore {

    override fun messageEntityList(): Observable<List<MessageEntity>> {
        //TODO: implement simple cache for storing/retrieving collections of messages.
        throw UnsupportedOperationException("Operation is not available!!!")
    }

    override fun messageEntityDetails(messageId: Int): Observable<MessageEntity> {
        return messageCache[messageId]
    }
}