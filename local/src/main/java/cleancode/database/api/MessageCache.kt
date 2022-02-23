package cleancode.database.api

import cleancode.entity.MessageEntity
import io.reactivex.rxjava3.core.Observable


/**
 * An interface representing a message Cache.
 */
interface MessageCache {
    /**
     * Gets an [Observable] which will emit a [MessageEntity].
     *
     * @param messageId The message id to retrieve data.
     */
    operator fun get(messageId: Int): Observable<MessageEntity>

    /**
     * Puts and element into the cache.
     *
     * @param messageEntity Element to insert in the cache.
     */
    fun put(messageEntity: MessageEntity)

    /**
     * Checks if an element (Message) exists in the cache.
     *
     * @param messageId The id message to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(messageId: Int): Boolean

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(messageId: Int): Boolean

    /**
     * Evict all elements of the cache.
     */
    fun evictAll(messageId: Int)
}
