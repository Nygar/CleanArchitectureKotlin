package cleancode.repository.source.cloud

import cleancode.database.api.CategoryCache
import cleancode.entity.CategoryEntity
import cleancode.net.RestApi
import cleancode.repository.source.CategoryDataStore

/**
 * Construct a [CategoryDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [RestApi] implementation to use.
 * @param categoryCache A [CategoryCache] to cache data retrieved from the api.
 */
class CloudCategoryDataStore(private val restApi: RestApi, private val categoryCache: CategoryCache) :
    CategoryDataStore {
    override suspend fun categoryEntityList(): Result<List<CategoryEntity>> {
        return restApi.categoryEntityList()
    }

    override suspend fun categoryEntityDetails(categoryId: Int): Result<CategoryEntity> {
        return restApi.categoryEntityById(categoryId).onSuccess(categoryCache::put)
    }
}
