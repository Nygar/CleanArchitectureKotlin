package cleancode.repository.source

import cleancode.entity.MessageEntity
import io.reactivex.rxjava3.core.Observable


/**
 * Interface that represents a data store from where data is retrieved.
 */
interface MessageDataStore {
    /**
     * Get an [Observable] which will emit a List of [MessageEntity].
     */
    fun messageEntityList(): Observable<List<MessageEntity>>

    /**
     * Get an [Observable] which will emit a [MessageEntity] by its id.
     *
     * @param messageId The id to retrieve message data.
     */
    fun messageEntityDetails(messageId: Int): Observable<MessageEntity>
}
