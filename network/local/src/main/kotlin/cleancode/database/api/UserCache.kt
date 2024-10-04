package cleancode.database.api

import cleancode.entity.UserEntity

/**
 * An interface representing a user Cache.
 */
interface UserCache {
    /**
     * Gets an [Result] which will emit a [UserEntity].
     *
     * @param userId The user id to retrieve data.
     */
    suspend fun get(userId: Int): Result<UserEntity>

    /**
     * Puts and element into the cache.
     *
     * @param userEntity Element to insert in the cache.
     */
    fun put(userEntity: UserEntity)

    /**
     * Gets an [Result] which will emit a [UserEntity].
     *
     */
    suspend fun getList(): Result<List<UserEntity>>

    /**
     * Puts and element into the cache.
     *
     * @param listEntity Element to insert in the cache.
     */
    fun putList(listEntity: List<UserEntity>)

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param userId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(userId: Int): Boolean

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(userId: Int): Boolean

    /**
     * Evict all elements of the cache.
     */
    fun evictAll(userId: Int)
}
