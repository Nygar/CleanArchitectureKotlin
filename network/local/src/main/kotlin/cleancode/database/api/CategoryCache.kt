package cleancode.database.api

import com.nygar.entity.CategoryEntity

/**
 * An interface representing a message Cache.
 */
interface CategoryCache {
    /**
     * Gets an [Result] which will emit a [CategoryEntity].
     *
     * @param categoryId The message id to retrieve data.
     */
    suspend fun get(categoryId: Int): Result<CategoryEntity>

    /**
     * Puts and element into the cache.
     *
     * @param categoryEntity Element to insert in the cache.
     */
    fun put(categoryEntity: CategoryEntity)

    /**
     * Checks if an element (Message) exists in the cache.
     *
     * @param categoryId The id message to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(categoryId: Int): Boolean

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(categoryId: Int): Boolean

    /**
     * Evict all elements of the cache.
     */
    fun evictAll(categoryId: Int)
}
