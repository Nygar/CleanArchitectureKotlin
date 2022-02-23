package cleancode.database.api

import cleancode.entity.UserLoggedEntity
import io.reactivex.rxjava3.core.Observable


/**
 * An interface representing a user logged Cache.
 */
interface UserLoggedCache {

    /**
     * Checks if an element (UserLogged) exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(): Boolean

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

    /**
     * Gets an [Observable] which will emit a [UserLoggedEntity].
     *
     */
    fun get(): Observable<UserLoggedEntity>

    /**
     * Puts and element into the cache.
     *
     * @param userLoggedEntity Element to insert in the cache.
     */
    fun put(userLoggedEntity: UserLoggedEntity)

    /**
     * Evict all elements of the cache.
     */
    fun evictAll()
}
