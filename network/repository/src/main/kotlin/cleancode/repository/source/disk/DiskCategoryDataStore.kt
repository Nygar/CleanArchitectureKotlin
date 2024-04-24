package cleancode.repository.source.disk

import cleancode.database.api.CategoryCache
import cleancode.entity.CategoryEntity
import cleancode.repository.source.CategoryDataStore

/**
 * Construct a [CategoryDataStore] based file system data store.
 *
 * @param categoryCache A [CategoryCache] to cache data retrieved from the api.
 */
class DiskCategoryDataStore(private val categoryCache: CategoryCache) : CategoryDataStore {
    override suspend fun categoryEntityList(): Result<List<CategoryEntity>> {
        // TODO: implement simple cache for storing/retrieving collections of messages.
        throw UnsupportedOperationException("Operation is not available!!!")
    }

    override suspend fun categoryEntityDetails(categoryId: Int): Result<CategoryEntity> {
        return categoryCache.get(categoryId)
    }
}
