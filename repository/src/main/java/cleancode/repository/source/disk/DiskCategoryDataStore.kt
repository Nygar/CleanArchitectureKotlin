package cleancode.repository.source.disk

import cleancode.entity.CategoryEntity
import cleancode.database.api.CategoryCache
import cleancode.repository.source.CategoryDataStore
import io.reactivex.rxjava3.core.Observable

/**
 * Construct a [CategoryDataStore] based file system data store.
 *
 * @param categoryCache A [CategoryCache] to cache data retrieved from the api.
 */
class DiskCategoryDataStore(private val categoryCache: CategoryCache) : CategoryDataStore {

    override fun categoryEntityList(): Observable<List<CategoryEntity>> {
        //TODO: implement simple cache for storing/retrieving collections of messages.
        throw UnsupportedOperationException("Operation is not available!!!")
    }

    override fun categoryEntityDetails(categoryId: Int): Observable<CategoryEntity> {
        return categoryCache[categoryId]
    }
}