package cleancode.database.api

import com.nygar.entity.UserLoggedEntity

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
     * Gets an [Result] which will emit a [UserLoggedEntity].
     *
     */
    suspend fun get(): Result<UserLoggedEntity>

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
